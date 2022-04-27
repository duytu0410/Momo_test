package com.company.Machine;

import com.company.FactoryClass.ProductFactory;
import com.company.ProductType.ProductType;
import com.company.SuperClass.Product;

import java.util.ArrayList;
import java.util.List;

public class CoreFactory {
    private static final List<Product> products = new ArrayList<>();
    private static final int[]moneyList = {10000,20000,50000,100000,200000};
    private CoreFactory(){
        products.add(ProductFactory.getProduct(ProductType.COKE));
        products.add(ProductFactory.getProduct(ProductType.PEPSI));
        products.add(ProductFactory.getProduct(ProductType.SODA));
    }
    private static class SingletonHelper{
        private static final CoreFactory INSTANCE = new CoreFactory();
    }
    public static CoreFactory getInstance(){
        return SingletonHelper.INSTANCE;
    }
    public int handleInsertMoneyFunction(SecureMachine secureMachine,DisplayMachine displayMachine){
        displayMachine.displayInsertMoneyOption(moneyList);
        int moneyOption = secureMachine.validInput(secureMachine.getInput(),moneyList.length);
        int walletMoney = 0;
        switch (moneyOption){
            case 0:
                System.exit(0);
            case 1:
                walletMoney=10000;
                break;
            case 2:
                walletMoney=20000;
                break;
            case 3:
                walletMoney=50000;
                break;
            case 4:
                walletMoney=100000;

                break;
            case 5:
                walletMoney=200000;
                break;
        }
        displayMachine.displayProductsOption(walletMoney,products);
        return walletMoney;
    }
    public int handleFunctionOption(int option,int walletMoney,
                                    DisplayMachine displayMachine,
                                    SecureMachine secureMachine,
                                    CalculateMachine calculateMachine,
                                    InitMachine initMachine,
                                    CheckMachine checkMachine){
        switch (option){
            case 0:
                System.exit(0);
            case 1:
                walletMoney = handleInsertMoneyFunction(secureMachine,displayMachine);
                walletMoney = handleBuyProduct(walletMoney,secureMachine,displayMachine,
                        calculateMachine,initMachine, checkMachine);
                break;
            case 2:
                System.out.println(walletMoney);
                if(walletMoney == 0) {
                    displayMachine.askForMoneyFirst();
                }
                else if(walletMoney > 0) {
                    walletMoney = handleBuyProduct(walletMoney,secureMachine,displayMachine,
                            calculateMachine,initMachine, checkMachine);
                }
                else{
                    System.out.println("Đã xảy ra lỗi, vui lòng thử lại sau");
                }
                break;
        }
        return walletMoney;
    }

    public int handleBuyProduct(int walletMoney,SecureMachine secureMachine,DisplayMachine displayMachine,
                                CalculateMachine calculateMachine, InitMachine initMachine, CheckMachine checkMachine){
        int productOption = secureMachine.validInput(secureMachine.getInput(),3);
        switch (productOption){
            case 0:
                displayMachine.askForTakeBackMoney(walletMoney);
                break;
            case 1:
                handleCalculateMoney(initMachine, checkMachine,ProductFactory.getProduct(ProductType.COKE)
                        ,walletMoney,displayMachine,secureMachine,calculateMachine);
                break;
            case 2:
                handleCalculateMoney(initMachine, checkMachine,ProductFactory.getProduct(ProductType.PEPSI)
                        ,walletMoney,displayMachine,secureMachine,calculateMachine);
                break;
            case 3:
                handleCalculateMoney(initMachine, checkMachine,ProductFactory.getProduct(ProductType.SODA)
                        ,walletMoney,displayMachine,secureMachine,calculateMachine);
                break;
        }
        walletMoney = 0;
        return walletMoney;
    }
    public void handleCalculateMoney(InitMachine initMachine, CheckMachine checkMachine ,Product product, int walletMoney, DisplayMachine displayMachine,
                                     SecureMachine secureMachine, CalculateMachine calculateMachine){
        int maxQuantity = calculateMachine.calcMaxQuantity(walletMoney,product);
        displayMachine.requestEnterQuantity(maxQuantity);
        int quantity = secureMachine.validInput(secureMachine.getInput(),maxQuantity);
        int totalBill = calculateMachine.calcTotalBill(quantity, product);
        int change = calculateMachine.calcChange(walletMoney,totalBill);
        quantity = handleEvent(initMachine,displayMachine,checkMachine,product,quantity)? quantity+1 : quantity;
        displayMachine.askCustomerToTakeProduct(quantity,product,change);
    }
    public boolean handleEvent(InitMachine initMachine,DisplayMachine displayMachine,CheckMachine checkMachine, Product product, int quantity){
        if(checkMachine.checkWin(product,initMachine.getBudget(),quantity,initMachine.getWinRate())){
            displayMachine.congratulation(product);
            initMachine.setBudget(initMachine.getBudget()-product.getProductPrice());
            System.out.println(initMachine.getBudget());
            return true;
        }
        return false;
    }
}
