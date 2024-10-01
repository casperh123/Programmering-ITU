# Security 1 - Mandatory excercise set 1

## ElGamal Encryption

The ElGamal public key encryption scheme has been chosen to encrypt messages between two parties.
The following tasks demonstrates how different parts of ElGamal works in practice.

The keys used for messaging are based on the following values:

$p = 666, g = 6661$, Bobs Private key - $BobPk = 2227$

### Task 1 - Message Encryption

Given

### Task 2 - Bruteforcing Bobs private key

To obtain Bobs private key, we can use the fact that we already know $g$ and $p$, and
thus that the formula for bobs public key, $B = g^b \mod p = 2227$ only contains one unknown - Bobs private key, _b_.
We also know that Bobs private key is in the range $0 \leq b \leq p - 2$, or $0 \leq b \leq 666$, making it very realistic 
to brute force it. We simply have to find a $b$, for which $B = 6661^b \mod 666 = 2227$. We will do this using a loop that tests
all numbers from $0$ to $p - 2$, and returns b once the equation is true.