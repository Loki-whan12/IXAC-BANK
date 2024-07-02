package com.IXACBANK.TERMINAL;

import java.util.*;
class Registration extends Transactions{
    Registration(){
        /*--------------NULL REGISTRATION CONSTRUCTOR--------------*/
    }
    public void displayWelcomeInfo(){
        System.out.println("\t\t\tIXAC BANK");
        System.out.println("\t\t  WELCOME To IXAC BANK");
    }
    public void displayAfterSuccessfulRegister(){
        clearScreen();
        System.out.println("\nYour Account Has Successfully Been Created!");
        System.out.println("Please Check To Confirm Your Details");
        System.out.println("And Ensure That They Are Your Correct Details");
        System.out.println("And Ensure That You Make A Copy Of Them");
        System.out.println("You Will Require Them To Able To Login Into Your Account");
        delayRun(5);
    }
    public void displayPostTasksRegistration(){
        clearScreen();
        System.out.println("\nSelect An Option");
        System.out.print("1.Display Your Details\n2.Back To Main Menu\nInput Choice: ");
    }
    public void registerAccount(){
        Scanner sc = new Scanner(System.in);
        String firstName, middleName, lastName, full_name, input;
        String[] whatToDisplay = new String[2];
        int key = 1;
        double amount;
        clearScreen();
        System.out.println("\t\t\tACCOUNT CREATION\n");
        do{
            do{
                System.out.print("\nInput first name: ");
                firstName = sc.nextLine();
                if(firstName.length() < 1){
                    System.out.println("\nSorry you haven't enterd a name\nThis is a required name\nTry again...");
                }
                if(validateName(firstName) == false){
                    System.out.println("\nSorry backspace is not a valid name\nTry again...");
                }
            }while(validateName(firstName) != true);
        }while(firstName.length() < 1);
        
        System.out.print("\nInput middle name [If none press enter]: ");
        middleName = sc.nextLine();
        
        do{
            do{
                System.out.print("\nInput Last name: ");
                lastName = sc.nextLine();
                if(lastName.length() < 1){
                    System.out.println("\nSorry you haven't entered a name\nThis is a required name\nTry again...");
                }
                if(validateName(lastName) == false){
                    System.out.println("\nSorry backspace is not a valid name\nTry again...");
                }
            }while(validateName(firstName) != true);
        }while(lastName.length() < 1);

        if(middleName.length() > 1){
            full_name = firstName + " " + middleName + " " + lastName;
        }else{
            full_name = firstName + " " + lastName;
        }

        clearScreen();
        do{
            do{
                System.out.print("\nInput Amount To Deposit Initialy Into Account : $");                
                input = sc.nextLine();
                if(validateInputTransaction(input, whatToDisplay) != true){
                    if(input.length() < 1){
                        System.out.println(whatToDisplay[1]);
                    }else{
                        System.out.println(whatToDisplay[0]);
                    }
                }
            }while(validateInputTransaction(input, whatToDisplay) == false);
            amount = Double.valueOf(input);
            if(amount <= 100.00){
                System.out.println("\nPlease Input A Value Greater Than 100!!!");
            }else if(amount >= 10000000.00){
                System.out.println("\nPlease Input A Value Lower Than 10000000.00!!!");
            }else if(amount > 100 && amount < 10000000.00){
                key++;
            }
        }while(key == 1);

        clearScreen();
        System.out.println("Creating you account please wait\nThis will only take some few seconds...");
        delayRun(5);


        int value = Integer.valueOf(ReadUniqueNumber());
        value++;
        SetUniqueNumber(value);
        int Id = Integer.valueOf(ReadUniqueNumber());
        
        int number = 505322;

        String bankCode = String.valueOf(number);
        String uniqueNumber = String.valueOf(Id);
        String concat = bankCode + uniqueNumber;
        int reFormat = Integer.valueOf(concat);
                
        String formatName = full_name + uniqueNumber;
        

        String balance = String.valueOf(amount);
        String cancatBalanace = uniqueNumber + "/" + balance;
        
        setAccountName(full_name);
        setAccountNumber(reFormat);
        setAccountBalance(amount);

        StoreAccountName(formatName);
        StoreAccountNumber(reFormat);
        StoreAccountBalance(cancatBalanace);
        CreateTrackTransaction(reFormat);
    }
    public void displayUserDetails(){
        clearScreen();
        System.out.println("\nAccount Holder's Name : " + getAccountName());
        System.out.println("Account Number : " + getAccouuntNumber());
        int code = getAccouuntNumber();
        String cast = String.valueOf(code);
        String getCode = cast.substring(6,9);
        int secretCode = Integer.valueOf(getCode);
        System.out.println("Account Secreet Code : " + secretCode);
        delayRun(10);
    }
}