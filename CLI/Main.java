package com.IXACBANK.TERMINAL;

import java.io.File;
import java.util.*;
public class Main extends Database{
    Main(){
        /*--------------NULL TRANSACTIONS CONSTRUCTOR--------------*/
    }
    @Override
    public void displayWelcomeInfo(){
        super.displayWelcomeInfo();
        System.out.println("This Is The Main Program From Which You Can Access All Our Services");
        System.out.println("You Are Required To Use The Program According To The Rules Below");
        System.out.println("1.You Are Suppossed To Give Only Vaid Inputs");
        System.out.println("2.You Must Agree To The Terms And condition");
        System.out.println("3.Failure To So Will Result To The Cancelation Of The Program");
    }
    public void quit(){
        clearScreen();
        System.out.println("\nGOOD~BYE!!!\nSEE YOU NEXT TIME!!!");
        System.out.println("MADE WITH LOVE BY\n[<L-O-K-I-W-H-A-N>]");
    }
    public void tasks(){
        clearScreen();
        System.out.println("\n1.Login\n2.Create An Account\n3.Quit");
        System.out.print("Input Choice: ");
    }
    public void afterLoginTasks(){
        clearScreen();
        System.out.println("\n1.Withdraw Cash\n2.Deposit Cash\n3.Check Account Balance\n4.Check Bank Statement\n5.Log Out");
        System.out.print("Input Choice: ");
    }
    public void stementtasks(){
        clearScreen();
        System.out.println("\n1.Check Last 5 Transactions Statement\n2.Check Last 10 Transactions Statement\n3.Check All Transactions Statement\n4.Back");
        System.out.print("Input Choice: ");
    }
    public void invalidChoice(){
        System.out.println("\nYou Have Entered An Invalid/Wrong Input!!!\nTry Again!!!");
    }
    public void logout(){
        clearScreen();
        System.out.println("\nLogging you out of your account please wait...");
        delayRun(2);
        clearScreen();
        System.out.println("\nYou Have Been Successfully Logged Out!!\nSee You Next Time!!!");
    }
    public void login(){
        clearScreen();
        System.out.println("Logging into your account\nPlease wait...");
        delayRun(3);
    }
    public boolean validateLogin(int enteredSecreteCode, int enteredAccountNumber, int[] secretCodes, int[] accountNumbers){
        boolean isValid = false;
        for(int i =  0; i < accountNumbers.length; i++){
            if(enteredSecreteCode == secretCodes[i] && enteredAccountNumber == accountNumbers[i]){
                isValid = true;
            }
        }
        return isValid;
    }
    public void dispalyUserDetails(int enterSecretCode){
        clearScreen();
        System.out.println("\nAccount Holder's Name: " + getAccountName());
        System.out.println("Account Holder's Number: " + getAccouuntNumber());
        System.out.println("Account Holder's secret Code: " + enterSecretCode);
        System.out.println("Account Holder's Balance: $" + getAccountBalance());
    }
    public void withdrawCashFunction(){
        Scanner sc = new Scanner(System.in);
        int key = 1;
        double amount;
        String[] whatToDisplay = new String[2];
        String input;
        clearScreen();
        do{
            do{
                System.out.print("\nInput Amount To Withdraw: $");
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
            if(amount <= 50.00){
                System.out.println("\nPlease Input A Value Greater Than 50!!!");
            }else if(amount >= getAccountBalance()){
                System.out.println("\nPlease Input A Value Less Than Your Balance!!!");
            }else if(amount > 50 && amount < getAccountBalance()){
                withdrawCash(amount);
                afterSuccessfullWithdraw(amount);
                key++;
            }
        }while(key == 1);
    }
    public void depositCashFunction(){
        Scanner sc = new Scanner(System.in);
        int key = 1;
        double amount;
        String[] whatToDisplay = new String[2];
        String input;
        clearScreen();
        do{
            do{
                System.out.print("\nInput Amount To Deposit: $");
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
                depositCash(amount);
                afterSuccessfullDeposit(amount);
                key++;
            }
        }while(key == 1);
    }
    public void withdrawCash(double amount){
        int transactionType = 1;
        addTransactionToDatabseOfUser(amount, transactionType);
        super.withdraw(amount);
    }
    public void depositCash(double amount){
        int transactionType = 2;
        addTransactionToDatabseOfUser(amount, transactionType);
        super.deposit(amount);
    }
    public void afterSuccessfullWithdraw(double amountWithdrew){
        clearScreen();
        System.out.println("You Have Successfully Withdrawn $" + amountWithdrew + " From Your Account.");
        System.out.println("Your Current Balance Is $" + getAccountBalance());
        delayRun(5);
    }
    public void afterSuccessfullDeposit(double amountDeposited){
        clearScreen();
        System.out.println("You Have Successfully Deposited $" + amountDeposited + " To Your Account.");
        System.out.println("Your Current Balance Is $" + getAccountBalance());
        delayRun(5);
    }
    public void dispalyAccountBalance(){
        clearScreen();
        System.out.println("\nYour Current Account Balance is $" + getAccountBalance());
        delayRun(5);
    }
    public static void main(String[] args){
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        String[] whatToDisplay = new String[2];
        String input;
        int condition;

        if(Boolean.valueOf(main.getLogValue("IsNewUser"))){
            main.displayWelcomeInfo();
            main.createLogFile();
            do{
                System.out.print("\n1.Yes\n2.No\nInput Choice: ");
                input = sc.nextLine();
                if(main.validateInput(input, whatToDisplay) != true){
                    if(input.length() < 1){
                        System.out.println(whatToDisplay[1]);
                    }else{
                        System.out.println(whatToDisplay[0]);
                    }
                }
            }while(main.validateInput(input, whatToDisplay) == false);
            main.updateLogValue("IsNewUser", "false");
            condition = Integer.valueOf(input);
        }else{
            condition = 1;
        }

        switch(condition){
            case 1:
                while(true){
                    do{
                        main.tasks();
                        input = sc.nextLine();
                        if(main.validateInput(input, whatToDisplay) != true){
                            if(input.length() < 1){
                                System.out.println(whatToDisplay[1]);
                                main.delayRun(2);
                            }else{
                                System.out.println(whatToDisplay[0]);
                                main.delayRun(2);
                            }
                        }
                    }while(main.validateInput(input, whatToDisplay) == false);

                    int control = Integer.valueOf(input);

                    if(!main.hasAllRequiredFiles() && control == 1){
                        main.clearScreen();
                        System.out.println("Sorry You need to create an account first before logging in!");
                        System.out.println("You will be automatically taken to the register section to create an account!");
                        System.out.println("It will only take a few seconds!");
                        main.delayRun(5);
                        control = 2;
                    }

                    if(control == 1){
                        int size = main.getArraySize();
                        String file_name = "Account Names", file_extension = ".txt";

                        String[] accountNames = new String[size];
                        String[] accountNumbers = new String[size];
                        String[] accountBalances = new String[size];
                        int[] secretCodes = new int[size];
                        int[] realAccountNumbers = new int[size];
                        double[] realAccountBalances = new double[size];

                        main.fetchAccountNames(file_name, file_extension, accountNames);
                        main.fetchAccountBalances("Account Balances", file_extension, accountBalances, realAccountBalances);
                        main.fetchAccountNumbers("Account Numbers", file_extension, accountNumbers, realAccountNumbers);
                        main.fetchSecretCodes(secretCodes, accountNumbers);

                        int enterSecretCode, enterAccountNumber, accountNumbersSize = 9, secretCodeSize = 3;
                        main.clearScreen();
                        do {
                            do {
                                System.out.print("\nInput Your Account Number: ");
                                input = sc.nextLine();
                                if (input.length() <= 8) {
                                    if (main.validateInput(input, whatToDisplay) != true) {
                                        if (input.length() == 0) {
                                            System.out.println(whatToDisplay[1]);
                                        } else {
                                            System.out.println(whatToDisplay[0]);
                                        }
                                    } else {
                                        System.out.println("\nAccount number cannot be less 9 digits!!!\nTry again...");
                                    }
                                } else if (input.length() > 9) {
                                    System.out.println("\nAccount number cannot be greater than 9 digits!!!\nTry again..\n");
                                }
                            } while (input.length() != 9);
                        } while (main.validateInput(input, whatToDisplay) == false);
                        main.clearScreen();

                        enterAccountNumber = Integer.valueOf(input);

                        do {
                            do {
                                System.out.print("\nInput Your Secret Code [***]: ");
                                input = sc.nextLine();
                                if (input.length() <= 2) {
                                    if (main.validateInput(input, whatToDisplay) != true) {
                                        if (input.length() == 0) {
                                            System.out.println(whatToDisplay[1]);
                                        } else {
                                            System.out.println(whatToDisplay[0]);
                                        }
                                    } else {
                                        System.out.println("\nSecret code be less 3 digits!!!\nTry again...");
                                    }
                                } else if (input.length() > 3) {
                                    System.out.println("\nAccount number cannot be greater than 3 digits!!!\nTry again..\n");
                                }
                            } while (input.length() != 3);
                        } while (main.validateInput(input, whatToDisplay) == false);

                        enterSecretCode = Integer.valueOf(input);

                        if (main.validateLogin(enterSecretCode, enterAccountNumber, secretCodes, realAccountNumbers) == true) {
                            String getuserName = main.getUsername(enterSecretCode);
                            double getuserBalance = main.getUserBalance(enterSecretCode);
                            boolean isValidLogin = true;
                            for (int i = 0; i < size; i++) {
                                if (enterAccountNumber != realAccountNumbers[i] || enterSecretCode != secretCodes[i]) {
                                    isValidLogin = false;
                                }
                            }
                            if ((isValidLogin == false && getuserName.length() == 0) || (main.crossCheckDetails(enterSecretCode, enterAccountNumber) != 1)) {
                                System.out.println("\nStop Trying To Mess With The System!!!");
                                break;
                            }

                            main.login();

                            main.clearScreen();
                            System.out.println("\nWelcome " + getuserName);
                            main.delayRun(2);
                            main.setAccountName(getuserName);
                            main.setAccountNumber(enterAccountNumber);
                            main.setAccountBalance(getuserBalance);
                            int key = 1;
                            do {
                                do {
                                    main.afterLoginTasks();
                                    input = sc.nextLine();
                                    if (main.validateInput(input, whatToDisplay) != true) {
                                        if (input.length() < 1) {
                                            System.out.println(whatToDisplay[1]);
                                            main.delayRun(2);
                                        } else {
                                            System.out.println(whatToDisplay[0]);
                                            main.delayRun(2);
                                        }
                                    }
                                } while (main.validateInput(input, whatToDisplay) == false);

                                int afterLoginControl = Integer.valueOf(input);

                                if (afterLoginControl == 1) {
                                    main.withdrawCashFunction();
                                    main.updateUserBalance(enterSecretCode);
                                } else if (afterLoginControl == 2) {
                                    main.depositCashFunction();
                                    main.updateUserBalance(enterSecretCode);
                                } else if (afterLoginControl == 3) {
                                    main.dispalyAccountBalance();
                                } else if (afterLoginControl == 4) {
                                    int contr = 1;
                                    int userTransactionSize = main.getUserTransactionSize(enterAccountNumber);
                                    String[] userStatements = new String[userTransactionSize];
                                    main.fetchUserTransactions(enterAccountNumber, ".txt", userStatements);
                                    do {
                                        do {
                                            main.stementtasks();
                                            input = sc.nextLine();
                                            if (main.validateInput(input, whatToDisplay) != true) {
                                                if (input.length() < 1) {
                                                    System.out.println(whatToDisplay[1]);
                                                    main.delayRun(2);
                                                } else {
                                                    System.out.println(whatToDisplay[0]);
                                                    main.delayRun(2);
                                                }
                                            }
                                        } while (main.validateInput(input, whatToDisplay) == false);

                                        int statementContol = Integer.valueOf(input);

                                        if (statementContol == 1) {
                                            if (userTransactionSize < 5) {
                                                main.clearScreen();
                                                System.out.println("\nYou Have Less Than [5] Transaction In Your Database Choose A Lower Value Or Check All Transaction.");
                                                main.delayRun(2);
                                            } else if (userTransactionSize >= 5) {
                                                main.displayTransactionsStatements(5, userStatements, false);
                                                main.delayRun(10);
                                            }
                                        } else if (statementContol == 2) {
                                            if (userTransactionSize < 10) {
                                                main.clearScreen();
                                                System.out.println("\nYou Have Less Than [10] Transaction In Your Database Choose Another Option Or Check All Transaction.");
                                                main.delayRun(2);
                                            } else if (userTransactionSize >= 10) {
                                                main.displayTransactionsStatements(10, userStatements, false);
                                                main.delayRun(10);
                                            }
                                        } else if (statementContol == 3) {
                                            int displaySize = userTransactionSize;
                                            main.displayTransactionsStatements(3, userStatements, true);
                                            main.delayRun(10);
                                        } else if (statementContol == 4) {
                                            contr++;
                                        } else {
                                            main.invalidChoice();
                                            main.delayRun(2);
                                        }
                                    } while (contr == 1);
                                } else if (afterLoginControl == 5) {
                                    main.setAccountName("");
                                    main.setAccountNumber(0);
                                    main.setAccountBalance(0.00);
                                    main.logout();
                                    main.delayRun(2);
                                    key++;
                                    break;
                                } else {
                                    main.invalidChoice();
                                }
                            } while (key == 1);
                        } else {
                            System.out.println("\nYou Have Entered An Incorrect Account Number/Secret Code!\nTry Again!!!");
                            main.delayRun(3);
                        }
                    }else if(control == 2){

                        main.registerAccount();
                        main.displayAfterSuccessfulRegister();
                        int key = 1;
                        do{
                            do{
                                main.displayPostTasksRegistration();
                                input = sc.nextLine();
                                if(main.validateInput(input, whatToDisplay) != true){
                                    if(input.length() < 1){
                                        System.out.println(whatToDisplay[1]);
                                    }else{
                                        System.out.println(whatToDisplay[0]);
                                    }
                                }
                            }while(main.validateInput(input, whatToDisplay) == false);

                            int afterRegistrationControl = Integer.valueOf(input);

                            if(afterRegistrationControl  == 1){
                                main.displayUserDetails();
                                key++;
                            }else if(afterRegistrationControl == 2){
                                key++;
                            }
                        }while(key == 1);
                    }else if(control == 3){
                        main.quit();
                        break;
                    }else{
                        main.invalidChoice();
                        main.delayRun(2);
                    }
                }
                break;
            case 2:
                main.quit();
                break;
            default:
                main.invalidChoice();
        }
    }

    private boolean hasAllRequiredFiles() {
        boolean _hasAllRequiredFiles = true;
        String absolute_path = "C:\\";
        File relative_path = new File(absolute_path+"ProgramData\\IXAC BANK\\Database\\");
        String[] stuff_in_dir = relative_path.list();
        if(stuff_in_dir.length <= 2)
            _hasAllRequiredFiles = false;
        return _hasAllRequiredFiles;
    }
}