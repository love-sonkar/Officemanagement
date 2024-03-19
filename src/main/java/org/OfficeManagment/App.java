package org.OfficeManagment;

import org.OfficeManagment.helper.InputException;
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
            int input = new InputException().handleInputExceptionInt("Enter Your Option : ");
            if (input == 1) {
                System.out.print("Enter your Username : ");
                String username = ss.nextLine().trim();
                System.out.print("Enter Your Password : ");
                String password = ss.nextLine().trim();
                AdminService.loginMethod(username, password);
            } else if (input == 0) {
                break;
            }else {
                System.out.println("Wrong Input");
            }
        }
    }
}
