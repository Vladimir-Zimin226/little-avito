# Web application for selling and buying things

## Little-Avito. Description and Objectives.
- A platform for placing ads for goods and services from individuals and companies.
- The goal of the project is to create an application through which it is possible to perform the full cycle of purchasing goods: from viewing them on the site to buying them.

## The following functionality is implemented:

Functionality for unauthorized users:
- View all ads from all users.
  
Functionality for authorized users:
- Authorizing users and creating accounts for new users;
- composing/editing an ad, including its title, description, photos, and seller's contact information;
- uploading a photo of the item to the site;
- viewing your listings;
- writing/editing your own comments under the ads of other sellers.

Functionality for administrators:
- authorization in the role of administrator;
- editing all user comments;
- editing of all ads.

## Launch the application:
- clone the project into the development environment;
- configure the database connection in the application.properties file;
- install the Docker application and run it;
- download the Docker image using the command docker pull ghcr.io/bizinmitya/front-react-avito:latest
- Run the Docker image on the command line using the command docker run -p 3000:3000 ghcr.io/bizinmitya/front-react-avito:latest
- Run the main method in the HomeworkApplication.java file.
  
You will then have access to http://localhost:3000 and the Swagger UI http://localhost:8080/swagger-ui/index.html#.

## Technologies used

- Java 11;
- Spring Boot;
- Spring JPA;
- Spring Security;
- Springdoc;
- SpringBootTest;
- Docker;
- Maven;
- Lombok;
- PostgreSQL;
- Liquibase;
- Mapstruct;
- h2database for tests.

## Note
- The functionality of the backend part of the application was developed for the ready OpenAPI specification https://github.com/skygroundwater/OnlineResellingProject/blob/release/openapi.yaml.
- The application functions using requests from localhost:3000 to endpoints on localhost:8080.

## Project team
- Allan Allanazarov, developer;
- Kirill Zhukov, developer;
- Vladimir Zimin, developer.
