package com.company.SubClass;

import com.company.SuperClass.Product;

public class Pepsi implements Product {
    @Override
    public String getProductName() {
        return "Pepsi";
    }

    @Override
    public int getProductPrice() {
        return 10000;
    }
}
