package main;

import java.util.Scanner;

public class Authentication {
	private static User user = new User("hatio", "hatio123");

    public static boolean login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" User Login ");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (user.getUsername().equals(username) && user.validatePassword(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

}
