package org.OfficeManagment.service;

import java.util.Scanner;

public class ClientService {

    public static void ClientList(){}
    public static void addClient(){}
    public static void updateClient(){}
    public static void deletClient(){}
    public static void oprationLoop(){
        while(true){
            ClientList();
            System.out.println("Client List");
              Scanner ss =new Scanner(System.in);
            System.out.println("[1] Add Client ");
            System.out.println("[2] Edit Client");
            System.out.println("[3] Delete Client");
            System.out.println("[0] Exit");
            System.out.print("Enter Option : ");
            int input = ss.nextInt();
            switch (input){
                case 1:{
                    addClient();
                    break;
                }
                case 2: {
                    updateClient();
                    break;
                }
                case 3 : {
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
            if(input==0){
                break;
            }
        }
    }

}
