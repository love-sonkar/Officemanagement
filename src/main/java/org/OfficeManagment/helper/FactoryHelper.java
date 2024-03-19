package org.OfficeManagment.helper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryHelper {

    static public SessionFactory sessionFac;
    static public Session session;

    static public SessionFactory getSession(){
        if(sessionFac==null) {
            sessionFac = new Configuration().configure().buildSessionFactory();
        }
        return sessionFac;
    }

    static public void closeSessionFac(){
        if(sessionFac.isOpen()){
            sessionFac.close();
        }
    }

}
