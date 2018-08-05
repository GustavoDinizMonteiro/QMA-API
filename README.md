QMA Backend
===========

Overview
--------

QMA aims to bring a collaborative network of students who need help in the disciplines and students who can offer this help. In QMA, the student can register and seek tutoring or become a tutor.

# QMA Backend

This is the default API application for the QMA platform. It is a RESTfull API written in Spring Boot framework. It leverages common popular libraries such as Spring Data using as default PostgreSQL database and JWT.

## Build Status

Travis

[![Build Status](https://travis-ci.org/GustavoDinizMonteiro/QMA-API.svg?branch=master)](https://travis-ci.org/GustavoDinizMonteiro/QMA-API)

## API Documentation

<a target="_blank" href="">Access swagger reference here</a>


## Building from source

1. Ensure you have 

   ```java``` installed - goto http://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html to download installer for your OS.    
   ```maven``` installed - goto https://maven.apache.org/download.cgi to download latest version of maven.

1. Clone this repository to your local filesystem (default branch is 'master')

1. To download the dependencies
   ```
    mvn install
   ```

1. To run the application, run the following command on the project root folder

   ```
    mvn spring-boot:run
   ```

   Note: If you see a warning similar to the one shown below on running `Disabling contextual LOB creation as createClob() method threw error`, see this question https://stackoverflow.com/questions/4588755/disabling-contextual-lob-creation-as-createclob-method-threw-error

   ```
    java.lang.reflect.InvocationTargetException: null
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:564) ~[na:na]
        at org.hibernate.engine.jdbc.env.internal.LobCreatorBuilderImpl.useContextualLobCreation(LobCreatorBuilderImpl.java:113) [hibernate-core-5.2.17.Final.jar:5.2.17.Final]
        at org.hibernate.engine.jdbc.env.internal.LobCreatorBuilderImpl.makeLobCreatorBuilder(LobCreatorBuilderImpl.java:54) [hibernate-core-5.2.17.Final.jar:5.2.17.Final]
        at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentImpl.<init>(JdbcEnvironmentImpl.java:271) [hibernate-core-5.2.17.Final.jar:5.2.17.Final]
   ```

   Note: If you try to get GraphQL Schema ou try to access GraphiQL you must have an authorization token in your local storage. This may be inconvenient, but the graph-tool provided in `spring-boot-start-*` does not yet have the functionality to automatically generate authentication, but there is already a published issue for this:
   
   https://github.com/graphql-java/graphql-spring-boot/issues/106

## Running maven tasks


### Build

For production you need to provide to enviroment variables:

* `DATABASE_URL`: Url for ProstgreSQL Database
* `REDIS_URL`: Url for Redis chache

With this you need just run the following commands:

* `mvn install`

* `java -jar target/*.jar --spring.profiles.active=prod`

and the aplication will start in port 8080. Opitionaly you can use a specific port:

* `java -jar target/*.jar --spring.profiles.active=prod --server.port=$PORT`

### Other alternative ways to generate a build.

not yet


## Running the tests

You just need to run the following command:

`mvn test`


## Getting Started doc

Swagger documentation: https://qma-api.herokuapp.com/swagger-ui.html


## Contribution guidelines

Not yet