
if (args[0].Equals("say"))
{
    string message = args[1];
    int frequency = int.Parse(args[2]);

    for (int i = 0; i < frequency; i++)
    {
        Console.WriteLine(message + " ");
    }
}