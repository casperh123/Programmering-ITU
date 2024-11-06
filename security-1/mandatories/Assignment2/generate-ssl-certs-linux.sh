#!/bin/bash

# Generate private key
openssl genpkey -algorithm RSA -out private.key

# Generate CSR with proper extensions
openssl req -new -key private.key -out server.csr -subj "/CN=localhost"

# Generate self-signed certificate
openssl x509 -req -days 365 -in server.csr \
    -signkey private.key \
    -out certificate.crt \
    -extensions v3_req \
    -extfile <(printf "[v3_req]\nbasicConstraints=CA:FALSE\nkeyUsage=keyEncipherment,dataEncipherment\nextendedKeyUsage=serverAuth\nsubjectAltName=DNS:localhost,IP:127.0.0.1")

# Clean up CSR
rm server.csr

# Set permissions
chmod 600 private.key
chmod 644 certificate.crt