# Alexandria API 

## Summary
- [Overview](#overview)
- [Why I Built This?](#why-i-built-this?)
- [Methodologies Used](#methodologies-used)
- [Tech Stack](#tech-stack)
- [API](#api)
- [Autenticação](#autenticação-e-uso-da-api)
- [Rodando Localmente o Projeto](#rodando-localmente-o-projeto)

## Overview

Welcome to the Alexandria API, a RESTful service designed to manage the core entities of a digital library system. This API allows users to handle user registration, authentication, author management, and book operations with full CRUD capabilities.

## Why I Built This?
I'm always striving to improve my coding skills, and I'm currently focused on Java. This is the second version of a project I built while taking a course. In this version, I aimed to deepen my knowledge by implementing the application according to SOLID principles and good practices related to project development. Additionally, I chose to adopt an issue-to-pull request (PR) pattern to keep my development history more organized.

## Methodologies Used

- **TDD (Test-Driven Development)**: The entire backend was developed following TDD. Tests were written before implementing functionalities, ensuring that the code meets requirements and is testable from the start.

- **Git Workflow**: Development followed Git Workflow practices, with the project's timeline documented in **Issues** and **Pull Requests**. This facilitates tracking changes and collaboration.


## Tech Stack

- **Spring Boot**: Used for the backend. Improved my Java skills and demonstrated my adaptability.

- **Swagger**: Used for API documentation, following testing recommendations. It provides interactive documentation for the API.

- **Docker**: Used to containerize and test the API.

- **PostgreSQL**: Relational database used to store user data, ensuring integrity and performance in CRUD operations.

- **bcrypt**: Used for hashing passwords, providing an additional layer of security.

- **JWT (JSON Web Token)**: Used for authentication and authorization, ensuring that only authenticated and authorized users can access certain routes and functionalities.

- **Flyway**: A migration tool used to manage and version database schemas, facilitating data manipulation with a schema-based approach.


## API

The API was documented with Swagger to facilitate the exploration and testing of the endpoints. The documentation can be accessed at localhost:8080/api. It includes details about the available endpoints, expected parameters, and response formats, providing a clear and comprehensive view of the API's functionalities.

## Authentication and API Usage

Some routes are restricted and can only be accessed by users with the `admin` role. Routes that require the `admin` role include sensitive operations, such as deleting users and updating critical information, ensuring that only users with appropriate privileges can perform these actions.

To obtain a JWT token, the user must go through the authentication routes provided by the API, such as the login route. The JWT token must be included in the `Authorization` header of requests, in the format `Bearer <token>`.


## Rodando localmente o projeto

1. Clone o repositório

    ```bash
   git clone git@github.com:gabesouto/user-storage.git
    ```

2. Navegue para o repositório

    ```bash
    cd user-storage
    ```

3. Instale as depêndencias

    ```bash
    npm run install:apps
    ```
4. Adicione as suas credencias do PostgreSQL ao arquivo <Strong>.env-example</Strong> e renomeie ele para <Strong>.env</Strong>

5. Inicia o banco de dados com o docker compose. Dependendo da sua versão do docker compose talvez seja necessário utilizar `docker compose` ao invés de `docker-compose`

    ```bash
    cd backend && npm run db:reset
    ```

6. Inicie a aplicação em dois terminais seperados, um para cada serviço.
   /backend

    ```bash
    npm run dev
    ```
7. Inicie a aplicação no frontend
  /frontend

    ```bash
    npm run dev
    ```
