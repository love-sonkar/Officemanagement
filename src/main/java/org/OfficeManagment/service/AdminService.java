package org.OfficeManagment.service;

import org.OfficeManagment.Function.Function;
import org.OfficeManagment.beanFiles.Admin;
import org.OfficeManagment.helper.FactoryHelper;
import org.OfficeManagment.helper.HqlMethods;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class AdminService {
    static public void loginMethod(String username, String password) {
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        try {
            Query q = ss.createQuery(HqlMethods.adminByUsername);
            q.setParameter("x", username);
            List<Admin> aa = q.list();
            if (aa.size() <= 0) {
                System.out.println("Invalid Credentials");
            } else {
                Admin admin = aa.get(0);
                if (!password.equals(admin.getPassword())) {
                    System.out.println("password Incorrect");
                } else {
                    System.out.println("Login Success");
                    Function.adminLogedIn(admin.getId());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        ss.close();
    }

    public static void changePassword(int adminid){
        Scanner scan=new Scanner(System.in);
        Session ss = FactoryHelper.getSession().openSession();
        Transaction tx = ss.beginTransaction();
        Admin currentAdmin = ss.get(Admin.class,adminid);
        System.out.print("Enter Your Current Password : ");
        String currentPass = scan.nextLine().trim();
        System.out.print("Enter Your New Password : ");
        String newPass = scan.nextLine().trim();
        if(!currentPass.equals(currentAdmin.getPassword())){
            System.out.println("Password Incorrect Please Try Again");
        }else {
            currentAdmin.setPassword(newPass);
            ss.persist(currentAdmin);
            System.out.println("Password Changed...");
        }
        tx.commit();
        ss.close();
    }
}
