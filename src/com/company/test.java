package com.company;

import com.company.Model.Product;

import java.util.*;

public class test {
    public static Queue<Integer> queue = new LinkedList<Integer>();
    public static int winRate = 10;
    private static Product Coke = new Product("Coke", 10000);
    private static Product Pepsi = new Product("Pepsi", 10000);
    private static Product Soda = new Product("Soda", 20000);
    private static List<Product> products = new ArrayList<Product>();
    private static int budget = 50000;
    public static void main(String[] args) {
        queue.add(0);
        queue.add(0);
        queue.add(0);
        products.add(Coke);
        products.add(Pepsi);
        products.add(Soda);
//        boolean flag;
//        Scanner sc = new Scanner(System.in);
//        String digit = "\\d*";
//        do {
//
//            // must be a digit from 0 - 9
//            System.out.print("Input an integer: ");
//
//            String input = sc.next();
//
//            flag = input.matches(digit);
//
//            if (!flag) System.out.println("You must enter a number!");
//
//        } while (!flag);
//
//        System.out.println("Valid data");
        swap(1,3);
        for(int x: queue){
            System.out.println(x);
        }

    }
    public static void swap(int value,int count){
       for(int i =0; i< count;i++){
           queue.remove();
           queue.add(value);
       }
    }
    public static void checkWin(int value){
        Random r = new Random();
        boolean flag  = true;
        for(int x : queue){
            if(value!=x){
                flag = false;
            }
        }
        int winRateThisTurn = r.nextInt(100);
        System.out.println(winRateThisTurn);
        if((budget>products.get(value-1).getPrice())&&(winRateThisTurn > winRate)&&(flag)){
            budget-= products.get(value).getPrice();
            System.out.println("Chúc mừng bạn đã trúng 1 "+ products.get(value-1).getName());
        }
    }


}
