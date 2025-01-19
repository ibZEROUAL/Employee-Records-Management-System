# Employee-Records-Management-System

This project is a REST API for managing employee records. The application is containerized using Docker Compose, and it depends on an Oracle database.

# Prerequisites

Ensure you have the following installed on your system:

<h6>Java SE 17</h6>
<h6>Maven</h6>
<h6>Docker Engine</h6>

# Running the Application

Follow these steps to run the REST API:

<h6>Step 1: Clone the Repository</h6>

<h6>Step 2: Generate App.jar using  " ./mvnw clean install -DskipTests
"</h6>

<h6>Step 3: Build and Run with Docker Compose with  "docker-compose up --build"</h6>



# Access the Application

Once the containers are running, you can access the REST API at:

Base URL: http://localhost:8080

The Oracle database will be accessible at:

<h6>Host: localhost</h6>

<h6>Port: 1521</h6>

<h6>Database: xe</h6>

<h6>Username: system</h6>

<h6>Password: password</h6>

# Verify the API

Base URL for swagger ui : http://localhost:8080/swagger-ui/index.html#/