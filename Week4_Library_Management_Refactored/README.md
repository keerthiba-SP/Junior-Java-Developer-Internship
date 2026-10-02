# Week 4 - Refactored Library Management System

## Objective
This version refactors the Week 2/Week 3 Library Management System to improve readability,
maintainability, reuse, and basic efficiency while preserving the application's core behavior.

## Main changes
- Separated user-interface responsibilities from data-management responsibilities.
- Removed repeated input-validation code by creating reusable helper methods.
- Centralized ISBN lookup so duplicate checking, update, delete, and search use the same logic.
- Used the `List` interface while keeping `ArrayList` as the concrete in-memory collection.
- Prevented external code from directly modifying the internal collection.
- Used a boolean loop instead of `System.exit()` for cleaner program control.
- Added constants for valid publication-year boundaries.
- Added comments explaining the refactoring decisions.
- Kept JUnit regression tests to make sure the refactoring does not break existing behavior.

## Run
Using Maven:
```bash
mvn test
mvn package
java -cp target/classes Library_Management
```

Or open the project in IntelliJ IDEA / Eclipse / VS Code and run `Library_Management`.

## Note
The application still stores books in memory. Data is cleared when the application exits.
