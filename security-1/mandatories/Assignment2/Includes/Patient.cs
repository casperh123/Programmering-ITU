namespace Assignment2.Includes;

public class Patient
{
    private Protocol _protocol;
    private int Id { get; }
    private int Secret { get; }
    private readonly int _fieldSize = 23;
    private List<Share> _shares = [];
    private List<Share> _aggregateShares = [];

    public Patient(int id)
    {
        Id = id;
        Secret = new Random().Next(_fieldSize);
        _protocol = new Protocol(Id)
        {
            OnShare = OnShareRecieved,
            OnAggregateShare = OnAggregateShareRecieved
        };
        
        _protocol.StartListening();
    }

    public async Task InitializeAsync()
    {
        await _protocol.VerifyConnections();
    }

    public async Task ShareSecrets()
    {
        List<Share> shares = GenerateShares(Secret);
        
        await _protocol.BroadCastAll(shares);
    }

    private void OnShareRecieved(Share share)
    {
        _shares.Add(share);

        if (_shares.Count > 2)
        {
            ProcessShares();
        }
    }

    private void OnAggregateShareRecieved(Share share)
    {
        _aggregateShares.Add(share);

        if (_aggregateShares.Count > 2)
        {
            ProcessAggregateShares();
        }
    }

    private List<Share> GenerateShares(int secret)
    {
        Random random = new Random();
        int share1 = random.Next(_fieldSize);
        int share2 = random.Next(_fieldSize);
        int share3 = (secret - share1 - share2 + _fieldSize) % _fieldSize;

        return ([
            new Share(share1, 0),
            new Share(share2, 1),
            new Share(share3, 2)
        ]);
    }

    private void ProcessAggregateShares()
    {
        int sum = _aggregateShares.Sum(share => share.Value);
        Console.WriteLine($"Patient {Id} has {sum} Aggregate.");
    }

    private async void ProcessShares()
    {
        int sum = _shares.Sum(share => share.Value);
        List<Share> shares = [];
        
        for (int i = 0; i < 3; i++)
        {
            shares.Add(new Share(sum, i, true));
        }
        
        await _protocol.BroadCastAll(shares);
    }
}