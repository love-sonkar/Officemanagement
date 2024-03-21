package org.OfficeManagment.Function;

import org.OfficeManagment.helper.InputException;
import org.OfficeManagment.service.AdminService;
import org.OfficeManagment.service.ClientService;
import org.OfficeManagment.service.EmployeeService;

public class Function {

    public static void adminLogedIn(int adminId) {
        System.out.println("Logged As Admin");
        while (true) {
            System.out.println("[1] Show all Employee");
            System.out.println("[2] Show all Clients");
            System.out.println("[3] Show all Projects");
            System.out.println("[4] Change Admin Password");
            System.out.println("[0] Exit");
            int input = new InputException().handleInputExceptionInt("Enter Your Option: ");
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
                    System.out.println("all projects");
                    break;
                }
                case 4: {
                    AdminService.changePassword(adminId);
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

    public static void employeeLogedIn(int employeeId) {
        System.out.println("Logged As employee");
        while (true) {
            System.out.println("[1] Show My Projects");
            System.out.println("[2] Update Project Status");
            System.out.println("[3] Change Employee Password");
            System.out.println("[0] Exit");
            int input = new InputException().handleInputExceptionInt("Enter Your Option: ");
            switch (input) {
                case 1: {
                    System.out.println("[1] Show My Projects");
                    break;
                }
                case 2: {
                    System.out.println("[2] Update Project Status");
                    break;
                }
                case 3: {
                    EmployeeService.changePassword(employeeId);
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
