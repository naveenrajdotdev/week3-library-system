package library;

import java.time.LocalDate;

public class Book {
    private String id, title, author;
    private boolean borrowed;
    private String borrowedBy;
    private LocalDate dueDate;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public boolean isBorrowed() { return borrowed; }
    public LocalDate getDueDate() { return dueDate; }

    public void borrow(String memberId) {
        borrowed = true;
        borrowedBy = memberId;
        dueDate = LocalDate.now().plusDays(7);
    }

    public void returnBook() {
        borrowed = false;
        borrowedBy = null;
        dueDate = null;
    }

    public String toFile() {
        return id + "|" + title + "|" + author + "|" + borrowed + "|" +
                (borrowedBy == null ? "null" : borrowedBy) + "|" +
                (dueDate == null ? "null" : dueDate);
    }

    public static Book fromFile(String line) {
        String[] p = line.split("\\|");
        Book b = new Book(p[0], p[1], p[2]);
        b.borrowed = Boolean.parseBoolean(p[3]);
        if (!p[4].equals("null")) b.borrowedBy = p[4];
        if (!p[5].equals("null")) b.dueDate = LocalDate.parse(p[5]);
        return b;
    }

    public String toString() {
        return id + " | " + title + " | " + author + " | " +
                (borrowed ? "Borrowed (Due: " + dueDate + ")" : "Available");
    }
}