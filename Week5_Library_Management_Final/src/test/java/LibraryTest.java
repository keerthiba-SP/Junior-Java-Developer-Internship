import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }

    @Test
    void addBookStoresBook() {
        assertTrue(library.addBook(new Book("Java", "James", "101", 2024)));
        assertEquals(1, library.getAllBooks().size());
    }

    @Test
    void duplicateIsbnIsRejected() {
        library.addBook(new Book("Java", "James", "101", 2024));
        assertFalse(library.addBook(new Book("Other", "Author", "101", 2025)));
        assertEquals(1, library.getAllBooks().size());
    }

    @Test
    void searchUpdateAndDeleteWorkTogether() {
        library.addBook(new Book("Old", "Author", "101", 2020));

        assertNotNull(library.findByIsbn("101"));
        assertTrue(library.updateBook("101", "New", "New Author", 2025));
        assertEquals("New", library.findByIsbn("101").getTitle());

        assertTrue(library.deleteBook("101"));
        assertNull(library.findByIsbn("101"));
    }

    @Test
    void authorSearchFindsMatches() {
        library.addBook(new Book("Java", "James Gosling", "101", 2024));
        library.addBook(new Book("Python", "Guido van Rossum", "102", 1991));

        assertEquals(1, library.findByAuthor("james").size());
    }

    @Test
    void invalidUpdateDoesNotChangeBook() {
        library.addBook(new Book("Original", "Author", "101", 2020));

        assertFalse(library.updateBook("101", "", "Author", 2025));
        assertEquals("Original", library.findByIsbn("101").getTitle());
    }
}
