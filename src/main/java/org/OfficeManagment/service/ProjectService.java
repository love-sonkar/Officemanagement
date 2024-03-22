package org.OfficeManagment.service;

import org.OfficeManagment.beanFiles.Client;
import org.OfficeManagment.beanFiles.Employee;
import org.OfficeManagment.beanFiles.Project;
import org.OfficeManagment.helper.FactoryHelper;
import org.OfficeManagment.helper.HqlMethods;
import org.OfficeManagment.helper.InputException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjectService {

    public static void addProjcet(int clientId){
        Scanner input = new Scanner(System.in);
        System.out.println("Add Project");
        System.out.print("Enter Title : ");
        String title = input.nextLine().trim();
        System.out.print("Enter Description : ");
        String desc = input.nextLine().trim();
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Client currentClient = ss.get(Client.class,clientId);
        Project pro = new Project(title,desc,currentClient);
        List<Project> projectList = new ArrayList<>();
        projectList.add(pro);
        currentClient.setProject(projectList);
        ss.persist(pro);
        tx.commit();
        ss.close();
        System.out.println("Project Added");
    }

    public static void projectList() {
        System.out.println("<<--------------Project List------------->>");
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Query q = ss.createQuery(HqlMethods.getAllProjects);
        List<Project> projectList = q.list();
        if (projectList.size() <= 0) {
            System.out.println("No Projects Found");
        } else {
            System.out.println(" Id Title Description Status Client_Name");
            for (Project pp : projectList) {
                System.out.println(pp.getId() + " " + pp.getTitle() +" " + pp.getDescription() +" " + "Status : " + pp.getStatus() + " Project By " + pp.getClient().getName());
            }
        }
        tx.commit();
        ss.close();
    }

    public static void deleteProject(){
        int id = new InputException().handleInputExceptionInt("Enter Id to delete Project : ");
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Project project = ss.get(Project.class, id);
        if (project == null) {
            System.out.println("Wrong Project Id");
        } else {
            System.out.println("Project " + project.getTitle() + " Deleted");
            ss.delete(project);
        }
        tx.commit();
        ss.close();
    }

    public static void changeStatus(){
        int id = new InputException().handleInputExceptionInt("Enter Id To Approve project : ");
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Project project = ss.get(Project.class, id);
        if (project == null) {
            System.out.println("Wrong Project Id");
        } else {
            System.out.println("Project " + project.getTitle() + " Deleted");
            project.setStatus("Approved");
            ss.persist(project);
            System.out.println("<<------------ Project Status Changed ------------>>");
        }
        tx.commit();
        ss.close();
    }

    public static void assignProject(){
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        int projectId = new InputException().handleInputExceptionInt("Enter project id To Assign : ");
        Project project = ss.get(Project.class, projectId);
        if (project == null) {
            System.out.println("Wrong Project Id");
        } else {
            EmployeeService.listEmployee();
            int employeeId = new InputException().handleInputExceptionInt("Enter Employee id To Assign : ");
            Employee emp = ss.get(Employee.class,employeeId);
            if(emp==null){
                System.out.println("Employee Not Found");
            }else{
                List<Project> pList = new ArrayList<>();
                pList.add(project);
                emp.setProject(pList);
                project.setEmployee(emp);
                ss.persist(project);
                System.out.println("<<<<< Project Assign to " + emp.getName() + " >>>>>>>");
            }
        }
        tx.commit();
        ss.close();
    }

    public static void adminProjectOpration(){
        System.out.println("Project Module");
        while (true) {
            projectList();
            System.out.println("[1] Approved Project ");
            System.out.println("[2] Delete Project");
            System.out.println("[3] Assign Project To Employee ");
            System.out.println("[0] Exit");
            int input = new InputException().handleInputExceptionInt("Enter Your Option: ");
            switch (input) {
                case 1: {
                    changeStatus();
                    break;
                }
                case 2: {
                    deleteProject();
                    break;
                }
                case 3: {
                    assignProject();
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
