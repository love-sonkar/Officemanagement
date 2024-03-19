package org.OfficeManagment.Function;

import org.OfficeManagment.service.ClientService;
import org.OfficeManagment.service.EmployeeService;

import java.util.Scanner;

public class Function {

    public static void adminLogedIn() {
        System.out.println("Logged As Admin");
        Scanner ss = new Scanner(System.in);
        while (true) {
            System.out.println("[1] Show all Employee");
            System.out.println("[2] Show all Clients");
            System.out.println("[3] Create Invoice");
            System.out.println("[0] Exit");
            System.out.print("Enter Your Option : ");
            int input = ss.nextInt();
            switch (input) {
                case 1: {
                    EmployeeService.oprationLoop();
                    break;
                }
                case 2: {
                    ClientService.oprationLoop();
                    break;
                }
                case 3: {
                    System.out.println("invoice list");
                    break;
                }
                case 0: {
                    break;
                }
                default:
                    System.out.println("Wrong Input");
                    break;
            }
            if (input == 0) {
                break;
            }

        }

    }


}
