QMA Backend
===========

Overview
--------

QMA aims to bring a collaborative network of students who need help in the disciplines and students who can offer this help. In QMA, the student can register and seek tutoring or become a tutor.

# QMA Backend

This is the default API application for the QMA platform. It is a RESTfull API written in Spring Boot framework. It leverages common popular libraries such as Spring Data using as default PostgreSQL database and JWT.

## Build Status

Travis

Not Yet

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

## Running maven tasks


### Build

Build the code for production deployment.

not yet

### Create a build and run

not yet


## Running the tests

not yet

## Getting Started doc

not yet


## Contribution guidelines

Please read the <a href="">not yet</a>  