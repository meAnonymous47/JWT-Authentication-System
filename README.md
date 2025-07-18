# 🛡️ Spring Boot JWT Authentication Store API

This is a secure RESTful API built with **Spring Boot** that allows users to register, authenticate using **JWT (JSON Web Token)**, and access store items after authentication.

---

## 🚀 Technologies Used

- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **Postman** – used for testing API endpoints

---

## 📌 Features

- User registration and login
- JWT-based authentication
- Secure access to store items after login
- Stateless sessions using JWT
- REST API with role-based access (if implemented later)

---

##  API Testing

Use **Postman** to test the following endpoints:

### 🔐 Authentication

- `POST /register` – Register a new user  
- `POST /login` – Authenticate and receive JWT token

## 📦 Store Module

### 🛒 Add Items
- **POST** `/items`
- Description: Adds a new item to the Dukaan store.

### 📂 Get All Items
- **GET** `/items`
- Description: Fetches all available items from the store.

### 🔍 Get Item by ID
- **GET** `/items/{id}`
- Description: Fetches a specific item based on the provided ID.


---

## ⚙️ How to Run

1. Clone the repository  
2. Configure your `application.properties` file (DB credentials, JWT secret, etc.)
3. Run the application using your IDE or:

```bash
mvn spring-boot:run
