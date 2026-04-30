package library;

import java.util.*;

public class Main {

    static final String BOOK_FILE = "data/books.txt";
    static final String MEMBER_FILE = "data/members.txt";

    public static void main(String[] args) {

        List<Book> books = FileHandler.loadBooks(BOOK_FILE);
        List<Member> members = FileHandler.loadMembers(MEMBER_FILE);

        Library lib = new Library(books, members);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Add Book 2.View Books 3.Search Books 4.Delete Book 5.Register Member 6.Borrow Book 7.Return Book 8.Statistics 9.Exit");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Title: ");
                    String t = sc.nextLine();
                    System.out.print("Author: ");
                    String a = sc.nextLine();
                    lib.addBook(new Book(id, t, a));
                    FileHandler.saveBooks(lib.getBooks(), BOOK_FILE);
                    break;

                case 2: lib.viewBooks(); break;

                case 3:
                    System.out.print("Search: ");
                    lib.search(sc.nextLine());
                    break;

                case 4:
                    System.out.print("ID: ");
                    lib.deleteBook(sc.nextLine());
                    FileHandler.saveBooks(lib.getBooks(), BOOK_FILE);
                    break;

                case 5:
                    System.out.print("ID: ");
                    String mid = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    lib.addMember(new Member(mid, name));
                    FileHandler.saveMembers(lib.getMembers(), MEMBER_FILE);
                    break;

                case 6:
                    System.out.print("Book ID: ");
                    String bid = sc.nextLine();
                    System.out.print("Member ID: ");
                    String mem = sc.nextLine();
                    lib.borrow(bid, mem);
                    FileHandler.saveBooks(lib.getBooks(), BOOK_FILE);
                    break;

                case 7:
                    System.out.print("Book ID: ");
                    lib.returnBook(sc.nextLine());
                    FileHandler.saveBooks(lib.getBooks(), BOOK_FILE);
                    break;

                case 8:
                    lib.stats();
                    break;

                case 9:
                    FileHandler.saveBooks(lib.getBooks(), BOOK_FILE);
                    FileHandler.saveMembers(lib.getMembers(), MEMBER_FILE);
                    System.out.println("Saved & Exit.");
                    return;
            }
        }
    }
}