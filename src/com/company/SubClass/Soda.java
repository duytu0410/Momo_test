package com.company.SubClass;

import com.company.SuperClass.Product;

public class Soda implements Product {
    @Override
    public String getProductName() {
        return "Soda";
    }

    @Override
    public int getProductPrice() {
        return 20000;
    }
}
