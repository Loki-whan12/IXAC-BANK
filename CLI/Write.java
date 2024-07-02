package com.IXACBANK.TERMINAL;

import java.io.*;
public class Write extends Read{
    public void StoreAccountName(String accountName){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\ProgramData\\IXAC BANK\\Database\\Account Names.txt", true));
            bw.write(accountName + "\r\n");
            bw.close();
        }catch(IOException e){
            System.out.println("File Error");
        }
    }
    public void StoreAccountBalance(String accountBalance){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\ProgramData\\IXAC BANK\\Database\\Account Balances.txt", true));
            bw.write(accountBalance + "\r\n");
            bw.close();
        }catch(IOException e){
            System.out.println("File Error");
        }
    }
    public void StoreAccountNumber(int accountNumber){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\ProgramData\\IXAC BANK\\Database\\Account Numbers.txt", true));
            String castAccountNumber = String.valueOf(accountNumber);
            bw.write(castAccountNumber + "\r\n");
            bw.close();
        }catch(IOException e){
            System.out.println("File Error");
        }
    }
    public void SetUniqueNumber(int uniqueNumber){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\ProgramData\\IXAC BANK\\Database\\Unique Number.txt"));
            String castuniqueNumber = String.valueOf(uniqueNumber);
            bw.write(castuniqueNumber);
            bw.close();
        }catch(IOException e){
            System.out.println("File Error");
        }
    }
    public void CreateTrackTransaction(int userAccountNumber){
        String userAccountNum = String.valueOf(userAccountNumber);
        String location = "C:\\ProgramData\\IXAC BANK\\Database\\Transactions\\" + userAccountNum + ".txt";
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(location));
            bw.close();
        }catch(IOException ex){
            System.out.println("Error Creating File!!!");
        }
    }
    public void createLogFile(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\ProgramData\\IXAC BANK\\Logs\\log.txt"));
            bw.write("IsNewUser:true\r\n");
            bw.close();
        }catch(IOException ex){
            System.out.println("Error creating log File!!!");
        }
    }
    public void updateLogValue(String key, String value){
        String[] logs = new String[returnLogsSize()];
        fetchLogs(logs);
        for(int i = 0; i < logs.length; i++){
            String keyFromLog = logs[i].substring(0, key.length());
            String valueFromLog = logs[i].substring(key.length()+1);
            if(keyFromLog.equalsIgnoreCase(key)){
                valueFromLog = value;
            }
            logs[i] = keyFromLog+":"+valueFromLog;
        }
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\ProgramData\\IXAC BANK\\Logs\\log.txt"));
            for(String log : logs){
                bw.write(log + "\r\n");
            }
            bw.close();
        }catch(IOException ex){
            System.out.println("Error Updating log!!!");
        }
    }
    public void addKeyToLog(String key, String value){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\ProgramData\\IXAC BANK\\Logs\\log.txt", true));
            bw.write(key+":"+value+"\r\n");
            bw.close();
        }catch(IOException e){
            System.out.println("Coudn't add key to log!!!");
        }
    }
}