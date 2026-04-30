package library;

public class Member {
    private String id, name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }

    public String toFile() {
        return id + "|" + name;
    }

    public static Member fromFile(String line) {
        String[] p = line.split("\\|");
        return new Member(p[0], p[1]);
    }

    public String toString() {
        return id + " | " + name;
    }
}