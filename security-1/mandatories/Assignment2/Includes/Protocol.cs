namespace Assignment2.Includes;
using System.Net;
using System.Text;
using System.Text.Json;

public class Protocol
{
    private readonly HttpListener _listener;
    private readonly HttpClient _client;
    
    public Action<Share> OnShare;
    public Action<Share> OnAggregateShare;

    private int _id { get; }
    private int _port { get; }

    public Protocol(int id)
    {
        _id = id;
        _port = 3000 + id;
        
        _client = new HttpClient();
        
        _listener = new HttpListener();
        _listener.Prefixes.Add($"http://localhost:{_port}/");
        _listener.Start();
    }

    public async Task VerifyConnections()
    {
        for (int i = 0; i < 3; i++)
        {
            if (i == (_port - 3000))
            {
                continue;
            }
            await Connect(3000 + i);
        }
    }

    public async Task BroadCastAll(List<Share> shares)
    {
        foreach (Share share in shares)
        {
            if (share.PatientId == _id)
            {
                // Handle local shares
                if (share.Aggregate)
                {
                    OnAggregateShare?.Invoke(share);
                }
                else
                {
                    OnShare?.Invoke(share);
                }
            }
            else
            {
                // Send to other patients
                await Send(share, share.PatientId);
            }
        }
    }
    
    private async Task Send(Share share, int targetId)
    {
        HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Post, $"http://localhost:{3000 + targetId}/")
        {
            Content = new StringContent(JsonSerializer.Serialize(share), Encoding.UTF8, "application/json")
        };
            
        await _client.SendAsync(request);
    }

    private async Task Connect(int port)
    {
        bool connected = false;
        
        do
        { 
            try
            {
                HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, $"http://localhost:{port}/");
                
                await _client.SendAsync(request);
                
                connected = true;
                
                Console.WriteLine($"Patient {_id} - Connected to {port}");
            }
            catch (Exception e)
            {
                Console.WriteLine($"Patient {_id} - Failed to connect to {port}");
                await Task.Delay(1000);
            }
        } while (!connected);
    }

    public async Task StartListening()
    {
        _listener.Start();
        
        while (_listener.IsListening)
        {
            HttpListenerContext context = await _listener.GetContextAsync();
            _ = ProcessRequestAsync(context);
        }

    }
    
    private async Task ProcessRequestAsync(HttpListenerContext context)
    {
        try
        {
            HttpListenerRequest request = context.Request;
            HttpListenerResponse response = context.Response;

            try
            {
                if (request.HttpMethod is "GET")
                {
                    await HandleGetRequest(response);
                }
                else if (request.HttpMethod is "POST")
                {
                    await HandlePostRequest(request, response);
                }
                else
                {
                    response.StatusCode = (int)HttpStatusCode.MethodNotAllowed;
                }
            }
            finally
            {
                response.Close();
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error processing request: {ex}");
            throw;
        }
    }
    
    private async Task HandleGetRequest(HttpListenerResponse response)
    {
        response.StatusCode = (int)HttpStatusCode.OK;
        byte[] buffer = "Success"u8.ToArray();
        response.ContentLength64 = buffer.Length;
        await response.OutputStream.WriteAsync(buffer);
    }

    private async Task HandlePostRequest(HttpListenerRequest request, HttpListenerResponse response)
    {
        using StreamReader reader = new StreamReader(request.InputStream, Encoding.UTF8);
        string body = await reader.ReadToEndAsync();
        Share share = JsonSerializer.Deserialize<Share>(body);
            
        Console.WriteLine($"{_id} - Received Message: {body}");

        if (share.Aggregate)
        {
            OnAggregateShare?.Invoke(share);
        }
        else
        {
            OnShare?.Invoke(share);
        }
            
        response.StatusCode = (int)HttpStatusCode.Accepted;
        byte[] buffer = "Success"u8.ToArray();
        response.ContentLength64 = buffer.Length;
        await response.OutputStream.WriteAsync(buffer);
    }
}