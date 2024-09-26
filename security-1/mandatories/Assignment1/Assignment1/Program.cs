using System.Data;
using System.Numerics;
using System.Runtime.CompilerServices;
using System.Runtime.InteropServices.JavaScript;

int generator = 666;
int prime = 6661;
int bobPk = 2227;

int message = 2000;

// Task one

(int, int) Encrypt(int g, int p, int recieverPk, int m)
{
    int r = Random.Shared.Next(1, (p - 1));

    int R = (int)BigInteger.ModPow(g, r, p);
    int S = (int)(m * BigInteger.Pow(recieverPk, r) % p);

    return (R, S);
}

(int, int) encryptedMessage = Encrypt(generator, prime, bobPk, message);

Console.WriteLine(encryptedMessage);

// Intercept 2000, decrypt it, find bobs Private key

int FindKey(int pk)
{
    for(int i = 0; i < prime; i++) {
        
        int computedKey = (int)BigInteger.ModPow(generator, i, prime);

        if (computedKey == pk)
        {
            return i;
        }
    }

    return 0;
}

int DecryptMessage(int p, int recieverKey, (int, int) cipherText)
{
    int R = cipherText.Item1;
    int S = cipherText.Item2;
    
    int sharedSecret = (int)BigInteger.ModPow(R, recieverKey, p);
    int inverseSharedSecret = (int)BigInteger.ModPow(sharedSecret, p - 2, p);
    int decryptedMessage = S * inverseSharedSecret % p;

    return decryptedMessage;
}

int bobKey = FindKey(bobPk);

Console.WriteLine(DecryptMessage( prime, bobKey, encryptedMessage));

