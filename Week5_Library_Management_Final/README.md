# Week 5 - Final Library Management System

## Project
This is the integrated final version of the Library Management System from Weeks 2-4.

## Technologies
- Java 17
- Java Collections (`ArrayList`, `List`)
- Maven
- JUnit 5
- Command-line interface

## Architecture
- `Book.java`: entity/model
- `Library.java`: in-memory application/data service
- `Library_Management.java`: command-line presentation and application entry point
- `LibraryTest.java`: integration/regression-oriented tests for core library behavior

## Build and test
From the project root:

    mvn clean test

## Package
Create the deployable JAR:

    mvn clean package

The resulting JAR is created in `target/` as `library-management-final-1.0.jar`.

## Run
    java -jar target/library-management-final-1.0.jar

## Windows
Run `scripts/run.bat` after building the project.

## Linux/macOS
Run:

    chmod +x scripts/run.sh
    ./scripts/run.sh

## Deployment model
The application is a command-line, in-memory application. It does not require a database or external service. The deployment unit is the executable JAR plus a compatible Java runtime.

## Data persistence
Book records are stored in memory and are lost when the application exits. A future version can replace the in-memory `Library` storage with a repository/database implementation.
