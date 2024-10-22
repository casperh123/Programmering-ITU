using System.Net;

namespace Assignment2.Includes;

public class Share
{
    public int Value { get; }
    public int PatientId { get; }
    
    public bool Aggregate { get; }

    public Share(int value, int patientId, bool aggregate = false)
    {
        Value = value;
        PatientId = patientId;
        Aggregate = aggregate;
    }
}