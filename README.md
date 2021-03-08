# Fresh Planner

## Useful Links

* [Swagger Tutorial](https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api)
* [Http Codes](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)
* [Lombok Documentation](https://projectlombok.org/features/Data)
* [Lombok IntelliJ Plugin](https://plugins.jetbrains.com/plugin/6317-lombok)
* [CodeCoverageRepo](https://about.codecov.io/)
* [Docker Image](https://hub.docker.com/r/felixsteinke/private/tags)
* http://localhost:8080/swagger-ui/

## Database Configuration

### Local Development

__MySQL Installation:__

1. Download the [Installer](https://dev.mysql.com/downloads/installer/).
2. Install the `Server only!`
3. Configure the Server:
   * Default Network with `Port: 3306`
   * Recommended Authentication with `cashing_sha_2_password`
   * Root Password: `password`

__Prepare Database:__

1. Open the `MySQL 8.0 Command Line Client`
2. Login with the password: `password`
3. Create the Database: `CREATE DATABASE fresh_planner_database;`

### Container

Defined in the [docker-compose.yml](docker-compose.yml).

* Host: `localhost`
* Port: `3306`
* Database: `fresh_planner_database`
* Url: `jdbc:mysql://localhost:3306/fresh_planner_database`
* Username: `root`
* Password: `password`

### Table Configuration

The configuration of the tables will be automatically done from [JPA](https://spring.io/guides/gs/accessing-data-jpa/).

1. Run MySQL with the existing Database required [application.properties](src/main/resources/application.properties).
2. Run the [ServerApplication](src/main/java/com/spring/ServerApplication.java). It will create all the required tables.

### Data Backup
