package library;

import java.io.*;
import java.util.*;

public class FileHandler {

    public static List<Book> loadBooks(String path) {
        List<Book> list = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) return list;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Book.fromFile(line));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveBooks(List<Book> books, String path) {
        try {
            File file = new File(path);

            // 🔥 auto-create folder
            file.getParentFile().mkdirs();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Book b : books) {
                bw.write(b.toFile());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Member> loadMembers(String path) {
        List<Member> list = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) return list;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Member.fromFile(line));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveMembers(List<Member> members, String path) {
        try {
            File file = new File(path);

            file.getParentFile().mkdirs();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Member m : members) {
                bw.write(m.toFile());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}