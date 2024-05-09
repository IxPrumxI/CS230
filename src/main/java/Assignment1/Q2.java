package Assignment1;

import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name:");
        String lastName = scanner.nextLine();

        System.out.printf("First Name: %s\n", firstName.toUpperCase());
        System.out.printf("Last Name: %s\n", lastName.toUpperCase());
    }
}
