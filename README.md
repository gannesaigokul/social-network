# social-network

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [APIs](#usage)

<!-- About the Project -->
## About the Project

social-network is a project developed as part of an assessment program for a company's recruitment procedure.

<!-- Built With -->
### Built With
This section should list any major frameworks that you built your project using. Leave any add-ons/plugins for the acknowledgements section. Here are a few examples.
* [Java 8](https://www.oracle.com/java/technologies/java8.html)
* [Dropwizard framework](https://www.dropwizard.io)
* [Maven build tool](https://maven.apache.org/)
* [MS SQL](https://www.microsoft.com/en-in/sql-server/sql-server-2019)

<!-- GETTING STARTED -->
## Getting Started

Below are the instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

This is an example of how to list things you need to have for application to run.
* Java
* Dropwizard
* Maven
* MS SQL

### Installation

1. Clone the repo
```sh
git clone https://github.com/gannesaigokul/social-network.git
```
2. Build project using maven
```sh
mvn clean install
```
3. Run application and update your configurations at `development.yml`
```sh
java -jar target/social-network-1.0-SNAPSHOT.jar server development.yml
```

<!-- APIs -->
## APIs
    GET     /service/feed 
    GET     /service/feed/profile 
    POST    /service/feed/write 
    PUT     /service/user/friend 
    GET     /service/user/login 
    POST    /service/user/register 
