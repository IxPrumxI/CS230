import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        System.out.println("Welcome " + name);

        System.out.println("Please, enter a number:");
        int number = scanner.nextInt();

        if(number > 0) System.out.println("Positive number.");
        else if(number == 0) System.out.println("Number zero.");
//        else if(number < 0) System.out.println("Negative number.");
        else System.out.println("Negative number."); // Considering we covered 2 of the cases the last one will not need any checks.
    }
}
