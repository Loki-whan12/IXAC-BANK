package com.IXACBANK.TERMINAL;

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
public class Read extends Time{
    public void fetchAccountNames(String file_name, String file_extension, String[] array){
        String file_location = "C:\\ProgramData\\IXAC BANK\\Database\\" + file_name + file_extension;
        int i = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(file_location));
            String str;
            while((str = br.readLine()) != null){
                array[i] = str;
                i++;
            }
            br.close();
        }catch(IOException e){
            System.out.println("File Not Found");
        }
    }
    public void fetchAccountBalances(String file_name, String file_extension, String[] array, double[] accountBalances){
        String file_location = "C:\\ProgramData\\IXAC BANK\\Database\\" + file_name + file_extension;
        int i = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(file_location));
            String str;
            while((str = br.readLine()) != null){
                array[i] = str;
                i++;
            }
            br.close();
        }catch(IOException e){
            System.out.println("File Not Found");
        }
    }
    public void fetchAccountNumbers(String file_name, String file_extension, String[] array, int[] accountNumbers){
        String file_location = "C:\\ProgramData\\IXAC BANK\\Database\\" + file_name + file_extension;
        int i = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(file_location));
            String str;
            while((str = br.readLine()) != null){
                array[i] = str;
                i++;
            }
            br.close();
        }catch(IOException e){
            System.out.println("File Not Found");
        }
    }
    public String ReadUniqueNumber(){
        String file_location = "C:\\ProgramData\\IXAC BANK\\Database\\Unique Number.txt";
        String str = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file_location));
            str = br.readLine();
            br.close();
        }catch(IOException e){
            System.out.println("File Not Found");
        }
        return str;
    }
    public int ReadDatabseAccountNamesCounter(){
        String file_location = "C:\\ProgramData\\IXAC BANK\\Database\\Account Names.txt";
        int i = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(file_location));
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
    public void fetchLogs(String[] logs){
        int i = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("C:\\ProgramData\\IXAC BANK\\Logs\\log.txt"));
            String str;
            while((str = br.readLine()) != null){
                logs[i] = str;
                i++;
            }
            br.close();
        }catch(IOException ex){
            System.out.println("Error fetching log!!!");
        }
    }
    public int returnLogsSize(){
        int i = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("C:\\ProgramData\\IXAC BANK\\Logs\\log.txt"));
            String str;
            while((str = br.readLine()) != null){
                i++;
            }
            br.close();
        }catch(IOException ex){
            System.out.println("Error getting log size!!!");
        }
        return i;
    }
    public String getLogValue(String key){
        String[] logs = new String[returnLogsSize()];
        String value = "";
        fetchLogs(logs);
        for(int i = 0; i < logs.length; i++){
            String keyFromLog = logs[i].substring(0, key.length());
            String valueFromLog = logs[i].substring(key.length()+1);
            if(keyFromLog.equalsIgnoreCase(key)){
                value = valueFromLog;
                break;
            }
        }
        return value;
    }
}