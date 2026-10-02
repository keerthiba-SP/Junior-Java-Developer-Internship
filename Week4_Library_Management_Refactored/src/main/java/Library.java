import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Responsible only for storing books and performing book operations.
 *
 * Refactoring and optimization:
 * - Keeps all collection logic in one class (single responsibility).
 * - Uses a List interface instead of exposing ArrayList directly.
 * - Duplicate ISBN checking is centralized in findByIsbn().
 * - Returns a read-only view so callers cannot directly modify internal data.
 */
public class Library {
    private final List<Book> books = new ArrayList<>();

    public boolean addBook(Book book) {
        if (book == null || isBlank(book.getIsbn())) {
            return false;
        }

        if (findByIsbn(book.getIsbn()) != null) {
            return false;
        }

        books.add(book);
        return true;
    }

    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(books);
    }

    public Book findByIsbn(String isbn) {
        if (isBlank(isbn)) {
            return null;
        }

        String normalizedIsbn = isbn.trim();
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(normalizedIsbn)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> matches = new ArrayList<>();

        if (isBlank(author)) {
            return matches;
        }

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
