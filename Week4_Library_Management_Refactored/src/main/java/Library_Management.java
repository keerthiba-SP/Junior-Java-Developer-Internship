import java.util.List;
import java.util.Scanner;

/**
 * Command-line interface for the Library Management System.
 *
 * Refactoring and optimization:
 * - Menu handling is separated into small methods.
 * - Repeated input logic is placed in readInt() and readRequiredText().
 * - Library is responsible for data operations; this class is responsible
 *   for user interaction.
 * - A boolean loop is used instead of System.exit(), making the program
 *   easier to test and maintain.
 */
public class Library_Management {
    private static final int MIN_YEAR = 1;
    private static final int MAX_YEAR = 2026;

    private final Scanner scanner;
    private final Library library;

    public Library_Management() {
        scanner = new Scanner(System.in);
        library = new Library();
    }

    public static void main(String[] args) {
        new Library_Management().run();
    }

    public void run() {
        System.out.println("#################################");
        System.out.println("    Library Management System");
        System.out.println("#################################");

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewAllBooks();
                case 3 -> searchBooks();
                case 4 -> updateBook();
                case 5 -> deleteBook();
                case 6 -> running = false;
                default -> System.out.println("Invalid choice. Enter 1 to 6.");
            }
        }

        scanner.close();
        System.out.println("Thank you for using the Library Management System.");
    }

    private void printMenu() {
        System.out.println("\n========== MENU ==========");
        System.out.println("1. Add book");
        System.out.println("2. View all books");
        System.out.println("3. Search book");
        System.out.println("4. Update book");
        System.out.println("5. Delete book");
        System.out.println("6. Exit");
    }

    private void addBook() {
        System.out.println("\n--- Add Book ---");

        String title = readRequiredText("Enter title: ");
        String author = readRequiredText("Enter author: ");
        String isbn = readRequiredText("Enter ISBN: ");
        int year = readInt("Enter publication year: ");

        if (year < MIN_YEAR || year > MAX_YEAR) {
            System.out.println("Invalid publication year.");
            return;
        }

        Book book = new Book(title, author, isbn, year);

        if (library.addBook(book)) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Book was not added. ISBN may already exist.");
        }
    }

    private void viewAllBooks() {
        List<Book> books = library.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n--- All Books ---");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void searchBooks() {
        System.out.println("\n1. Search by ISBN");
        System.out.println("2. Search by author");

        int choice = readInt("Choose search option: ");

        if (choice == 1) {
            String isbn = readRequiredText("Enter ISBN: ");
            Book book = library.findByIsbn(isbn);

            if (book == null) {
                System.out.println("Book not found.");
            } else {
                System.out.println(book);
            }
        } else if (choice == 2) {
            String author = readRequiredText("Enter author: ");
            List<Book> matches = library.findByAuthor(author);

            if (matches.isEmpty()) {
                System.out.println("No matching books found.");
            } else {
                for (Book book : matches) {
                    System.out.println(book);
                }
            }
        } else {
            System.out.println("Invalid search option.");
        }
    }

    private void updateBook() {
        String isbn = readRequiredText("Enter ISBN of the book to update: ");
        Book book = library.findByIsbn(isbn);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        String title = readRequiredText("Enter new title: ");
        String author = readRequiredText("Enter new author: ");
        int year = readInt("Enter new publication year: ");

        if (library.updateBook(isbn, title, author, year)) {
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Invalid update details.");
        }
    }

    private void deleteBook() {
        String isbn = readRequiredText("Enter ISBN of the book to delete: ");

        if (library.deleteBook(isbn)) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private String readRequiredText(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("This field cannot be empty.");
        }
    }
}
