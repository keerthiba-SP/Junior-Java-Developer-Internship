# Library Management System (Java)

A simple menu-driven Java application that manages books using an in-memory `ArrayList`.

## Features
- Add a book with title, author, ISBN, and publication year
- View all books
- Search for a book by ISBN
- Search for books by author
- Update a book's title, author, or year
- Delete a book by ISBN
- Basic input validation and duplicate ISBN checking

## Files
- `Book.java` — represents a book and stores its details
- `Library.java` — stores the collection and provides search/add/delete operations
- `Library_Management.java` — displays the menu and handles user input

## Compile and run
Place all three Java files in the same folder. Open a terminal in that folder and run:

```bash
javac Book.java Library.java Library_Management.java
java Library_Management
```

The book collection is stored in memory, so the records are cleared when the program exits.
