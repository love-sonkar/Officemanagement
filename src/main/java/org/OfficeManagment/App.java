package org.OfficeManagment;

import org.OfficeManagment.helper.FactoryHelper;
import org.OfficeManagment.helper.InputException;
import org.OfficeManagment.service.AdminService;
import org.OfficeManagment.service.ClientService;
import org.OfficeManagment.service.EmployeeService;
import org.hibernate.Session;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
       Session session= FactoryHelper.getSession().openSession();
        System.out.println("-----Office Management Project-----");
        Scanner ss = new Scanner(System.in);
        while (true) {
            System.out.println("[1] Login As Admin");
            System.out.println("[2] Login As Employee");
            System.out.println("[3] Client Module");
            System.out.println("[0] Exit");
            int input = new InputException().handleInputExceptionInt("Enter Your Option : ");
            switch (input){
                case 1:
                    System.out.print("Enter your Username : ");
                    String username = ss.nextLine().trim();
                    System.out.print("Enter Your Password : ");
                    String password = ss.nextLine().trim();
                    AdminService.loginMethod(username, password);
                    break;
                case 2:
                    System.out.print("Enter your Email : ");
                    String employeeEmail = ss.nextLine().trim();
                    System.out.print("Enter Your Password : ");
                    String employeePass = ss.nextLine().trim();
                    EmployeeService.employeeLogin(employeeEmail, employeePass);
                    break;
                case 3:
                    ClientService.clientService();
                    break;
                case 0 : break;
                default:
                    System.out.println("Wrong Input");
                    break;
            }
            if(input ==0){
                break;
            }
        }
        session.close();
    }
}
