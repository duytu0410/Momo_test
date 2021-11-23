package com.company;

import com.company.Model.Product;

import java.text.NumberFormat;
import java.util.*;

public class Main {
    private static int Wallet_Money = 0;
    private static int quantity = 0;
    private static int budget = 50000;
    private static Product Coke = new Product("Coke", 10000);
    private static Product Pepsi = new Product("Pepsi", 10000);
    private static Product Soda = new Product("Soda", 20000);
    private static List<Product> products = new ArrayList<Product>();
    private static final int[]moneyList = {10000,20000,50000,100000,200000};
    private static final String digit = "\\d*";
    private static Queue<Integer> queue = new LinkedList<Integer>();
    private static int winRate = 10;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        products.add(Coke);
        products.add(Pepsi);
        products.add(Soda);
        queue.add(0);
        queue.add(0);
        queue.add(0);
        while(true){
            System.out.println("Vui lòng chọn chức năng:");
            System.out.println("1. Nạp thêm tiền");
            System.out.println("2. Chọn sản phẩm muốn mua");
            System.out.println("0.Thoát khỏi hệ thống");
            String input = scanner.next();
            int option = validInput(input,2);
            switch (option){
                case 0:
                    System.exit(0);
                case 1:
                    System.out.println("Chọn số tiền muốn nạp:");
                    int len = moneyList.length;
                    for(int i =0;i<len;i++){
                        System.out.println((i+1)+"."+format(moneyList[i]));
                    }
                    System.out.println("0.Thoát");
                    String inputMoney = scanner.next();
                    int inputMoneyValidated = validInput(inputMoney,len);
                    switch (inputMoneyValidated){
                        case 0:
                            break;
                        case 1:
                            Wallet_Money+=10000;
                            buyProduct();
                            break;
                        case 2:
                            Wallet_Money+=20000;
                            buyProduct();
                            break;
                        case 3:
                            Wallet_Money+=50000;
                            buyProduct();
                            break;
                        case 4:
                            Wallet_Money+=100000;
                            buyProduct();
                            break;
                        case 5:
                            Wallet_Money+=200000;
                            buyProduct();
                            break;
                    }
                    break;
                case 2:
                    if(Wallet_Money == 0) {
                        System.out.println("Vui lòng đút tiền vào trước");
                    }
                    else if(Wallet_Money > 0) {
                        buyProduct();
                    }
                    else{
                        System.out.println("Đã xảy ra lỗi, vui lòng thử lại sau");
                    }
                    break;
            }

        }
    }
    public static void buyProduct(){
        Scanner scanner = new Scanner(System.in);
        int size = products.size();
        System.out.println("Vui lòng chọn sản phẩm muốn mua:");
        for(int i =0;i< size; i++){
            System.out.println((i+1)+"."+products.get(i).getName());
        }
        System.out.println("0.Hủy");
        String option = scanner.next();
        int optionValidated = validInput(option,size);
        switch (optionValidated){
            case 0:
                System.out.println("Mời nhận lại tiền: "+ format(Wallet_Money));
                break;
            case 1:
                calculateBill(Coke);
                break;
            case 2:
                calculateBill(Pepsi);
                break;
            case 3:
                calculateBill(Soda);
                break;
        }
    }
    private static void calculateBill(Product product){
        Scanner scanner = new Scanner(System.in);
        int maxQuantity = Wallet_Money/product.getPrice();
        System.out.println("Nhập số lượng muốn mua, tối đa: "+ maxQuantity);
        String input = scanner.next();
        quantity = validInput(input,maxQuantity);
        int billMoney = quantity*product.getPrice();
        swap(products.indexOf(product)+1);
        checkWin(products.indexOf(product)+1);
        String mess = "Mời nhận "+ quantity +" "+ product.getName();
        if(billMoney == Wallet_Money){
            System.out.println(mess);
        }
        else if (billMoney < Wallet_Money){
            mess+= " và "
                    +format(Wallet_Money-billMoney)+" tiền thừa";
            System.out.println(mess);
        }
        else{
            System.out.println("Đã xảy ra sự cố vui lòng thử lại sau, mời nhận lại "+format(Wallet_Money));
        }
        Wallet_Money = 0;
    }
    private static int validInput(String input, int maxNumber){
        Scanner scanner = new Scanner(System.in);
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
    private static void event(){

    }
    private static String format(int currency){
        Locale locale = new Locale("vi","VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(currency);
    }
    private static void swap(int value){
        int len = quantity > 3 ? 3:quantity;
        for(int i =0; i< len;i++){
            queue.remove();
            queue.add(value);
        }
    }
    public static void checkWin(int value){
        Random r = new Random();
        int winRateThisTurn = r.nextInt(100);
        boolean flag  = true;
        for(int x : queue){
            if(value!=x){
                flag = false;
            }
        }
        System.out.println(winRateThisTurn);
        if((budget>=products.get(value-1).getPrice()) && (winRateThisTurn > winRate) && (flag)){
            budget-= products.get(value-1).getPrice();
            System.out.println("Chúc mừng bạn đã trúng 1 "+ products.get(value-1).getName());
            System.out.println(budget);
            quantity++;
        }
    }
}
