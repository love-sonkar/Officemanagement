package org.OfficeManagment.service;

import org.OfficeManagment.Function.Function;
import org.OfficeManagment.beanFiles.Admin;
import org.OfficeManagment.helper.FactoryHelper;
import org.OfficeManagment.helper.HqlMethods;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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

                //  Admin =
                Admin admin = aa.get(0);
                if (!password.equals(admin.getPassword())) {
                    System.out.println("password Incorrect");
                } else {
                    System.out.println("Login Success");
                    Function.adminLogedIn();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
