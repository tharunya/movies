# Movies

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.0-SNAPSHOT/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.0-SNAPSHOT/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.1.0-SNAPSHOT/reference/htmlsingle/#using.devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.0-SNAPSHOT/reference/htmlsingle/#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

# How to run application
Pre-requisites:
- mySQL, IDE - Java 17 or above
- Replace <username> <password> and <db_name> in application.properties and create a db with <db_name> in mySQL
- Postman to test
- Install maven

To run:
- mvn clean install
- Run Application.kt file to test the application on port 8080 using postman application or alike

# How to run unit tests
- Run unit tests in order listed in the @Order annotation
