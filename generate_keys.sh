#!/bin/bash
set -e

KEY_PATH="/backend/classes"
PRIVATE_KEY_FILE="app.key"
PUBLIC_KEY_FILE="app.pub"

echo "Running key generation script..."

if [ -f "$KEY_PATH/$PRIVATE_KEY_FILE" ] && [ -f "$KEY_PATH/$PUBLIC_KEY_FILE" ]; then
    echo "Existing keys found at $KEY_PATH. Skipping generation."
else
    echo "Keys not found. Generating dummy RSA keys for JWT authentication..."

    mkdir -p "$KEY_PATH"

    openssl genpkey -algorithm RSA -out "$KEY_PATH/$PRIVATE_KEY_FILE" -pkeyopt rsa_keygen_bits:2048

    openssl rsa -pubout -in "$KEY_PATH/$PRIVATE_KEY_FILE" -out "$KEY_PATH/$PUBLIC_KEY_FILE"

    echo "Successfully generated dummy keys in $KEY_PATH."
fi

echo "Starting Spring Boot application..."

exec java -cp "classes:dependency/*" "com.alanpmz.pharmacy_CRUD_spring.PharmacyCrudSpringApplication" "$@"
