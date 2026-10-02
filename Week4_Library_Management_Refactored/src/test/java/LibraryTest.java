import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Regression tests for the refactored Library class.
 * These tests help confirm that refactoring did not change expected behavior.
 */
class LibraryTest {
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }

    @Test
    void addBookStoresBook() {
        Book book = new Book("Java", "James", "101", 2024);

        assertTrue(library.addBook(book));
        assertEquals(1, library.getAllBooks().size());
    }

    @Test
    void duplicateIsbnIsRejected() {
        library.addBook(new Book("Java", "James", "101", 2024));

        assertFalse(library.addBook(new Book("Another", "Author", "101", 2025)));
        assertEquals(1, library.getAllBooks().size());
    }

    @Test
    void searchByIsbnIsCaseInsensitive() {
        Book book = new Book("Java", "James", "ISBN-101", 2024);
        library.addBook(book);

        assertSame(book, library.findByIsbn(" isbn-101 "));
    }

    @Test
    void searchByAuthorReturnsMatchingBooks() {
        library.addBook(new Book("Java", "James Gosling", "101", 2024));
        library.addBook(new Book("Python", "Guido van Rossum", "102", 1991));

        List<Book> results = library.findByAuthor("james");

        assertEquals(1, results.size());
        assertEquals("101", results.get(0).getIsbn());
    }

    @Test
    void updateChangesBookDetails() {
        library.addBook(new Book("Old", "Old Author", "101", 2020));

        assertTrue(library.updateBook("101", "New", "New Author", 2025));

        Book book = library.findByIsbn("101");
        assertEquals("New", book.getTitle());
        assertEquals("New Author", book.getAuthor());
        assertEquals(2025, book.getPublicationYear());
    }

    @Test
    void invalidUpdateIsRejected() {
        library.addBook(new Book("Old", "Author", "101", 2020));

        assertFalse(library.updateBook("101", "", "Author", 2025));
        assertEquals("Old", library.findByIsbn("101").getTitle());
    }

    @Test
    void deleteRemovesBook() {
        library.addBook(new Book("Java", "James", "101", 2024));

        assertTrue(library.deleteBook("101"));
        assertNull(library.findByIsbn("101"));
    }

    @Test
    void deletingMissingBookReturnsFalse() {
        assertFalse(library.deleteBook("missing"));
    }
}
