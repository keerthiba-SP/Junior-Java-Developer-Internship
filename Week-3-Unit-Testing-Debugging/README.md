# Week 3 - Unit Testing and Debugging

This submission extends the Week 2 **Library Management System** with JUnit 5 unit tests, CRUD coverage, edge-case tests, debugging documentation, and a small refactoring of the application.

## Important
The uploaded Week 2 project was a **Library Management System**, not an Inventory Management System. Therefore, this Week 3 package tests the uploaded Library Management code.

## Structure

- `src/main/java/Book.java` - Book model
- `src/main/java/Library.java` - CRUD/search logic
- `src/main/java/Library_Management.java` - console application
- `src/test/java/LibraryTest.java` - JUnit 5 tests
- `VerificationRunner.java` - dependency-free verification runner used for local checks
- `pom.xml` - Maven/JUnit configuration
- `Week-3-Debugging-Report.docx` - required documentation

## JUnit test coverage

The test suite covers:
- Create: valid add, duplicate ISBN, case-insensitive duplicate ISBN, invalid/blank ISBN
- Read: get all books, ISBN search, missing ISBN, author search, missing/null author
- Update: successful update, missing book, invalid title/year
- Delete: successful delete, missing/null ISBN
- Model setters/getters

## Run with Maven

From the project root:

```bash
mvn test
```

Java 17+ is recommended.

## Run the included local verification

The environment used to prepare this package did not have Maven/JUnit installed, so the final application logic was also checked with the dependency-free `VerificationRunner`.

```bash
javac -d out src/main/java/*.java VerificationRunner.java
java -cp out VerificationRunner
```

The report records the local verification outcome and explains the JUnit setup.
