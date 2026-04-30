package library;

import java.io.*;
import java.util.*;

public class FileHandler {

    // 📥 LOAD BOOKS
    public static List<Book> loadBooks(String path) {
        List<Book> list = new ArrayList<>();

        try {
            File file = new File(path);

            System.out.println("Loading from: " + file.getAbsolutePath());

            if (!file.exists()) {
                System.out.println("Books file not found. Starting fresh.");
                return list;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = br.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        list.add(Book.fromFile(line));
                    }
                }
            }

            System.out.println("Books loaded: " + list.size());

        } catch (Exception e) {
            System.out.println("Error loading books:");
            e.printStackTrace();
        }

        return list;
    }

    // 💾 SAVE BOOKS
    public static void saveBooks(List<Book> books, String path) {
        try {
            File file = new File(path);

            System.out.println("Saving to: " + file.getAbsolutePath());

            // ✅ Fix: avoid NullPointerException
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

                for (Book b : books) {
                    bw.write(b.toFile());
                    bw.newLine();
                }
            }

            System.out.println("Books saved: " + books.size());

        } catch (Exception e) {
            System.out.println("Error saving books:");
            e.printStackTrace();
        }
    }

    // 📥 LOAD MEMBERS
    public static List<Member> loadMembers(String path) {
        List<Member> list = new ArrayList<>();

        try {
            File file = new File(path);

            System.out.println("Loading from: " + file.getAbsolutePath());

            if (!file.exists()) {
                System.out.println("Members file not found. Starting fresh.");
                return list;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = br.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        list.add(Member.fromFile(line));
                    }
                }
            }

            System.out.println("Members loaded: " + list.size());

        } catch (Exception e) {
            System.out.println("Error loading members:");
            e.printStackTrace();
        }

        return list;
    }

    // 💾 SAVE MEMBERS
    public static void saveMembers(List<Member> members, String path) {
        try {
            File file = new File(path);

            System.out.println("Saving to: " + file.getAbsolutePath());

            // ✅ Fix: avoid NullPointerException
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

                for (Member m : members) {
                    bw.write(m.toFile());
                    bw.newLine();
                }
            }

            System.out.println("Members saved: " + members.size());

        } catch (Exception e) {
            System.out.println("Error saving members:");
            e.printStackTrace();
        }
    }
}