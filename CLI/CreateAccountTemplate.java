package com.IXACBANK.TERMINAL;

import java.text.DecimalFormat;
public class CreateAccountTemplate extends Write{
    private String accountName;
    private int accountNumber;
    private double accountBalance;

    CreateAccountTemplate(String accountName, int accountNumber, double accountBalance){
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }
    public String getAccountName(){
        return this.accountName;
    }
    public int getAccouuntNumber(){
        return this.accountNumber;
    }
    public double getAccountBalance(){
        return this.accountBalance;
    }
    public void setAccountName(String name){
        this.accountName = name;
    }
    public void setAccountNumber(int number){
        this.accountNumber = number;
    }
    public void setAccountBalance(double amount){
        this.accountBalance = amount;
    }
    public void formatBalance(double balanceToFormat){
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedBalance = df.format(balanceToFormat);
        double convertedBalance = Double.valueOf(formattedBalance);
        setAccountBalance(convertedBalance);
    }
}