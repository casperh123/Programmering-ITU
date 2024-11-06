sudo apt install netsh

openssl genpkey -algorithm RSA -out private.key

## Windows specific ##
netsh http add sslcert ipport=0.0.0.0:3000
netsh http add sslcert ipport=0.0.0.0:3001
netsh http add sslcert ipport=0.0.0.0:3002