# Finance Tracker

Table of contents:

- [About the project](#about-the-project)
- [Features](#features)
- [Tech stack](#tech-stack)
- [Encountered problems](#encountered-problems)
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
- PostgreSQL Database
- IntelliJ IDEA
- Postman
- MailDev (for testing purposes)

## Encountered problems

One of many problems encountered by me in the process of making this project, was figuring out which package structure
is the preferred one for Spring Boot applications.  
Up till now, I've been using a package structure that grouped classes based on their "type" (controller, repository,
service, etc.).  
However, after
watching [Jakub Nabrdalik's talk about the package access modifier](https://www.youtube.com/watch?v=ILBX9fa9aJo&t=1398s)
, as well as after asking in multiple Java related communities, I've decided to group my classes based on their "
theme" (User, Product, Configuration, etc.).  
One of the biggest advantages of this approach is the fact, that if I want to add or modify a feature for a specific
domain object, everything that I need is in one place.  
It also allows me to only make classes public when absolutely necessary, and stick with package-private most of the
time.

Another problem I've faced was deciding on how my service classes should look like.  
I've always been making one, giant service class per "theme", which led to a lot of clutter in the code.  
So this time, I've decided to split my service classes into tiny, package-private "use cases" with only one public
method, that are then combined in a public facade.  
This allows for greater maintainability, as we can switch the "use case" implementation at any time, without breaking
the rest of the application.

## How to use

A detailed documentation will be accessible when the project is complete.  
It'll include an OpenAPI definition file, as well as a Swagger GUI.

## How to deploy

When the project is complete, I'll add a Docker Compose file for easy deployment.  
The current compose file available in the project only contains the required dependencies and not the project itself.