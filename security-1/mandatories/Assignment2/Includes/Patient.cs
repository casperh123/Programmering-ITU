using System.Net;
using System.Net.Sockets;
using System.Text;

namespace Assignment2.Includes;

public class Patient
{
    private TcpListener _listener;
    private IDictionary<int, TcpClient> _clients;
    private int _port { get; }
    private int _fieldSize = 23;

    public string Name { get; }
    public int Id { get; }

    public Patient(string name, int id)
    {
        Name = name;
        Id = id;
        _port = 3000 + id;
        _clients = new Dictionary<int, TcpClient>();
        _listener = new TcpListener(IPAddress.Any, _port);

        _listener.Start();
    }

    public async Task InitializeAsync()
    {
        for (int i = 0; i < 4; i++)
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
        TcpClient client = new TcpClient();
        
        do
        {
            try
            {
                await client.ConnectAsync(IPAddress.Loopback, port);
                
                _clients[id] = client;
                connected = true;
                
                Console.WriteLine($"Patient {Id} - Connected to {client.Client.RemoteEndPoint}");
            }
            catch
            {
                await Task.Delay(1000);
            }
        } while (!connected);
    }

    public async Task SendShare(int targetId, int share)
    {
        TcpClient client = _clients[targetId];
        byte[] messageBuffer = Encoding.UTF8.GetBytes(share.ToString());

        await client.GetStream().WriteAsync(messageBuffer);
    }

    public async Task RecieveShares()
    {
        
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