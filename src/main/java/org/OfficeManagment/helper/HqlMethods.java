package org.OfficeManagment.helper;

public class HqlMethods {
    public static String  adminByUsername = "From Admin where username =:x";
    public static String employeeByEmail = "From Employee where email =:x";
    public static String getAllEmployee = "From Employee";
    public static String getAllClients = "From Client";
    public static String clientByEmail = "From Client where email =:x";
    public static String getAllProjects = "From Project";


}
