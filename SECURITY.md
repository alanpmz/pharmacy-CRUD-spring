# Security Disclaimer for Development Keys
This repository, a CRUD application demonstrating JWT-based authentication, utilizes dummy cryptographic keys that are automatically generated inside the Docker container on startup.

# ⚠️ CRITICAL WARNING: DO NOT USE THESE KEYS IN PRODUCTION
## The private key used for JWT signing is generated with every container run and is intended only for demonstrating the application's functionality. Any production environment that uses this key generation method or exposes the keys will be highly vulnerable to attack and compromise.


The automatic generation is implemented via the generate_keys.sh script inside the Docker container to ensure the application is functional out-of-the-box for reviewers and collaborators without manual setup.

# Production Key Strategy
For any real-world deployment of this service, you MUST replace the development key generation mechanism with a persistent, unique, and secure pair of RSA keys.

# Recommended Production Best Practices:

**Generate a Secure Pair**: Generate a high-bit RSA key pair (4096-bit).

**Secret Management**: Store the Private Key in a dedicated Secret Management service (e.g., AWS Secrets Manager, Azure Key Vault, HashiCorp Vault) or inject it as a highly restricted environment variable.

**Public Key**: The public key can be safely stored in configuration or retrieved from the security manager, but it MUST NOT be the same public key used for local development.