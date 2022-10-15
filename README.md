# Finance Tracker

Table of contents:

- [About the project](#about-the-project)
- [Features](#features)
- [Tools and dependencies](#tools-and-dependencies)
- [How to use](#how-to-use)
- [How to deploy](#how-to-deploy)

## About the project

Finance Tracker is an API for keeping track of your daily expenses.  
It's a project meant to provide an insight into my current knowledge on creating polished REST APIs with Spring Boot.

## Features

My goal for this project is to allow its users to keep a history of their incomes and expenses across multiple
wallets.  
[Check out my GitHub project page to see, which features are implemented and which are still in the process of being added](https://github.com/users/Eukon05/projects/3)

## Tools and dependencies

- Java 17
- Maven
- Docker (with Docker Compose)
- Multiple Spring projects (Boot, Security, etc.)
- Hibernate (Spring Data JPA)
- PostgreSQL
- IntelliJ IDEA
- Postman
- JUnit 5 + Mockito
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

You can deploy Finance Tracker with Docker:

1. Download `docker-compose.yml` file from this repo and save it in an empty folder
2. Modify the file to contain your e-mail credentials and server address. I also **STRONGLY** recommend changing the
   default database and JWT settings!
3. Open a new terminal window inside the folder containing the `.yml` file and run: `docker-compose up`
