package com.IXACBANK.TERMINAL;

public class Transactions extends CreateAccountTemplate{
    Transactions(){
        super("", 0, 0.00);
    }
    public void withdraw(double amount){
        double formatBalance = getAccountBalance() - amount;
        formatBalance(formatBalance);
    }
    public void deposit(double amount){
        double formatBalance = getAccountBalance() + amount;
        formatBalance(formatBalance);
    }
}