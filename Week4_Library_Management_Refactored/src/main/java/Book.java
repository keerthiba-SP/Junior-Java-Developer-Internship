/**
 * Represents one book in the library.
 *
 * Refactoring note:
 * - Fields are private to preserve encapsulation.
 * - The ISBN is final because it is the book's unique identifier and
 *   should not change during an update.
 */
public class Book {
    private String title;
    private String author;
    private final String isbn;
    private int publicationYear;

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Title: " + title
                + " | Author: " + author
                + " | ISBN: " + isbn
                + " | Year: " + publicationYear;
    }
}
