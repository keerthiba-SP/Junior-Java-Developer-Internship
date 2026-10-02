public class VerificationRunner {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        run("Add valid book", VerificationRunner::addValidBook);
        run("Reject duplicate ISBN", VerificationRunner::duplicateIsbn);
        run("Reject duplicate ISBN ignoring case", VerificationRunner::duplicateIsbnCase);
        run("Find ISBN case-insensitively", VerificationRunner::findBook);
        run("Search author", VerificationRunner::searchAuthor);
        run("Update book", VerificationRunner::updateBook);
        run("Reject invalid update", VerificationRunner::invalidUpdate);
        run("Delete book", VerificationRunner::deleteBook);
        run("Reject missing delete", VerificationRunner::missingDelete);
        run("Reject blank ISBN", VerificationRunner::blankIsbn);

        System.out.println("\nVerification result: " + passed + " passed, " + failed + " failed.");
        if (failed > 0) System.exit(1);
    }

    private static void run(String name, Runnable test) {
        try {
            test.run();
            passed++;
            System.out.println("[PASS] " + name);
        } catch (AssertionError e) {
            failed++;
            System.out.println("[FAIL] " + name + " -> " + e.getMessage());
        }
    }

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    private static Book b(String title, String author, String isbn, int year) {
        return new Book(title, author, isbn, year);
    }

    private static void addValidBook() {
        Library l = new Library();
        check(l.addBook(b("Java", "Author", "1", 2020)), "valid book was not added");
        check(l.getAllBooks().size() == 1, "book count should be 1");
    }

    private static void duplicateIsbn() {
        Library l = new Library();
        l.addBook(b("A", "A", "1", 2020));
        check(!l.addBook(b("B", "B", "1", 2021)), "duplicate ISBN accepted");
    }

    private static void duplicateIsbnCase() {
        Library l = new Library();
        l.addBook(b("A", "A", "isbn", 2020));
        check(!l.addBook(b("B", "B", "ISBN", 2021)), "case variant duplicate accepted");
    }

    private static void findBook() {
        Library l = new Library();
        Book b = b("Java", "Author", "ISBN1", 2020);
        l.addBook(b);
        check(l.findBook("isbn1") == b, "book was not found");
    }

    private static void searchAuthor() {
        Library l = new Library();
        l.addBook(b("A", "Robert Martin", "1", 2020));
        l.addBook(b("B", "Other", "2", 2020));
        check(l.findByAuthor("martin").size() == 1, "author search failed");
    }

    private static void updateBook() {
        Library l = new Library();
        l.addBook(b("Old", "Old", "1", 2020));
        check(l.updateBook("1", "New", "New Author", 2025), "update failed");
        Book b = l.findBook("1");
        check(b.getTitle().equals("New"), "title not updated");
        check(b.getYear() == 2025, "year not updated");
    }

    private static void invalidUpdate() {
        Library l = new Library();
        l.addBook(b("Old", "Author", "1", 2020));
        check(!l.updateBook("1", "", "Author", 2020), "invalid update accepted");
    }

    private static void deleteBook() {
        Library l = new Library();
        l.addBook(b("A", "A", "1", 2020));
        check(l.deleteBook("1"), "delete failed");
        check(l.findBook("1") == null, "deleted book still exists");
    }

    private static void missingDelete() {
        Library l = new Library();
        check(!l.deleteBook("missing"), "missing delete should be false");
    }

    private static void blankIsbn() {
        Library l = new Library();
        check(!l.addBook(b("A", "A", " ", 2020)), "blank ISBN accepted");
    }
}
