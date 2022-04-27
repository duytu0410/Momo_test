package com.company.Machine;

import java.util.Scanner;

public class SecureMachine {
    Scanner scanner = new Scanner(System.in);
    private SecureMachine(){

    }
    private static class SingletonHelper{
        private static final SecureMachine INSTANCE =  new SecureMachine();
    }
    public static SecureMachine getInstance(){
        return SingletonHelper.INSTANCE;
    }
    private static final String digit = "\\d*";
    public String getInput(){
        return scanner.next();
    }
    public int validInput(String input, int maxNumber){
        int option = 0;
        boolean flag = false;
        do{
            try{
                if(input.matches(digit)) {
                    option = Integer.parseInt(input);
                    if (option <= maxNumber) {
                        flag = true;
                    }
                }
            }catch (NumberFormatException e){
            }finally {
                if (!flag) {
                    System.out.println("Vui lòng nhập lại, số nhập vào phải là số nguyên dương," +
                            " lớn hơn 0 và nhỏ hơn "+ (maxNumber+1)+":");
                    input = scanner.next();
                }
            }
        }while(!flag);
        return option;
    }
}
