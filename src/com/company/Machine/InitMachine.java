package com.company.Machine;

import com.company.FactoryClass.ProductFactory;
import com.company.ProductType.ProductType;
import com.company.SuperClass.Product;

import java.util.ArrayList;
import java.util.List;

public class InitMachine {
    private int Wallet_Money = 0;

    public int getWallet_Money() {
        return Wallet_Money;
    }

    public void setWallet_Money(int wallet_Money) {
        Wallet_Money = wallet_Money;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getWinRate() {
        return winRate;
    }

    public void setWinRate(int winRate) {
        this.winRate = winRate;
    }

    private int budget;
    private int winRate;



    private static final List<Product> products = new ArrayList<>();
    private static final int[]moneyList = {10000,20000,50000,100000,200000};
    private InitMachine(){
        products.add(ProductFactory.getProduct(ProductType.COKE));
        products.add(ProductFactory.getProduct(ProductType.PEPSI));
        products.add(ProductFactory.getProduct(ProductType.SODA));
        budget = 50000;
        winRate = 80;
    }
    private static class SingletonHelper{
        private static final InitMachine INSTANCE = new InitMachine();
    }
    public void setInit(){
        this.winRate =  10;
        this.budget = 50000;
    }
    public void increaseWinRate(){
        this.winRate+=50;
        this.winRate = this.winRate> 100 ? 100 : this.winRate;
    }
    public static InitMachine getInstance(){
        return InitMachine.SingletonHelper.INSTANCE;
    }
}
