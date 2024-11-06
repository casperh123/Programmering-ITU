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

rm server.csr

## Windows specific ##
netsh http add sslcert ipport=0.0.0.0:3000
netsh http add sslcert ipport=0.0.0.0:3001
netsh http add sslcert ipport=0.0.0.0:3002