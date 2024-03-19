package org.OfficeManagment;

import org.OfficeManagment.service.AdminService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("-----Office Management Project-----");
        Scanner ss = new Scanner(System.in);
        while (true) {
            System.out.println("[1] Login As Admin");
            System.out.println("[0] Exit");
            System.out.print("Enter Your Option : ");
            int input = ss.nextInt();
            if (input == 1) {
                System.out.print("Enter your Username : ");
                String username = ss.next().trim();
                System.out.print("Enter Your Password : ");
                String password = ss.next().trim();
                AdminService.loginMethod(username, password);
            } else if (input == 0) {
                break;
            } else {
                System.out.println("Wrong Input");
            }

        }

    }
}
