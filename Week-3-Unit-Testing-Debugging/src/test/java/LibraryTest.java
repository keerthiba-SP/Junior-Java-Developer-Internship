import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Book book(String title, String author, String isbn, int year) {
        return new Book(title, author, isbn, year);
    }

    @Test
    void addBookShouldAddValidBook() {
        Library library = new Library();
        assertTrue(library.addBook(book("Clean Code", "Robert Martin", "ISBN001", 2008)));
        assertEquals(1, library.getAllBooks().size());
    }

    @Test
    void addBookShouldRejectDuplicateIsbn() {
        Library library = new Library();
        library.addBook(book("Book A", "Author A", "ISBN001", 2020));
        assertFalse(library.addBook(book("Book B", "Author B", "ISBN001", 2021)));
        assertEquals(1, library.getAllBooks().size());
    }

    @Test
    void addBookShouldRejectDuplicateIsbnIgnoringCase() {
        Library library = new Library();
        library.addBook(book("Book A", "Author A", "isbn001", 2020));
        assertFalse(library.addBook(book("Book B", "Author B", "ISBN001", 2021)));
    }

    @Test
    void addBookShouldRejectNullOrBlankIsbn() {
        Library library = new Library();
        assertFalse(library.addBook(book("Book", "Author", null, 2020)));
        assertFalse(library.addBook(book("Book", "Author", "   ", 2020)));
        assertEquals(0, library.getAllBooks().size());
    }

    @Test
    void getAllBooksShouldReturnAllBooks() {
        Library library = new Library();
        library.addBook(book("A", "Author A", "1", 2000));
        library.addBook(book("B", "Author B", "2", 2001));
        assertEquals(2, library.getAllBooks().size());
    }

    @Test
    void findBookShouldBeCaseInsensitive() {
        Library library = new Library();
        Book expected = book("Java", "James", "ISBN-JAVA", 2020);
        library.addBook(expected);
        assertSame(expected, library.findBook("isbn-java"));
    }

    @Test
    void findBookShouldReturnNullForMissingOrNullIsbn() {
        Library library = new Library();
        assertNull(library.findBook("missing"));
        assertNull(library.findBook(null));
    }

    @Test
    void findByAuthorShouldFindPartialCaseInsensitiveMatches() {
        Library library = new Library();
        library.addBook(book("A", "Robert Martin", "1", 2008));
        library.addBook(book("B", "Martin Fowler", "2", 2018));
        library.addBook(book("C", "Other Author", "3", 2019));

        ArrayList<Book> result = library.findByAuthor("MARTIN");
        assertEquals(2, result.size());
    }

    @Test
    void findByAuthorShouldReturnEmptyForNullOrUnknownAuthor() {
        Library library = new Library();
        library.addBook(book("A", "Author", "1", 2020));
        assertTrue(library.findByAuthor(null).isEmpty());
        assertTrue(library.findByAuthor("Unknown").isEmpty());
    }

    @Test
    void updateBookShouldUpdateAllEditableFields() {
        Library library = new Library();
        library.addBook(book("Old Title", "Old Author", "ISBN001", 2000));

        assertTrue(library.updateBook("ISBN001", "New Title", "New Author", 2025));

        Book updated = library.findBook("ISBN001");
        assertEquals("New Title", updated.getTitle());
        assertEquals("New Author", updated.getAuthor());
        assertEquals(2025, updated.getYear());
    }

    @Test
    void updateBookShouldRejectMissingBook() {
        Library library = new Library();
        assertFalse(library.updateBook("MISSING", "Title", "Author", 2020));
    }

    @Test
    void updateBookShouldRejectInvalidData() {
        Library library = new Library();
        library.addBook(book("Title", "Author", "ISBN001", 2020));

        assertFalse(library.updateBook("ISBN001", "", "Author", 2020));
        assertFalse(library.updateBook("ISBN001", "Title", "Author", -1));
        assertFalse(library.updateBook("ISBN001", "Title", "Author", 2027));

        Book unchanged = library.findBook("ISBN001");
        assertEquals("Title", unchanged.getTitle());
        assertEquals(2020, unchanged.getYear());
    }

    @Test
    void deleteBookShouldDeleteExistingBook() {
        Library library = new Library();
        library.addBook(book("Title", "Author", "ISBN001", 2020));

        assertTrue(library.deleteBook("ISBN001"));
        assertNull(library.findBook("ISBN001"));
        assertTrue(library.getAllBooks().isEmpty());
    }

    @Test
    void deleteBookShouldReturnFalseForMissingOrNullBook() {
        Library library = new Library();
        assertFalse(library.deleteBook("MISSING"));
        assertFalse(library.deleteBook(null));
    }

    @Test
    void bookSettersShouldUpdateBookFields() {
        Book book = book("Old", "Old Author", "ISBN", 2000);
        book.setTitle("New");
        book.setAuthor("New Author");
        book.setYear(2024);

        assertEquals("New", book.getTitle());
        assertEquals("New Author", book.getAuthor());
        assertEquals(2024, book.getYear());
    }
}
