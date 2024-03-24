package org.OfficeManagment.Function;

import org.OfficeManagment.beanFiles.Client;
import org.OfficeManagment.beanFiles.Employee;
import org.OfficeManagment.beanFiles.Project;
import org.OfficeManagment.helper.FactoryHelper;
import org.OfficeManagment.helper.InputException;
import org.OfficeManagment.service.AdminService;
import org.OfficeManagment.service.ClientService;
import org.OfficeManagment.service.EmployeeService;
import org.OfficeManagment.service.ProjectService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Function {

    public static void adminLogedIn(int adminId) {
        System.out.println("<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<< Logged As Admin >>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>");
        while (true) {
            System.out.println("[1] Show all Employee");
            System.out.println("[2] Show all Clients");
            System.out.println("[3] Projects Module");
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
                    ProjectService.adminProjectOpration();
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

    public static void myProjects(int id){
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Employee c1 = ss.get(Employee.class, id);
        List<Project> projectList =  c1.getProject();
        if (projectList.size()<=0) {
            System.out.println("No projects");
        } else {
            System.out.println(" Id Title Description Status Client_Name");
            for (Project pp : projectList) {
                System.out.println(pp.getId() + " " + pp.getTitle() +" " + pp.getDescription() +" " + "Status : " + pp.getStatus() + " " + pp.getClient().getName() );
            }
        }
        tx.commit();
        ss.close();
    }

    public static void updateProjectStatus(int employeeId){
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Employee c1 = ss.get(Employee.class, employeeId);
        List<Project> projectList =  c1.getProject();
        myProjects(employeeId);
        int id = new InputException().handleInputExceptionInt("Enter project Id To Change Status : ");
        if(projectList.size()<=0){
            System.out.println("You don't have any project");
        }else {
            for(Project pp : projectList) {
                if(id != pp.getId()){
                    System.out.println("Wrong Input");
                }else {
                    Project currentProject = ss.get(Project.class,id);
                    while(true){
                        System.out.println("[1] In Progress");
                        System.out.println("[2] Completed");
                        System.out.println("[3] Backlog");
                        System.out.println("[0] Exit");
                        int input = new InputException().handleInputExceptionInt("Enter your option: ");
                        switch (input){
                            case 1: {
                                currentProject.setStatus("In Progress");
                                System.out.println("Project Status Updated To In Progress");
                                break;
                            }
                            case 2: {
                                currentProject.setStatus("Completed");
                                System.out.println("Project Status Updated To In Completed");
                                break;
                            }
                            case 3: {
                                currentProject.setStatus("Backlog");
                                System.out.println("Project Status Updated To Backlog");
                                break;
                            }
                            case 0 :break;
                        }
                        if(input ==0 || input ==1 || input ==2 || input ==3){
                            break;
                        }
                    }
                    ss.persist(currentProject);
                    tx.commit();
                }
            }
        }
        ss.close();
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
                    myProjects(employeeId);
                    break;
                }
                case 2: {
                    updateProjectStatus(employeeId);
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
