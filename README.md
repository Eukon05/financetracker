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

- Managing multiple wallets, each with its own name and currency
- Managing multiple transactions across different wallets, each with a name, value/price and category
- Converting wallets from one currency to another, based on real currency exchange rates
- Generating wallet statistics, grouped by transaction categories
- Social media login support (requires manual setup in Keycloak)

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

In order to use the API, you'll need to retrieve an access token from your Keycloak server.  
You can find instructions on how to do
it [here](https://sis-cc.gitlab.io/dotstatsuite-documentation/configurations/authentication/token-in-postman/).  
Every request to the API requires a `Bearer` type `Authorization` header with your Keycloak token.

OpenAPI documentation is available after deployment on the URL below:  
`[root]:8080/api/v1/api-docs`  
You can also view interactive Swagger UI here:  
`[root]:8080/api/v1/swagger-ui.html`

## How to deploy

### Deploy a development environment

You can set up a development environment with Docker:

1. Download the `docker` folder from this repository
2. Modify the `.env` file to suit your needs
3. Run `docker compose up` to start up the database and Keycloak
4. Go to [your Keycloak dashboard](http://localhost:8180) and login with credentials from the `.env` file
5. Click on `Create Realm` and select the file `realm-export.json` (it's present in the docker/keycloak folder)

After these steps, you should have a fully functional development environment running on your machine.  
If you've modified the `.env` file, remember to modify `application.yml` file in the source code accordingly.

### Deploy to production

While deploying to production, you need to manually download and install Keycloak on your machine.  
That's because the compose image uses Keycloak in development mode, which, according to Keycloak documentation, is not
secure and shouldn't be used in production.

After your database and Keycloak are up and running, download the latest release ZIP file
from [here](https://github.com/Eukon05/financetracker/releases).

Now, you have a few options on how to pass your database and Keycloak credentials to the app.
Instructions on how to do it are listed [here](https://www.baeldung.com/spring-properties-file-outside-jar) 
