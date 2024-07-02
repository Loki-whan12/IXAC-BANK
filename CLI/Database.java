package com.IXACBANK.TERMINAL;

import java.io.*;
public class Database extends Registration {
    Database(){
        /*--------------NULL DATABASE CONSTRUCTOR--------------*/
    }
    @Override
    public void fetchAccountNames(String file_name, String file_extension, String[] array){
        super.fetchAccountNames(file_name, file_extension, array);
        reFormatAccountNames(array);
    }
    @Override
    public void fetchAccountBalances(String file_name, String file_extension, String[] array, double[] accountBalances){
        super.fetchAccountBalances(file_name, file_extension, array, accountBalances);
        reFormatAccountBalances(array);
        convertArrayToTypeDouble(array, accountBalances);
    }
    @Override
    public void fetchAccountNumbers(String file_name, String file_extension, String[] array, int[] accountNumbers){
        super.fetchAccountNumbers(file_name, file_extension, array, accountNumbers);
        convertArrayToTypeInt(array, accountNumbers);
    }
    private void reFormatAccountBalances(String[] array){
        int size = getArraySize();
        String[] reFormattedBalances = new String[size];
        int i = 0;
        for(String str : array){
            if(str == null){
                break;
            }
            String formattedBalance = str.substring(4);
            reFormattedBalances[i] = formattedBalance;
            i++;
        }
        for(int j = 0; j < reFormattedBalances.length; j++){
            array[j] = reFormattedBalances[j];
        }
    }
    private void reFormatAccountNames(String[] array){
        int size = getArraySize();
        String[] reFormattedNames = new String[size];
        int i = 0;
        for(String str : array){
            if(str == null){
                break;
            }
            int endPosition = str.length() - 3;
            String formattedName = str.substring(0, endPosition);
            reFormattedNames[i] = formattedName;
            i++;
        }
        for(int j = 0; j < reFormattedNames.length; j++){
            array[j] = reFormattedNames[j];
        }
    }
    public int getArraySize(){
        int actualUsers = Integer.valueOf(ReadDatabseAccountNamesCounter());
        String size;
        if(actualUsers <= 9){
            size = ReadUniqueNumber().substring(2,3);
        }else if(actualUsers <= 99){
            size = ReadUniqueNumber().substring(1,3);
        }else{
            size = String.valueOf(ReadUniqueNumber());
        }
        return Integer.valueOf(size);
    }
    private void displayEveryInfoOfEveryAccounHolder(){
        int size = getArraySize();
        String file_name = "Account Names", file_extension = ".txt";
        
        String[] accountNames = new String[size];
        String[] accountBalances = new String[size];
        String[] accountNumbers = new String[size];
        int[] realAccountNumbers = new int[size];
        double[] realAccountBalances = new double[size];

        fetchAccountNames(file_name, file_extension, accountNames);
        fetchAccountBalances("Account Balances", file_extension, accountBalances, realAccountBalances);
        fetchAccountNumbers("Account Numbers", file_extension, accountNumbers, realAccountNumbers);

        for(int i = 0; i < size; i++){
            int secretCode = getSecretCode(accountNumbers[i]);
            System.out.println("[Account Holder's Name: " + accountNames[i] + "]");
            System.out.println("[Account Holder's Number: " + accountNumbers[i] + "]");
            System.out.println("[Account Holder's secret Code: " + secretCode + "]");
            System.out.println("[Account Holder's Balance: $" + accountBalances[i] + "]\n");
        }
    }
    private int getSecretCode(String accountNumber){
        String cast = String.valueOf(accountNumber);
        String getCode = cast.substring(6,9);
        int secretCode = Integer.valueOf(getCode);
        return secretCode;
    }
    public void fetchSecretCodes(int[] secretCodes, String[] accountNumbers){
        for(int i = 0; i < accountNumbers.length; i++){
            int secretCode = getSecretCode(accountNumbers[i]);
            secretCodes[i] = secretCode;
        }
    }
    public void convertArrayToTypeInt(String[] array, int[] accountNumbers){
        for(int i = 0; i < array.length; i++){
            int accountNum = Integer.valueOf(array[i]);
            accountNumbers[i] = accountNum;
        }
    }
    public void convertArrayToTypeDouble(String[] array, double[] accountBalances){
        for(int i = 0; i < array.length; i++){
            double accountBal = Double.valueOf(array[i]);
            accountBalances[i] = accountBal;
        }
    }
    public String getUsername(int secretCode){
        int size = getArraySize();
        String file_name = "Account Names", file_extension = ".txt";
        String[] usernames = new String[size];
        super.fetchAccountNames(file_name, file_extension, usernames);
        String username = returnAccountName(secretCode, usernames);
        return username;
    }
    public double getUserBalance(int secretCode){
        int size = getArraySize();
        String file_name = "Account Balances", file_extension = ".txt";
        double userBalance = 0.00; 
        String[] userBalances = new String[size];
        double[] temp = new double[0];
        super.fetchAccountBalances(file_name, file_extension, userBalances, temp);
        for(String balance : userBalances){
            String getBalanceCode = balance.substring(0, 3);
            String getBalance = balance.substring(4);
            int castTo = Integer.valueOf(getBalanceCode);
            double convertBalanceToDouble = Double.valueOf(getBalance);
            if(castTo == secretCode){
                userBalance = convertBalanceToDouble;
            }
        }
        return userBalance;
    }
    public void StoreAccountBalances(String[] accountBalances){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\ProgramData\\IXAC BANK\\Database\\Account Balances.txt"));
            for(String userBalance : accountBalances){
                bw.write(userBalance + "\r\n");
            }
            bw.close();
        }catch(IOException e){
            System.out.println("File Error");
        }
    }
    public void updateUserBalance(int secretCode){
        String file_name = "Account Balances", file_extension = ".txt";
        int size = getArraySize();
        String[] accountBalances = new String[size];
        double[] realAccountBalances = new double[0];
        super.fetchAccountBalances(file_name, file_extension, accountBalances, realAccountBalances);
        for(int i = 0; i < accountBalances.length; i++){
            String getBalanceCode = accountBalances[i].substring(0, 3);
            String getBalance = accountBalances[i].substring(4);
            int castTo = Integer.valueOf(getBalanceCode);
            if(castTo == secretCode){
                String convertAccountBalanceToString = String.valueOf(getAccountBalance());
                String formattedBalance = getBalanceCode + "/" + convertAccountBalanceToString;
                accountBalances[i] = formattedBalance;
            }
        }
        StoreAccountBalances(accountBalances);
    }
    public int returnValidAccountCode(int entAccountNumber){
        String str = String.valueOf(entAccountNumber);
        String codeFromNumber = str.substring(6);
        int realCode = Integer.valueOf(codeFromNumber);
        return realCode;
    }
    public String returnAccountName(int secretCode, String[] UserNames){
        String username = "";
        for(String name : UserNames){
            int endPosition = name.length() - 3;
            String formattedName = name.substring(0, endPosition);
            String getNameCode = name.substring(endPosition);
            int castTo = Integer.valueOf(getNameCode);
            if(castTo == secretCode){
                username = formattedName;
            }
        }
        return username;
    }
    public int crossCheckDetails(int secretCode, int enteredAccountNumber){
        int value = 0;
        int size = getArraySize();
        String file_name = "Account Names", file_extension = ".txt";
        String[] usernames = new String[size];
        super.fetchAccountNames(file_name, file_extension, usernames);
        String username1 = returnAccountName(secretCode, usernames);
        int getRealCode = returnValidAccountCode(enteredAccountNumber);
        String username2 = returnAccountName(getRealCode, usernames);
        if(username1.equalsIgnoreCase(username2)){
            value++;
        }else{
            value += 10;
        }
        return value;
    }
    public int getUserTransactionSize(int userAccountNumber){
        String userAccountNum = String.valueOf(userAccountNumber);
        String location = "C:\\ProgramData\\IXAC BANK\\Database\\Transactions\\" + userAccountNum + ".txt";;
        int i = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(location));
            String str;
            while((str = br.readLine()) != null){
                i++;
            }
            br.close();
        }catch(IOException e){
            System.out.println("File Not Found");
        }
        return i;
    }
    public void addTransactionToDatabseOfUser(double amount, int transactionType){
        String amt = String.valueOf(amount);
        double balance = getAccountBalance();
        
        int number = getAccouuntNumber();
        String num = String.valueOf(number);
        String bal = String.valueOf(balance);
        String date = returnDateAndTime();
        String location = "C:\\ProgramData\\IXAC BANK\\Database\\Transactions\\" + num + ".txt";
        String recordTransaction = "";
        if(transactionType == 1){
            recordTransaction = "[ [Withdrawal]"+" || ["+bal+"] || ["+ amt +"] || "+date+"] ]";
        }else if(transactionType == 2){
            recordTransaction = "[ [Deposit   ]"+" || ["+bal+"] || ["+ amt +"] || "+date+"] ]";
        }
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(location, true));
            bw.write(recordTransaction + "\r\n");
            bw.close();
        }catch(IOException ex){
            System.out.println("Error Creating File!!!");
        }
    }
    public void displayTransactionsStatements(int range, String[] transactionStatements, boolean condition){
        clearScreen();
        if(condition){
            for(int i = 0; i < range; i++){
                System.out.println(transactionStatements[i]);
            }
        }else{
            for(int i = --range; i >= 1; i--){
                System.out.println(transactionStatements[i]);
            }
        }
    }
    public void fetchUserTransactions(int userAccountNumber, String file_extension, String[] transactionsStatements){
        String file_name = String.valueOf(userAccountNumber);
        String file_location = "C:\\ProgramData\\IXAC BANK\\Database\\Transactions\\" + file_name + file_extension;
        int i = 0;
        int size = getUserTransactionSize(userAccountNumber) + 1;
        String[] trackTemp = new String[size];
        try{
            BufferedReader br = new BufferedReader(new FileReader(file_location));
            String str;
            while((str = br.readLine()) != null){
                trackTemp[i] = str;
                i++;
            }
            br.close();
        }catch(IOException e){
            System.out.println("File Not Found");
        }
        String[] temp = new String[trackTemp.length];
        for(int j = 0; j < trackTemp.length; j++){
            temp[j] = trackTemp[j];
        }
        for(int l = 0; l < trackTemp.length; l++){
            transactionsStatements[l] = temp[l];
        }
    }
}