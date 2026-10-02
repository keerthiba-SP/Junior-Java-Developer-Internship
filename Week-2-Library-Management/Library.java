import java.util.ArrayList;

/**
 * Stores books and performs library operations.
 */
public class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public boolean addBook(Book book) {
        if (findBook(book.getIsbn()) != null) {
            return false; // ISBN already exists
        }
        books.add(book);
        return true;
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public Book findBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> findByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public boolean deleteBook(String isbn) {
        Book book = findBook(isbn);
        if (book == null) {
            return false;
        }
        books.remove(book);
        return true;
    }
}
