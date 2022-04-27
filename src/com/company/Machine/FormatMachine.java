package com.company.Machine;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatMachine {
    private FormatMachine(){}
    private static class SingletonHelper{
        private static final FormatMachine INSTANCE =  new FormatMachine();
    }
    public static FormatMachine getInstance(){
        return FormatMachine.SingletonHelper.INSTANCE;
    }
    public String format(int currency){
        Locale locale = new Locale("vi","VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(currency);
    }
}
