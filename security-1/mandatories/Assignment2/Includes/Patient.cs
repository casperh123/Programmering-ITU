using System.Net;
using System.Net.Sockets;
using System.Runtime.InteropServices.Marshalling;
using System.Text;
using System.Text.Json;

namespace Assignment2.Includes;

public class Patient
{
    private readonly HttpListener _listener;
    private readonly HttpClient _client;

    private string _name { get; }
    private int _id { get; }
    private int _port { get; }
    private int _fieldSize = 23;
    private int _secretsProcessed = 0;
    private List<Share> _shares = [];

    public Patient(string name, int id)
    {
        this._name = name;
        _id = id;
        _port = 3000 + id;
        _client = new HttpClient()
        {
            Timeout = TimeSpan.FromSeconds(1)
        };
        _listener = new HttpListener();
        
        _listener.Prefixes.Add($"http://localhost:{_port}/");

        _listener.Start();

        Listen();
    }

    public async Task InitializeAsync()
    {
        for (int i = 0; i < 3; i++)
        {
            if (i == (_port - 3000))
            {
                continue;
            }
            await Connect(i, 3000 + i);
        }
        
    }

    private async Task Connect(int id, int port)
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
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Patient {_id} - Failed to connect to {port}");
                await Task.Delay(1000);
            }
        } while (!connected);
    }

    private async Task Listen()
    {
        while (true)
        {
            await ProcessRequest();
        }
    }

    private async Task ProcessRequest()
    {
        HttpListenerContext context = await _listener.GetContextAsync();
        HttpListenerRequest request = context.Request;

        if (request.HttpMethod is "GET")
        {
            HttpListenerResponse response = context.Response;
            response.StatusCode = (int)HttpStatusCode.OK;
            byte[] buffer = "Success"u8.ToArray();
            response.ContentLength64 = buffer.Length;
            await response.OutputStream.WriteAsync(buffer, 0, buffer.Length);
        }
        else if(request.HttpMethod is "POST")
        {
            using StreamReader reader = new StreamReader(request.InputStream, Encoding.UTF8);
            string body = await reader.ReadToEndAsync();
            Share share = JsonSerializer.Deserialize<Share>(body) ?? new Share(-1, -1);
            _shares.Add(share);

        }
    }

    private async Task SendShare(int targetId, int share)
    {
        HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Post, $"http://localhost:{3000 + targetId}/")
        {
            Content = new StringContent(JsonSerializer.Serialize(share), Encoding.UTF8, "application/json")
        };
        
        await _client.SendAsync(request);
    }
    
    protected (Share, Share, Share) GenerateShares(int secret)
    {
        Random random = new Random();
        int share1 = random.Next(_fieldSize);
        int share2 = random.Next(_fieldSize);
        int share3 = (secret - share1 - share2 + _fieldSize) % _fieldSize;
            
        return (
            new Share(share1, 1),
            new Share(share2, 2),
            new Share(share3, 3)
        );
    }
    
    protected int AddShares(params Share[] shares)
    {
        int sum = 0;
        
        foreach (Share share in shares)
        {
            sum = (sum + share.Value) % _fieldSize;
        }
        
        return sum;
    }
}