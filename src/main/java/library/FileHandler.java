package library;

import java.io.*;
import java.util.*;

public class FileHandler {

    public static List<Book> loadBooks(String path) {
        List<Book> list = new ArrayList<>();
        try {
            File f = new File(path);
            if (!f.exists()) return list;

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Book.fromFile(line));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error loading books.");
        }
        return list;
    }

    public static void saveBooks(List<Book> books, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (Book b : books) {
                bw.write(b.toFile());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error saving books.");
        }
    }

    public static List<Member> loadMembers(String path) {
        List<Member> list = new ArrayList<>();
        try {
            File f = new File(path);
            if (!f.exists()) return list;

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Member.fromFile(line));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error loading members.");
        }
        return list;
    }

    public static void saveMembers(List<Member> members, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (Member m : members) {
                bw.write(m.toFile());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error saving members.");
        }
    }
}