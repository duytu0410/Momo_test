package com.company.Machine;

import com.company.FactoryClass.ProductFactory;
import com.company.ProductType.ProductType;
import com.company.SubClass.Coke;
import com.company.SubClass.Pepsi;
import com.company.SubClass.Soda;
import com.company.SuperClass.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisplayMachine {
    FormatMachine formatMachine = FormatMachine.getInstance();


    private DisplayMachine(){

    }
    private static class SingletonHelper{
        private static final DisplayMachine INSTANCE = new DisplayMachine();
    }
    public static DisplayMachine getInstance(){
        return SingletonHelper.INSTANCE;
    }
    public void displayProductsOption(int walletMoney,List<Product> products){
        int size = products.size();
        System.out.println("Vui lòng chọn sản phẩm muốn mua:");
        for(int i =0;i< size; i++){
            if(walletMoney>=products.get(i).getProductPrice()){
                System.out.println((i+1)+"."+products.get(i).getProductName());
            }
        }
        System.out.println("0.Hủy");
    }
    public void displayFunctionOption(){
        System.out.println("Vui lòng chọn chức năng:");
        System.out.println("1. Nạp thêm tiền");
        System.out.println("2. Chọn sản phẩm muốn mua");
        System.out.println("0.Thoát khỏi hệ thống");
    }
    public void displayInsertMoneyOption(int[]moneyList){
        System.out.println("Chọn số tiền muốn nạp:");
        int len = moneyList.length;
        for(int i =0;i<len;i++){
            System.out.println((i+1)+"."+formatMachine.format(moneyList[i]));
        }
        System.out.println("0.Thoát");
    }
    public void requestEnterQuantity(int maxQuantity){
        System.out.println("Nhập số lượng muốn mua, tối đa: "+ maxQuantity);
    }
    public void askCustomerToTakeProduct(int quantity, Product product, int change){
        if(change == 0){
            System.out.println("Mời nhận "+ quantity + " "+ product.getProductName());
        }
        else if(change > 0){
            System.out.println("Mời nhận "+ quantity + " "+ product.getProductName()
                    + " và "+ change + " tiền thừa");
        }
        else {
            printError();
        }
    }
    public void askForMoneyFirst(){
        System.out.println("Vui lòng đút tiền vào trước");
    }
    public void printError(){
        System.out.println("Đã xảy ra lỗi vui lòng thử lại sau");
    }
    public void askForTakeBackMoney(int money){
        System.out.println("Mời nhận lại "+ formatMachine.format(money));
    }
    public void congratulation(Product product){
        System.out.println("Xin chúc mừng, bạn đã trúng 1 "+ product.getProductName());
    }
}
