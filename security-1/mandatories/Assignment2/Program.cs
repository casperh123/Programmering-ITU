
using Assignment2.Includes;

Patient patient1 = new Patient(0);
Patient patient2 = new Patient(1);
Patient patient3 = new Patient(2);

await patient1.InitializeAsync();
await patient2.InitializeAsync();
await patient3.InitializeAsync();

await patient1.ShareSecrets();
await patient2.ShareSecrets();
await patient3.ShareSecrets();