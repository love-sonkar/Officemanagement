package org.OfficeManagment.service;

import org.OfficeManagment.beanFiles.Employee;
import org.OfficeManagment.helper.FactoryHelper;
import org.OfficeManagment.helper.HqlMethods;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class EmployeeService {

    public static void listEmployee(){
        System.out.println("=== Employee List ====");
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx =ss.beginTransaction();
        Query q = ss.createQuery(HqlMethods.getAllEmployee);
        List<Employee> elist = q.list();
        if(elist.size()<=0){
            System.out.println("No Employee found Please Add");
        }else {
            System.out.println("  Id    Name          Email             Number          Department      Role");
            for(Employee ee : elist){
                System.out.println("  "+ ee.getId() + "    " + ee.getName() + "    " +ee.getEmail() + "   "+ ee.getNumber() +"   " + ee.getDepartment() + "  " + ee.getRole());
            }
        }
        tx.commit();
        ss.close();
    }

    public static void createEmployee(){
        Scanner input = new Scanner(System.in);
        System.out.println("Add Employee");
        System.out.print("Enter name : ");
        String name = input.next().trim();
        System.out.print("Enter Email : ");
        String email = input.next().trim();
        System.out.print("Enter Department : ");
        String department = input.next().trim();
        System.out.print("Enter Role : ");
        String role = input.next().trim();
        System.out.print("Enter Number : ");
        Long number = input.nextLong();
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx=ss.beginTransaction();
        Employee emp = new Employee(name,email,number,department,role);
        ss.persist(emp);
        tx.commit();
        ss.close();
        System.out.println("Added Employee");

    }

    public static void deleteEmployee(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Id to delete the employee : ");
        int id = input.nextInt();
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx=ss.beginTransaction();
        Employee em1= ss.get(Employee.class,id);
        if(em1==null){
            System.out.println("Wrong Employee Id");
        }else{
            System.out.println("User " + em1.getName() + " Deleted");
            ss.delete(em1);
        }
        tx.commit();
        ss.close();

    }

    public static void EditEmployee(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Id To Edit Employee : ");
        int id = input.nextInt();
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx=ss.beginTransaction();
        Employee em1 = ss.get(Employee.class,id);
        if(em1==null){
            System.out.println();
            System.out.println("Wrong Id Employee not Found");
            System.out.println();
        }else {
            System.out.print("Enter name : ");
            String name = input.next().trim();
            System.out.print("Enter Email : ");
            String email = input.next().trim();
            System.out.print("Enter Department : ");
            String department = input.next().trim();
            System.out.print("Enter Role : ");
            String role = input.next().trim();
            System.out.print("Enter Number : ");
            Long number = input.nextLong();
            em1.setName(name);
            em1.setEmail(email);
            em1.setNumber(number);
            em1.setDepartment(department);
            em1.setRole(role);
            ss.update(em1);
            System.out.println("SuccessFully Updated Employee");
        }
        tx.commit();
        ss.close();

    }

    public static void oprationLoop(){
        while(true){
             listEmployee();
            Scanner ss =new Scanner(System.in);
            System.out.println("[1] Add Employee ");
            System.out.println("[2] Edit Employee");
            System.out.println("[3] Delete Employee");
            System.out.println("[0] Exit");
            System.out.print("Enter Option : ");
            int input = ss.nextInt();
            switch (input){
                case 1:{
                    createEmployee();
                    break;
                }
                case 2: {
                    EditEmployee();
                    break;
                }
                case 3 : {
                    deleteEmployee();
                    break;
                }
                case 0: {
                    break;
                }
                default:
                    System.out.println("Wrong Input");
                    break;
            }
            if(input==0){
                break;
            }
        }
    }
}
