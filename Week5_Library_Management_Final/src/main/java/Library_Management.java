import java.util.List;
import java.util.Scanner;

/**
 * Command-line entry point for the Library Management System.
 *
 * The application integrates the Book entity, Library service, and
 * command-line interface into one deployable Java application.
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
        printHeader();
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
        System.out.println("Application closed.");
    }

    private void printHeader() {
        System.out.println("#################################");
        System.out.println("    Library Management System");
        System.out.println("#################################");
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
        String title = readRequiredText("Enter title: ");
        String author = readRequiredText("Enter author: ");
        String isbn = readRequiredText("Enter ISBN: ");
        int year = readInt("Enter publication year: ");

        if (year < MIN_YEAR || year > MAX_YEAR) {
            System.out.println("Invalid publication year.");
            return;
        }

        if (library.addBook(new Book(title, author, isbn, year))) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Book was not added. Check for a duplicate ISBN.");
        }
    }

    private void viewAllBooks() {
        List<Book> books = library.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void searchBooks() {
        System.out.println("1. Search by ISBN");
        System.out.println("2. Search by author");
        int choice = readInt("Choose search option: ");

        if (choice == 1) {
            Book book = library.findByIsbn(readRequiredText("Enter ISBN: "));
            System.out.println(book == null ? "Book not found." : book);
        } else if (choice == 2) {
            List<Book> matches =
                    library.findByAuthor(readRequiredText("Enter author: "));
            if (matches.isEmpty()) {
                System.out.println("No matching books found.");
            } else {
                matches.forEach(System.out::println);
            }
        } else {
            System.out.println("Invalid search option.");
        }
    }

    private void updateBook() {
        String isbn = readRequiredText("Enter ISBN of the book to update: ");

        if (library.findByIsbn(isbn) == null) {
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
        System.out.println(library.deleteBook(isbn)
                ? "Book deleted successfully."
                : "Book not found.");
    }

    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
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
