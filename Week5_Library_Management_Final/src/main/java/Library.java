import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Application service responsible for in-memory library operations.
 */
public class Library {
    private final List<Book> books = new ArrayList<>();

    public boolean addBook(Book book) {
        if (book == null || isBlank(book.getIsbn()) || findByIsbn(book.getIsbn()) != null) {
            return false;
        }
        books.add(book);
        return true;
    }

    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(books);
    }

    public Book findByIsbn(String isbn) {
        if (isBlank(isbn)) return null;
        String normalized = isbn.trim();
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(normalized)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> matches = new ArrayList<>();
        if (isBlank(author)) return matches;

        String query = author.trim().toLowerCase();
        for (Book book : books) {
            if (book.getAuthor() != null
                    && book.getAuthor().toLowerCase().contains(query)) {
                matches.add(book);
            }
        }
        return matches;
    }

    public boolean updateBook(String isbn, String title, String author, int year) {
        Book book = findByIsbn(isbn);
        if (book == null || isBlank(title) || isBlank(author)
                || year < 1 || year > 2026) {
            return false;
        }

        book.setTitle(title.trim());
        book.setAuthor(author.trim());
        book.setPublicationYear(year);
        return true;
    }

    public boolean deleteBook(String isbn) {
        Book book = findByIsbn(isbn);
        return book != null && books.remove(book);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
