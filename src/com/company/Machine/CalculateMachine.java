package com.company.Machine;

import com.company.SuperClass.Product;

import java.time.Duration;
import java.time.LocalTime;

public class CalculateMachine {
    private CalculateMachine(){}
    private static class SingletonHelper{
        private static final CalculateMachine INSTANCE = new CalculateMachine();
    }
    public static CalculateMachine getInstance(){
        return SingletonHelper.INSTANCE;
    }
    public int calcTotalBill(int quantity, Product product){

        return quantity*product.getProductPrice();
    }
    public int calcChange(int money, int totalBill){

        return money - totalBill;
    }
    public int calcMaxQuantity(int money, Product product){

        return money/product.getProductPrice();
    }
    public long calculateTimeToNextDay() {
        LocalTime time = LocalTime.now();
        LocalTime specificTime = LocalTime.of(0, 00, 00,00);
        Duration duration = Duration.between(specificTime,time);
        long timeLeft = 86400 - duration.getSeconds();
        return timeLeft;
    }
}
