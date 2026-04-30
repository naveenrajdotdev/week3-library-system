package library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Library {

    private List<Book> books;
    private List<Member> members;

    public Library(List<Book> books, List<Member> members) {
        this.books = books;
        this.members = members;
    }

    public void addBook(Book b) {
        books.add(b);
        System.out.println("Book added.");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books.");
            return;
        }
        books.forEach(System.out::println);
    }

    public void search(String key) {
        books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(key.toLowerCase()))
                .forEach(System.out::println);
    }

    // ⭐ DELETE BOOK
    public void deleteBook(String id) {
        boolean removed = books.removeIf(b -> b.getId().equals(id));
        if (removed) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void addMember(Member m) {
        members.add(m);
        System.out.println("Member added.");
    }

    private Book findBook(String id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    private Member findMember(String id) {
        return members.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    public void borrow(String bookId, String memberId) {
        Book b = findBook(bookId);
        Member m = findMember(memberId);

        if (b == null || m == null) {
            System.out.println("Invalid book/member.");
            return;
        }

        if (b.isBorrowed()) {
            System.out.println("Already borrowed.");
            return;
        }

        b.borrow(memberId);
        System.out.println("Borrowed successfully.");
    }

    public void returnBook(String bookId) {
        Book b = findBook(bookId);

        if (b == null || !b.isBorrowed()) {
            System.out.println("Invalid return.");
            return;
        }

        long late = ChronoUnit.DAYS.between(b.getDueDate(), LocalDate.now());
        if (late > 0) {
            System.out.println("Fine: ₹" + (late * 10));
        }

        b.returnBook();
        System.out.println("Returned.");
    }

    public void stats() {
        long borrowed = books.stream().filter(Book::isBorrowed).count();
        System.out.println("Total Books: " + books.size());
        System.out.println("Borrowed: " + borrowed);
        System.out.println("Members: " + members.size());
    }

    public List<Book> getBooks() { return books; }
    public List<Member> getMembers() { return members; }
}