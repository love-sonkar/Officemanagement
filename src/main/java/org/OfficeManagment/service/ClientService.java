package org.OfficeManagment.service;

import org.OfficeManagment.beanFiles.Client;
import org.OfficeManagment.beanFiles.Project;
import org.OfficeManagment.helper.FactoryHelper;
import org.OfficeManagment.helper.HqlMethods;
import org.OfficeManagment.helper.InputException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class ClientService {

    public static void ClientList() {
        System.out.println("=== Client List ====");
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Query q = ss.createQuery(HqlMethods.getAllClients);
        List<Client> cList = q.list();
        if (cList.size() <= 0) {
            System.out.println("No Clients found Please Add");
        } else {
            System.out.println("  Id    Name    Email    Number");
            for (Client ee : cList) {
                System.out.println("  " + ee.getId() + "    " + ee.getName() + "    " + ee.getEmail() + "   " + ee.getNumber());
            }
        }
        tx.commit();
        ss.close();

    }

    public static void addClient() {
        Scanner input = new Scanner(System.in);
        System.out.println("Add Client");
        System.out.print("Enter name : ");
        String name = input.nextLine().trim();
        System.out.print("Enter Email : ");
        String email = input.nextLine().trim();
        Long number = new InputException().handleInputExceptionLong("Enter Number : ");
        System.out.print("Enter Password : ");
        String password = input.nextLine().trim();
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Client cl = new Client(name, email, number, password);
        ss.persist(cl);
        tx.commit();
        ss.close();
        System.out.println("Added Client");
    }

    public static void updateClient() {
        Scanner input = new Scanner(System.in);
        int id = new InputException().handleInputExceptionInt("Enter Client Id to Edit : ");
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Client c1 = ss.get(Client.class, id);
        if (c1 == null) {
            System.out.println();
            System.out.println("Wrong Id Client not Found");
            System.out.println();
        } else {
            System.out.print("Enter name : ");
            String name = input.nextLine().trim();
            System.out.print("Enter Email : ");
            String email = input.nextLine().trim();
            Long number = new InputException().handleInputExceptionLong("Enter Number : ");
            c1.setName(name);
            c1.setEmail(email);
            c1.setNumber(number);
            ss.update(c1);
            System.out.println("SuccessFully Updated Client");
        }
        tx.commit();
        ss.close();

    }

    public static void deletClient() {
        int id = new InputException().handleInputExceptionInt("Enter Id to delete the Client : ");
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Client c1 = ss.get(Client.class, id);
        if (c1 == null) {
            System.out.println("Wrong Client Id");
        } else {
            System.out.println("User " + c1.getName() + " Deleted");
            ss.delete(c1);
        }
        tx.commit();
        ss.close();
    }

    public static void deleteAccount(int id) {
        while (true) {
            System.out.println("[1] Confirm ");
            System.out.println("[0] Exit ");
            int input = new InputException().handleInputExceptionInt("Enter Your option : ");
            if (input == 1) {
                Session ss = FactoryHelper.getSession().openSession();
                Transaction tx = ss.beginTransaction();
                Client c1 = ss.get(Client.class, id);
                System.out.println("Client " + c1.getName() + " Deleted");
                ss.delete(c1);
                tx.commit();
                ss.close();
            } else if (input == 0) {
                System.out.println("Opration Failed");
                break;
            } else {
                System.out.println("Wrong Input");
            }
        }
    }


    public static void clientLogin() {
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your Email : ");
        String email = scan.nextLine().trim();
        System.out.print("Enter your Password : ");
        String password = scan.nextLine().trim();
        try {
            Query q = ss.createQuery(HqlMethods.clientByEmail);
            q.setParameter("x", email);
            List<Client> client = q.list();
            if (client.size() <= 0) {
                System.out.println("Invalid Credentials");
            } else {
                Client clientData = client.get(0);
                if (!password.equals(clientData.getPassword())) {
                    System.out.println("password Incorrect");
                } else {
                    System.out.println("Login Success");
                    clientLoggedIn(clientData.getId());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        ss.close();
    }

    public static void changePassword(int clientId) {
        Scanner scan = new Scanner(System.in);
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Client currentClient = ss.get(Client.class, clientId);
        System.out.print("Enter Your Current Password : ");
        String currentPass = scan.nextLine().trim();
        System.out.print("Enter Your New Password : ");
        String newPass = scan.nextLine().trim();
        if (!currentPass.equals(currentClient.getPassword())) {
            System.out.println("Password Incorrect Please Try Again");
        } else {
            currentClient.setPassword(newPass);
            ss.persist(currentClient);
            System.out.println("Password Changed...");
        }
        tx.commit();
        ss.close();
    }

    public static void clientProjectOpration(){

    }

    public static void showClientProject(int id){
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Client c1 = ss.get(Client.class, id);
        List<Project> projectList =  c1.getProject();
        if (projectList.size()<=0) {
            System.out.println("No projects");
        } else {
            System.out.println(" Id Title Description Status Client_Name");
            for (Project pp : projectList) {
                System.out.println(pp.getId() + " " + pp.getTitle() +" " + pp.getDescription() +" " + "Status : " + pp.getStatus() );
            }
            clientProjectOpration();
        }
        tx.commit();
        ss.close();
    }

    public static void clientLoggedIn(int id) {
        System.out.println("Logged As Client");
        while (true) {
            System.out.println("[1] Create Project");
            System.out.println("[2] Show My Projects");
            System.out.println("[3] Change Password ");
            System.out.println("[4] Delete My Account ");
            System.out.println("[0] Exit");
            int input = new InputException().handleInputExceptionInt("Enter Your Option: ");
            switch (input) {
                case 1: {
                    ProjectService.addProjcet(id);
                    break;
                }
                case 2: {
                    showClientProject(id);
                    break;
                }
                case 3: {
                    changePassword(id);
                    break;
                }
                case 4:
                    deleteAccount(id);
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


    public static void clientService() {
        while (true) {
            Scanner ss = new Scanner(System.in);
            System.out.println("[1] Create Account ");
            System.out.println("[2] Login Client ");
            System.out.println("[0] Exit");
            int input = new InputException().handleInputExceptionInt("Enter Option : ");
            switch (input) {
                case 1: {
                    addClient();
                    break;
                }
                case 2: {
                    clientLogin();
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

    public static void oprationLoop() {
        while (true) {
            ClientList();
            System.out.println("Client List");
            Scanner ss = new Scanner(System.in);
            System.out.println("[1] Add Client ");
            System.out.println("[2] Edit Client");
            System.out.println("[3] Delete Client");
            System.out.println("[0] Exit");
            int input = new InputException().handleInputExceptionInt("Enter Option : ");
            switch (input) {
                case 1: {
                    addClient();
                    break;
                }
                case 2: {
                    updateClient();
                    break;
                }
                case 3: {
                    deletClient();
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
