package com.company.FactoryClass;

import com.company.ProductType.ProductType;
import com.company.SubClass.Coke;
import com.company.SubClass.Pepsi;
import com.company.SubClass.Soda;
import com.company.SuperClass.Product;

public class ProductFactory {
    private ProductFactory(){}
    public static final Product getProduct(ProductType productType){
        switch (productType){
            case COKE:
                return new Coke();
            case SODA:
                return new Soda();
            case PEPSI:
                return new Pepsi();
            default:
                throw new IllegalArgumentException("This product type is not supported");
        }
    }
}
