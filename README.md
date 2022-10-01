# Finance Tracker

Table of contents:

- [About the project](#about-the-project)
- [Features](#features)
- [Tech stack](#tech-stack)
- [How to use](#how-to-use)
- [How to deploy](#how-to-deploy)

## About the project

Finance Tracker is an API for keeping track of your daily expenses.  
It's a project meant to provide an insight into my current knowledge on creating polished REST APIs with Spring Boot.

## Features

My goal for this project is to allow its users to keep a history of their incomes and expenses across multiple
wallets.  
[Check out my GitHub project page to see, which features are implemented and which are still in the process of being added](https://github.com/users/Eukon05/projects/3)

## Tech stack

- Java 17
- Maven
- Docker (with Docker Compose)
- Multiple Spring projects (Boot, Security, etc.)
- Hibernate (Spring Data JPA)
- PostgreSQL
- IntelliJ IDEA
- Postman
- [MailDev (for testing)](https://maildev.github.io/maildev/)
- [TestContainers](https://www.testcontainers.org/)
- [SpringDoc](https://springdoc.org/)
- [MapStruct](https://mapstruct.org/)

## How to use

OpenAPI documentation is available after deployment on the URL below:  
`[root]:8080/api/v1/api-docs`  
You can also view interactive Swagger UI here:  
`[root]:8080/api/v1/swagger-ui.html`

## How to deploy

When the project is complete, I'll add a Docker Compose file for easy deployment.  
The current compose file available in the project only contains the required dependencies and not the project itself.