import java.util.ArrayList;
import java.util.Scanner;

/**
 * Menu-driven Library Management System.
 */
public class Library_Management {
    private static final Scanner sc = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {
        System.out.println("#################################");
        System.out.println("    Library Management System");
        System.out.println("#################################");

        boolean running = true;

        while (running) {
            System.out.println("\nThe Menu");
            System.out.println("1. Add book");
            System.out.println("2. View books");
            System.out.println("3. Update book");
            System.out.println("4. Delete book");
            System.out.println("5. Exit");

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> updateBook();
                case 4 -> deleteBook();
                case 5 -> {
                    System.out.println("Thank you for using the Library Management System.");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
        sc.close();
    }

    private static void addBook() {
        System.out.println("\nEnter book details");
        String title = readText("Book title: ");
        String author = readText("Author: ");
        String isbn = readText("ISBN: ");
        int year = readInt("Publication year: ");

        if (year < 0 || year > 2026) {
            System.out.println("Please enter a valid publication year.");
            return;
        }

        if (library.addBook(new Book(title, author, isbn, year))) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("A book with this ISBN already exists or the ISBN is invalid.");
        }
    }

    private static void viewBooks() {
        System.out.println("\n1. View all books");
        System.out.println("2. Search by ISBN");
        System.out.println("3. Search by author");
        int option = readInt("Choose an option: ");

        if (option == 1) {
            ArrayList<Book> books = library.getAllBooks();
            if (books.isEmpty()) {
                System.out.println("No books are available.");
            } else {
                System.out.println("\nBooks in the library:");
                for (Book book : books) System.out.println(book);
            }
        } else if (option == 2) {
            String isbn = readText("Enter ISBN: ");
            Book book = library.findBook(isbn);
            System.out.println(book == null ? "Book not found." : book);
        } else if (option == 3) {
            String author = readText("Enter author name: ");
            ArrayList<Book> matches = library.findByAuthor(author);
            if (matches.isEmpty()) {
                System.out.println("No books found for that author.");
            } else {
                for (Book book : matches) System.out.println(book);
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void updateBook() {
        String isbn = readText("Enter the ISBN of the book to update: ");
        Book book = library.findBook(isbn);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        System.out.println("Current details: " + book);
        System.out.println("Enter new details. Press Enter to keep the current value.");

        System.out.print("New title [" + book.getTitle() + "]: ");
        String title = sc.nextLine().trim();
        if (title.isEmpty()) title = book.getTitle();

        System.out.print("New author [" + book.getAuthor() + "]: ");
        String author = sc.nextLine().trim();
        if (author.isEmpty()) author = book.getAuthor();

        System.out.print("New publication year [" + book.getYear() + "]: ");
        String yearText = sc.nextLine().trim();
        int year = book.getYear();

        if (!yearText.isEmpty()) {
            try {
                year = Integer.parseInt(yearText);
            } catch (NumberFormatException e) {
                System.out.println("Year must be a number. Keeping the old year.");
            }
        }

        if (library.updateBook(isbn, title, author, year)) {
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Invalid update data. Existing details were kept.");
        }
    }

    private static void deleteBook() {
        String isbn = readText("Enter the ISBN of the book to delete: ");
        System.out.println(library.deleteBook(isbn)
                ? "Book deleted successfully."
                : "Book not found.");
    }

    private static String readText(String message) {
        String value;
        do {
            System.out.print(message);
            value = sc.nextLine().trim();
            if (value.isEmpty()) System.out.println("This field cannot be empty.");
        } while (value.isEmpty());
        return value;
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String value = sc.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
