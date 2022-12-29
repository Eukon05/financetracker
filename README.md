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

- Managing multiple wallets, each with a given name and currency
- Managing transactions across different wallets, each with its own name, value/price and category attached to it
- Migrating a wallet from one currency to another by using real currency exchange rates from the day every transaction
  was added to the app.
- Social media login with a properly configured Keycloak server

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
- [SpringDoc](https://springdoc.org/)
- [MapStruct](https://mapstruct.org/)
- [exchangerate.host (for pulling currency exchange rates)](https://exchangerate.host/#/)
- Keycloak

## How to use

In order to use the API, you need to retrieve an access token from your Keycloak server.  
Then, you can use Postman or a similar tool to make requests. Every request requires the `Authorization` field to be set
to `Bearer yourkeycloaktokenhere`

OpenAPI documentation is available after deployment on the URL below:  
`[root]:[port]/api/v1/api-docs`  
You can also view interactive Swagger UI here:  
`[root]:[port]/api/v1/swagger-ui.html`

## How to deploy

Due to issues with Keycloak and Docker, I won't be providing instructions on how to deploy the app as of now.  
As soon as I'll resolve these issues, I'll update this file to contain detailed deployment instructions.