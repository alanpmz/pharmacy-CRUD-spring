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

## Quick Start with Docker

### Prerequisites
- [Docker](https://www.docker.com)
- [Docker Compose](https://docs.docker.com/compose/)

### Setup
```bash
# Clone the repository
git clone https://github.com/alanpmz/pharmacy-crud-spring.git

# Open the repository folder 
cd pharmacy-crud-spring

# Start the application
docker-compose up
```

### Access Points
Application: http://localhost:8080

Database: localhost:3306 (username: root, password: meds)

---

# API Endpoints Reference

## Supplier Endpoints

### **CRUD Operations**
- **POST** `/api/suppliers`
- **GET** `/api/suppliers`
- **GET** `/api/suppliers/{id}`
- **PUT** `/api/suppliers/{id}`
- **DELETE** `/api/suppliers/{id}`

### **Search Operations**
- **GET** `/api/suppliers/name/{name}`
- **GET** `/api/suppliers/name/contains?name={name}`
- **GET** `/api/suppliers/name/prefix?prefix={prefix}`

---

##  Medicine Endpoints

### **CRUD Operations**
- **POST** `/api/medicines`
- **GET** `/api/medicines`
- **GET** `/api/medicines/{id}`
- **PATCH** `/api/medicines/{id}`
- **DELETE** `/api/medicines/{id}`

### **Search by Name**
- **GET** `/api/medicines/name/{name}`
- **GET** `/api/medicines/name/contains?name={name}`
- **GET** `/api/medicines/name/prefix?prefix={prefix}`
- **GET** `/api/medicines/name/{name}/form/{dosageForm}`
- **GET** `/api/medicines/name/form?name={name}&dosageForm={dosageForm}`

### **Search by Dosage**
- **GET** `/api/medicines/dosage/form/{dosageForm}`
- **GET** `/api/medicines/dosage/{dosage}`
- **GET** `/api/medicines/dosage/greater/{dosage}`
- **GET** `/api/medicines/dosage/less/{dosage}`
- **GET** `/api/medicines/dosage/form/{dosageForm}/dosage/{dosage}`

### **Search by Quantity**
- **GET** `/api/medicines/quantity/{quantity}`
- **GET** `/api/medicines/quantity/greater/{quantity}`
- **GET** `/api/medicines/quantity/less/{quantity}`

### **Search by Price**
- **GET** `/api/medicines/price/{price}`
- **GET** `/api/medicines/price/greater/{price}`
- **GET** `/api/medicines/price/less/{price}`

### **Search by Expiry Date**
- **GET** `/api/medicines/expiry?expiryDate={expiryDate}`
- **GET** `/api/medicines/expiry/after?expiryDate={expiryDate}`
- **GET** `/api/medicines/expiry/before?expiryDate={expiryDate}`
- **GET** `/api/medicines/expired`

### **Business Operations**
- **PATCH** `/api/medicines/{id}/stock?quantity={quantity}`
- **GET** `/api/medicines/supplier/{supplierId}`