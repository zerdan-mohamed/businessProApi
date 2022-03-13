::#businessProApi

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Entities](#entities)
- [Installation](#installation)
- [Running a specific test](#tests)
- [Swagger](#swagger)
- [Database Migrations](#database-migrations)
- [Environment variables](#environment-variables)

## Features

### Requirements

- MySQL: `^8`
- Java: `^11`
- Maven: `^3.8.4`

### Installation 

```shell
# clone the repository and access the directory.
$ git clone git@uri

# download dependencies
$ mvn clean install -DskipTests

# run the application
$ mvn spring-boot:run

# run the tests
$ mvn test

# to build for production
$ mvn clean package

# to generate the coverage report after testing (available at: target/site/jacoco/index.html)
$ mvn jacoco:report
```

### Running a specific test 

use the parameter `-Dtest=<class>#<method>`

for example the integration test. creating a user:
```
$ mvn test -Dtest=UsersControllerIntegrationTests#should_save_a_new_user
```