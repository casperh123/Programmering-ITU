using System.Net;

namespace Assignment2.Includes;

public class Share
{
    public int Value { get; set; }
    public int PatientId { get; set; }

    public Share(int value, int patientId)
    {
        Value = value;
        PatientId = patientId;
    }
}