package Assignment2.Question2;

public class Student {
    private String name;
    private String id;

    public Student() {
        this.name = "Default Name";
        this.id = "S00000000";
    }

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}
