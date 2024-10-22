
using Assignment2.Includes;

Patient patient1 = new Patient("Patient 1", 0);
Patient patient2 = new Patient("Patient 2", 1);
Patient patient3 = new Patient("Patient 3", 2);

await patient1.InitializeAsync();
await patient2.InitializeAsync();
await patient3.InitializeAsync();