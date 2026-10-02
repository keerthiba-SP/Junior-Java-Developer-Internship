import java.util.ArrayList;
import java.util.List;

/**
 * Stores books and performs library CRUD/search operations.
 */
public class Library {
    private final ArrayList<Book> books = new ArrayList<>();
    private static final int MIN_YEAR = 0;
    private static final int MAX_YEAR = 2026;

    // CREATE
    public boolean addBook(Book book) {
        if (book == null || isBlank(book.getIsbn())) {
            return false;
        }
        if (findBook(book.getIsbn()) != null) {
            return false; // ISBN already exists
        }
        books.add(book);
        return true;
    }

    // READ
    public ArrayList<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public Book findBook(String isbn) {
        if (isbn == null) {
            return null;
        }

        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn.trim())) {
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> findByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();

        if (author == null) {
            return result;
        }

        String search = author.trim().toLowerCase();
        for (Book book : books) {
            if (book.getAuthor() != null
                    && book.getAuthor().toLowerCase().contains(search)) {
                result.add(book);
            }
        }
        return result;
    }

    // UPDATE
    public boolean updateBook(String isbn, String title, String author, int year) {
        Book book = findBook(isbn);
        if (book == null || isBlank(title) || isBlank(author)
                || year < MIN_YEAR || year > MAX_YEAR) {
            return false;
        }

        book.setTitle(title.trim());
        book.setAuthor(author.trim());
        book.setYear(year);
        return true;
    }

    // DELETE
    public boolean deleteBook(String isbn) {
        Book book = findBook(isbn);
        if (book == null) {
            return false;
        }
        books.remove(book);
        return true;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
