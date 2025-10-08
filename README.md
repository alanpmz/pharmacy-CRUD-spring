# Pharmacy Stock Management System

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-007396?logo=openjdk&logoColor=white"  alt=""/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.6-6DB33F?logo=springboot&logoColor=white"  alt=""/>
  <img src="https://img.shields.io/badge/Spring%20Data%20JPA-3.5.4-6DB33F?logo=spring&logoColor=white"  alt=""/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=white"  alt=""/>
  <img src="https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=white"  alt=""/>
  <img src="https://img.shields.io/badge/Spring%20Web-6DB33F?logo=spring&logoColor=white"  alt=""/>
</p>

A complete REST API for pharmacy inventory management built with Spring Boot, featuring supplier and medicine management with full CRUD operations and advanced search capabilities.

## Technologies Used

- **Java 21** - Core application logic
- **Spring Boot 3.5.6** - Application framework
- **Spring Data JPA** - Database operations and ORM
- **MySQL 8.0** - Relational database
- **Docker & Docker Compose** - Containerization for easy deployment
- **Lombok** - Boilerplate reduction (getters, setters, builders)
- **Validation** - Request validation and error handling
- **REST API** - Full RESTful endpoints with proper HTTP status codes

## Features

### Supplier Management
-  Create, read, update, and delete suppliers
-  Input validation and error handling

### Medicine Management
-  Complete CRUD operations for medicines
-  Advanced search by dosage, quantity, price, and expiry date
-  Stock management and quantity updates
-  Supplier relationship with cascade delete
-  Comprehensive validation (positive values, future dates, etc.)

### Search & Filtering
-  Find by dosage form, dosage range, quantity, price
-  Search by expiry date (before, after, expired medicines)
-  Find medicines by supplier
-  Range-based searches (greater than, less than)

### API Features
-  Proper error handling with meaningful messages
-  Input validation with custom error responses
-  Pagination-ready architecture
-  Transaction management for data consistency

## Architecture
Controller Layer (REST API)<br>
↓<br>
Service Layer (Business Logic)<br>
↓<br>
Repository Layer (Data Access)<br>
↓<br>
JPA Entities (Domain Model)<br>
↓<br>
MySQL Database<br>

## ⚠️ SECURITY WARNING: Development Keys (AUTOMATICALLY GENERATED)
This project uses JWTs signed with RSA keys. For maximum ease of use, a non-sensitive key pair is automatically generated inside the Docker container at startup using the included generate_keys.sh script.

### THESE RANDOMLY GENERATED KEYS MUST NEVER BE USED IN PRODUCTION.
For a full security policy and details on production deployment best practices, please consult the dedicated [SECURITY.MD](security.md) file.

## Quick Start with Docker

### Prerequisites
- [Docker](https://www.docker.com)
- [Docker Compose](https://docs.docker.com/compose/)

### Setup
```bash
# Clone the repository
git clone [https://github.com/alanpmz/pharmacy-crud-spring.git](https://github.com/alanpmz/pharmacy-crud-spring.git)

# Open the repository folder 
cd pharmacy-crud-spring

# Build and start the application. 
# The Docker container will automatically generate the required JWT keys on first run.
docker-compose up --build
```

### Access Points
Application: http://localhost:8080

Database: localhost:3306 (username: root, password: meds)

---


## API Endpoints Full Reference

### 1. Authentication Endpoints

| Method | Endpoint | Description | Requires Scope | 
| ----- | ----- | ----- | ----- | 
| **POST** | `/api/users/register` | Creates a new user with the `USER` role. | `permitAll` | 
| **POST** | `/api/users/login` | Authenticates a user and returns a JWT access token. | `permitAll` | 
| **GET** | `/api/users` | Lists all users. | `admin` | 

### 2. Supplier Endpoints

| Method | Endpoint | Description | Requires Scope | 
| ----- | ----- | ----- | ----- | 
| **POST** | `/api/suppliers` | Creates a new supplier. | `admin` | 
| **GET** | `/api/suppliers` | Lists all suppliers. | `authenticated` | 
| **GET** | `/api/suppliers/{id}` | Retrieves a supplier by ID. | `authenticated` | 
| **PUT** | `/api/suppliers/{id}` | Updates an existing supplier. | `admin` | 
| **DELETE** | `/api/suppliers/{id}` | Deletes a supplier. | `admin` | 
| **GET** | `/api/suppliers/name/{name}` | Finds suppliers by exact name match. | `authenticated` | 
| **GET** | `/api/suppliers/name/contains?name={name}` | Finds suppliers whose name contains the search string. | `authenticated` | 
| **GET** | `/api/suppliers/name/prefix?prefix={prefix}` | Finds suppliers whose name starts with the given prefix. | `authenticated` | 

### 3. Medicine Endpoints

#### CRUD and Stock Operations

| Method | Endpoint | Description | Requires Scope | 
| ----- | ----- | ----- | ----- | 
| **POST** | `/api/medicines` | Creates a new medicine. | `admin` | 
| **GET** | `/api/medicines` | Lists all medicines. | `authenticated` | 
| **GET** | `/api/medicines/{id}` | Retrieves a medicine by ID. | `authenticated` | 
| **PATCH** | `/api/medicines/{id}` | Partially updates a medicine. | `admin` | 
| **DELETE** | `/api/medicines/{id}` | Deletes a medicine. | `admin` | 
| **PATCH** | `/api/medicines/{id}/stock?quantity={quantity}` | Updates the stock quantity. | `admin` | 
| **GET** | `/api/medicines/supplier/{supplierId}` | Finds all medicines supplied by a specific supplier ID. | `authenticated` | 

#### Search by Name & Form

| Method | Endpoint | Description | Requires Scope | 
| ----- | ----- | ----- | ----- | 
| **GET** | `/api/medicines/name/{name}` | Finds medicines by exact name match. | `authenticated` | 
| **GET** | `/api/medicines/name/contains?name={name}` | Finds medicines whose name contains the search string. | `authenticated` | 
| **GET** | `/api/medicines/name/prefix?prefix={prefix}` | Finds medicines whose name starts with the given prefix. | `authenticated` | 
| **GET** | `/api/medicines/name/{name}/form/{dosageForm}` | Finds medicines by exact name and dosage form match. | `authenticated` | 
| **GET** | `/api/medicines/name/form?name={name}&dosageForm={dosageForm}` | Finds medicines by name and dosage form. | `authenticated` | 
| **GET** | `/api/medicines/dosage/form/{dosageForm}` | Finds medicines by exact dosage form. | `authenticated` | 

#### Search by Dosage & Quantity

| Method | Endpoint | Description | Requires Scope | 
| ----- | ----- | ----- | ----- | 
| **GET** | `/api/medicines/dosage/{dosage}` | Finds medicines by exact dosage value. | `authenticated` | 
| **GET** | `/api/medicines/dosage/greater/{dosage}` | Finds medicines with dosage greater than the specified value. | `authenticated` | 
| **GET** | `/api/medicines/dosage/less/{dosage}` | Finds medicines with dosage less than the specified value. | `authenticated` | 
| **GET** | `/api/medicines/dosage/form/{dosageForm}/dosage/{dosage}` | Finds medicines by dosage form AND exact dosage value. | `authenticated` | 
| **GET** | `/api/medicines/quantity/{quantity}` | Finds medicines with exact stock quantity. | `authenticated` | 
| **GET** | `/api/medicines/quantity/greater/{quantity}` | Finds medicines with stock quantity greater than the specified value. | `authenticated` | 
| **GET** | `/api/medicines/quantity/less/{quantity}` | Finds medicines with stock quantity less than the specified value. | `authenticated` | 

#### Search by Price & Expiry Date

| Method | Endpoint | Description                                                                           | Requires Scope | 
| ----- | ----- |---------------------------------------------------------------------------------------| ----- | 
| **GET** | `/api/medicines/price/{price}` | Finds medicines with exact price.                                                     | `authenticated` | 
| **GET** | `/api/medicines/price/greater/{price}` | Finds medicines with price greater than the specified value.                          | `authenticated` | 
| **GET** | `/api/medicines/price/less/{price}` | Finds medicines with price less than the specified value.                             | `authenticated` | 
| **GET** | `/api/medicines/expired` | Finds all medicines that have already expired.                                        | `authenticated` | 
| **GET** | `/api/medicines/expiry?expiryDate={expiryDate}` | Finds medicines expiring on a specific date `(Format example: 2023-10-05T14:30+02:00)`. | `authenticated` | 
| **GET** | `/api/medicines/expiry/after?expiryDate={expiryDate}` | Finds medicines expiring after the specified date.                                    | `authenticated` | 
| **GET** | `/api/medicines/expiry/before?expiryDate={expiryDate}` | Finds medicines expiring before the specified date.                                   | `authenticated` |