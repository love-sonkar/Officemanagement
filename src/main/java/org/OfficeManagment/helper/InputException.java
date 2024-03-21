package org.OfficeManagment.helper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputException {
    public int handleInputExceptionInt(String printvalue) throws InputMismatchException {
        int input =0;
        while (true){
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print(printvalue);
            input = scan.nextInt();
            break;
        } catch (InputMismatchException e) {
            System.out.println("wrong input");
        }
        }
        return input;
    }
    public Long handleInputExceptionLong(String printvalue) throws InputMismatchException {
        Long input;
        while (true){
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print(printvalue);
                input = scan.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("wrong input");
            }
        }
        return input;
    }

}
