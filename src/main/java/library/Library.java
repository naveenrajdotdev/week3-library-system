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

    public void addBook(Book b) { books.add(b); }

    public void viewBooks() {
        if (books.isEmpty()) System.out.println("No books.");
        else books.forEach(System.out::println);
    }

    public void search(String key) {
        books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(key.toLowerCase()))
                .forEach(System.out::println);
    }

    public void deleteBook(String id) {
        if (books.removeIf(b -> b.getId().equals(id)))
            System.out.println("Deleted.");
        else
            System.out.println("Not found.");
    }

    public void addMember(Member m) { members.add(m); }

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
            System.out.println("Invalid.");
            return;
        }
        if (b.isBorrowed()) {
            System.out.println("Already borrowed.");
            return;
        }

        b.borrow(memberId);
        System.out.println("Borrowed.");
    }

    public void returnBook(String id) {
        Book b = findBook(id);
        if (b == null || !b.isBorrowed()) {
            System.out.println("Invalid.");
            return;
        }

        long late = ChronoUnit.DAYS.between(b.getDueDate(), LocalDate.now());
        if (late > 0) System.out.println("Fine: ₹" + (late * 10));

        b.returnBook();
        System.out.println("Returned.");
    }

    public void stats() {
        long borrowed = books.stream().filter(Book::isBorrowed).count();
        System.out.println("Books: " + books.size());
        System.out.println("Borrowed: " + borrowed);
        System.out.println("Members: " + members.size());
    }

    public List<Book> getBooks() { return books; }
    public List<Member> getMembers() { return members; }
}