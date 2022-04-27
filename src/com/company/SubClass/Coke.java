package com.company.SubClass;

import com.company.SuperClass.Product;

public class Coke implements Product {
    @Override
    public String getProductName() {
        return "Coke";
    }

    @Override
    public int getProductPrice() {
        return 10000;
    }
}
