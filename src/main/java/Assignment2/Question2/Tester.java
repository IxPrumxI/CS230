package Assignment2.Question2;

public class Tester {

    public static void main(String[] args) {
        Student student1 = new Student();
        Student student2 = new Student("Waleed AlAgeel", "S230041499");

        System.out.println("Name: " + student2.getName());
        System.out.println("ID: " + student2.getId());
    }
}
