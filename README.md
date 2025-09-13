# Database Project – Market Management System

A **Java 17** application integrating with **PostgreSQL** through JDBC.  
The system models a digital marketplace with robust data management and object-oriented design, demonstrating practical use of relational databases in software engineering.

## 🚀 Key Features
- PostgreSQL integration using JDBC  
- Full CRUD operations for marketplace entities (Users, Sellers, Products, Shopping Carts)  
- Modular, object-oriented architecture for scalability and maintainability  
- Externalized configuration for secure credential handling (`config.properties`)  

## 📂 Project Structure
```
src/
└── shon_daniel/
   ├── data_base/
   │   └── DBapi.java  (Centralized database API)
   ├── User.java
   ├── Seller.java
   ├── Buyer.java
   ├── Product.java
   └── ShoppingCart.java
resources/
└── config.properties.example

.gitignore
CREDENTIALS_NOTE.md
```

## ⚙️ Setup & Run

### 1. Requirements
- Java 17+
- PostgreSQL installed locally or accessible remotely

### 2. Configuration
- Copy `config.properties.example` â†’ `config.properties`  
- Insert your own database credentials:
  ```
  db.url=jdbc:postgresql://localhost:5432/college
  db.user=your_username
  db.password=your_password
  ```

### 3. Build & Run
```
javac -d out -cp jar_file/postgresql-42.7.5.jar src/shon_daniel/**/*.java
java -cp out:jar_file/postgresql-42.7.5.jar shon_daniel.Main
```

## 🔮 Roadmap
- Extend schema with advanced relationships (e.g., many-to-many)  
- Integrate a GUI or RESTful API layer for enhanced usability  
- Add unit/integration tests for database logic  
- Optimize queries for performance on larger datasets  

## 📜 License
This project is released under the MIT License.
