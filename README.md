# Task Management API - Backend

This is the backend service for the Task Management System, built using Spring Boot. It provides a REST API for task management with JWT authentication and is designed to run with Docker and PostgreSQL.

## Features

- **User authentication and authorization with JWT.**
- **Task management (CRUD operations).**
- **Secure API endpoints using Spring Security.**
- **Uses PostgreSQL as the database (via Docker container).**

## Built With

- **Java**
- **Spring Boot**
- **PostgreSQL** (via Docker container)

## Getting Started
These instructions will help you set up a local copy of this project for running and testing purposes.

### Prerequisites

Here is a list of software that need to be installed for this project to work:

- **Java 11+**
- **Docker & Docker Compose installed**

### Running the Project

1. Clone the Repository

         $ git clone https://github.com/your-repo/task-management-api.git
         $ cd task-management-api

2. Build the Project

        $ mvn clean install

3. Run with Docker Compose

        $ docker-compose up --build

4. Verify Running Containers

        $ docker ps

5. Access the API

The backend API will be available at:

    http://localhost:8080

## API Endpoints

### Authentication

      POST /api/auth/register - Register a new user

      POST /api/auth/login - Authenticate and get JWT token

### Task Management

      GET /api/tasks - Get all tasks (Authenticated users)

      POST /api/tasks - Create a new task

      PUT /api/tasks/{id} - Update a task

      DELETE /api/tasks/{id} - Delete a task

### Authorization

JWT token must be provided in the Authorization header as:

    Authorization: Bearer <token>

### Testing

Run unit tests using Mockito:

    $ mvn test

### Stopping the Containers

To stop and remove all running containers:

    $ docker-compose down

## This will start

- **Backend API** (Spring Boot app)

- **PostgreSQL database** (as a container)

## License

This project is licensed under the **MIT License**.