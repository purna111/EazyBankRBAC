
# EazyBank RBAC

EazyBank is a simplified application designed to showcase Role-Based Access Control (RBAC). It demonstrates how to restrict access to application features based on user roles using Angular, Spring Boot,Spring Security, and Keycloak. The main functionality involves **securely displaying data from a database, with role-based permissions** defining what data users can access.

## Architecture
EazyBank follows a **monlithic architecture**:
1. **Frontend**: Angular application communicating with backend APIs.
2. **Backend**: REST APIs built with Spring Boot for core banking operations like to fetch from Database and authorization.
3. **Authentication**: Keycloak handles user authentication.
4. **Database**: Stores user data, Account,Loans,Cards and Banking Notices

## Tech Stack
**Frontend: Angular**
- Dynamic UI with route guards for role-based navigation.
- Integration with Keycloak for authentication and role retrieval.
**Backend: Spring Boot**
- REST API secured with Keycloak and Spring Security.
- Database queries filtered by user roles.
**Database: MySQL**
- Stores data such as user details, account information, and access levels.
**Authentication and Authorization: Keycloak**
- Handles secure login and user role management.
- Enforces RBAC with JWT tokens.

## Features
**RBAC Implementation:**
- Admin users can access respected database records.
- Customer users can only access records assigned to them.
**Authentication:**
- Login using Keycloak, with redirection based on user roles.
**Data Security:**
- Backend enforces RBAC using Spring Security annotations and database queries.
**Simplified UI:**
- Angular displays only authorized content based on user roles.

## System Workflow
1. **Login Process:**
- Users log in via Keycloak.
- JWT tokens are issued and used for secure communication with the backend.
2. **Role-Based Access:**
- Keycloak assigns roles (ADMIN,USER).
- Angular fetches roles from Keycloak and configures route guards.
- Backend enforces role-based restrictions on API endpoints.
3. **Data Access:**
- Admins access there respected records.
- Customers (user) access only their associated data.
- Unauthorized attempts result in an error.


## Prerequisites

To set up and run the EazyBank project, ensure you have the following tools and software installed on your system:

### System Requirements
- **Operating System**: Windows, macOS, or Linux
- **Processor**: Dual-Core CPU or higher
- **Memory**: At least 4 GB RAM
- **Storage**: 500 MB free space

### Software Requirements

#### Frontend (Angular)
1. **Node.js** (v18.x or higher): Required for Angular CLI and running the development server.
   - [Download Node.js](https://nodejs.org/)
2. **Angular CLI** (v16.x or higher): For running Angular commands.
   ```bash
   npm install -g @angular/cli@16
   ```
####   Backend (Spring Boot)
1. **Java JDK** (v21 or higher): Required to build and run the backend application.
Download [Java JDK](https://www.oracle.com/in/java/technologies/downloads/#java21)

#### Authentication (Keycloak)
**Keycloak Server:** For authentication and role management, run it via Docker:
```bash
docker run -p 8180:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:25.0.1 start-dev
```
To create a Keycloak container
#### Database (MySQL)

**MySQL Server:** Run MySQL using Docker:
```bash
docker run -p 3306:3306 --name springsecurity -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=eazybank -d mysql
```
To create a MySQL DB container
#### Docker
**Docker:** Required for containerized deployment of services like Keycloak and MySQL.
- Download [Docker](https://www.docker.com/products/docker-desktop/)

## Configuration Requirements
#### Keycloak Realm
A Keycloak realm should be set up with the following:
- Roles: **ADMIN** and **USER**
- Client: **eazypublicclient**
- Allowed Redirect URIs: http://localhost:4200/*

## Network and Port Configuration
- Ports used by the application:
1. Frontend: http://localhost:4200
2. Backend: http://localhost:8080
3. Keycloak: http://localhost:8180
4. MySQL: http://localhost:3306


## Keycloak Setup

To run the EazyBank project, you **must configure Keycloak** by creating a realm, client, and user. Follow these steps:

### Steps to Set Up Keycloak

1. **Download and Run Keycloak**:
   - If you haven't already, run Keycloak using Docker:
     ```bash
     docker run -p 8180:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:25.0.1 start-dev
     ```
   - Access the Keycloak Admin Console at: [http://localhost:8180](http://localhost:8180)

2. **Log in to Keycloak**:
   - Default credentials:
     - Username: `admin`
     - Password: `admin` 

3. **Create a Realm**:
   - In the Keycloak Admin Console:
     1. Click **Add Realm**.
     2. Name the realm: `eazybankdev`.

4. **Create Roles**:
   - Navigate to **Roles** in the realm and create the following roles:
     - `ADMIN`
     - `USER`

5. **Create a Client**:
   - Navigate to **Clients** and click **Create**:
     - Client ID: `eazypublicclient`
     - Client Protocol: `openid-connect`
   - After saving:
     - Configure **Redirect URIs** to include: `http://localhost:4200/*`

6. **Create a User**:
   - Navigate to **Users** and click **Add User**:
     - Username: (e.g., `Happy@example.com`)
     - Assign roles (`ADMIN` or `USER`) to the user under the **Role Mappings** tab.

7. **Test the Setup**:
   - Ensure the realm, client, and user are configured correctly.
   - Start the Angular frontend, Spring Boot backend, and Keycloak services to verify the project works.

---

### Resources to Learn Keycloak

- [Official Keycloak Documentation](https://www.keycloak.org/documentation)
- [Keycloak Getting Started Guide](https://www.keycloak.org/getting-started)
- [How to Create and Configure a Keycloak Realms and client (Video)](https://youtu.be/toEVcosbedw?si=GspjZxYms6Xoa-DN)
- [Keycloak Client Setup Tutorial (Video)](https://www.youtube.com/watch?v=f1XkYxeqBZY)
### Example Screenshot


---

By following these steps, you’ll ensure Keycloak is properly set up to enable Role-Based Access Control (RBAC) for the EazyBank project.


## Database Initialization

To ensure the EazyBank project works correctly, the **database must be initialized** with the required tables and data. This can be done by executing the `schema.sql` file provided in the backend resources.

### Steps to Initialize the Database

1. **Locate the `schema.sql` File**:
   - The `schema.sql` file is located in the backend project under the `src/main/resources` directory.

2. **Set Up the Database**:
   - Ensure your database server (e.g., MySQL) is running.
   - If you're using Docker, start the MySQL container:
     ```bash
     docker run -p 3306:3306 --name springsecurity -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=eazybank -d mysql
     ```

3. **Execute the Schema**:
   - Use a database client (e.g., MySQL Workbench or the MySQL CLI) to execute the `schema.sql` file:
     ```bash
     mysql -u root -p eazybank < path/to/schema.sql
     ```
   - Replace `path/to/schema.sql` with the actual path to the file.

4. **Verify the Database**:
   - After executing the script, verify that the required tables (e.g., `accounts`, `loans`, `cards`, etc.) have been created in the `eazybank` database.

---

### Note
- Without executing the `schema.sql`, the application will not function correctly as it depends on the database schema and initial data.
- If any errors occur during execution, ensure that:
  - The database credentials in your backend environment variables are correct.
  - The MySQL server is running and accessible.

---

### Additional Resources
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Using SQL Scripts in MySQL Workbench](https://dev.mysql.com/doc/workbench/en/)


## API Documentation

The EazyBank project provides APIs for viewing bank-related information. Each API endpoint is secured using Keycloak and follows Role-Based Access Control (RBAC). Below are the available endpoints and their corresponding access roles.

### API Endpoints and Roles

| **HTTP Method** | **Endpoint**             | **Description**                        | **Required Role** |
|------------------|--------------------------|----------------------------------------|--------------------|
| `GET`           | `/notices`          | Fetch valid notices details                  | `None`         |
| `POST`           | `/contact`             | Post user details                      | `None`         |
| `GET`          | `/myAccount` | Access Account Details                       | `USER`            |
| `GET`        | `/myBalance` | Access Balance Details                 | `USER ! ADMIN`            |
| `GET`           | `/myLoans`             | Access loan details                      | `USER`         |
| `GET`           | `/myCards`             | Access credit card details             | `USER`         |

## API Documentation with Swagger

The EazyBank project comes with built-in Swagger UI for easy access to the API documentation and testing. Swagger provides a visual interface to interact with the available endpoints and view their details.

### Accessing Swagger UI

1. Start the backend Spring Boot application.
2. Open your web browser and navigate to:  
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Features of Swagger UI

- View all available API endpoints.
- Test endpoints with live requests and responses.
- See detailed descriptions of request parameters and responses.
- Authenticate using a Bearer token to access secured endpoints.

### Example Screenshot (Optional)
You can optionally include a screenshot of the Swagger UI to make it visually clear for the users.

---

### Learn More About Swagger
- [Swagger Documentation](https://swagger.io/docs/)




