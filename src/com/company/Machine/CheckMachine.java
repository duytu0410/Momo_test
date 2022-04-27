package com.company.Machine;

import com.company.SuperClass.Product;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CheckMachine {
    private CheckMachine(){}
    private static class SingletonHelper{
        private static final CheckMachine INSTANCE = new CheckMachine();
    }
    public static CheckMachine getInstance(){
        return CheckMachine.SingletonHelper.INSTANCE;
    }
    private static Queue<Product> queue = new LinkedList<Product>();
    public static void addProductToQueue(Product product, int quantity){
        int len = quantity > 3 ? 3:quantity;
        for(int i =0; i< len;i++){
            if(queue.isEmpty()){
                queue.add(product);
            }else if(!queue.isEmpty()){
                queue.remove();
                queue.add(product);
            }

        }

    }
    public static boolean checkBudgetCondition(int budget, int price){
        boolean flag = budget > price ? true : false;
        return flag;
    }
    public boolean budgetIsEmpty(int budget){
        return budget == 0 ? true : false;
    }
    public boolean checkIfADayPassed(long interval){
        return interval == 0 ? true : false;
    }
    public boolean checkWin(Product product, int budget, int quantity, int winRate){
        Random r = new Random();
        int winRateThisTurn = r.nextInt(100);
        addProductToQueue(product,quantity);
        for( Product x:queue){
            if(!x.equals(product)){
                return false;
            }
        }
        if(winRateThisTurn>winRate){
            return false;
        }
        return checkBudgetCondition(budget,product.getProductPrice());
    }
}
