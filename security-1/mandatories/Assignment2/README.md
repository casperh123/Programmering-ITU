# Private, multiparty computation using TLS and 

## Prerequisites

- .NET 8.0 SDK.
- netsh (aka a Windows system).
- Windows

## Generating self-signed SSL certificates

Generate a self-signed SLL certificate for your machine using:

    generate-ssl-certs-windows.sh

Or by running the commands found within that file (in powershell or equivalent bash compatible terminal).

## Running the program

The program will automatically simulate communication and multiparty secure computation between
3 simulated hospital patients. Each patients individual results will be printet to the console, and if 
the algorithm was successful, they should all have the same result.

Run:

    dotnet run

## Caveats

The program might have to be run a couple of times to see more than 1 patient returning a value. The program could 
terminate when only 1 patient has recieved an aggregate value, since there is no consistency guarantee in the system.

HTTPS for HttpListener (Which my implementation relies on) only works for Windows machines. I use Linux.
This implementation should work on windows, by using the generate-ssl-certs-windows.sh script, and then running the program.
However, it CANNOT work on Linux.

As cited from a GitHub issue:

    ...the HttpListener class is a legacy component we ported from .NET Framework (which is Windows only) to 
    .NET Core. But HTTPS functionality is not supported on Linux.