package com.IXACBANK;

import java.awt.*;
import java.io.*;

import static com.IXACBANK.Home.defaultBeep;
import static com.IXACBANK.Home.home_frame;

class PreTest {
    protected void createLogFile(){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\LOKIWHAN\\Desktop\\Stuff\\Projects\\IXAC BANK\\src\\Logs\\log.txt"));
            bufferedWriter.close();
        }catch (IOException ioException){
            defaultBeep();
            new PopUP(home_frame, ioException.toString(), "Error", 0);
        }
    }
    protected void addToLogs(String value_to_add){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\LOKIWHAN\\Desktop" +
                                                  "\\Stuff\\Projects\\IXAC BANK\\src\\Logs\\log.txt", true));
            bufferedWriter.write(value_to_add+":\r\n");
            bufferedWriter.close();
        }catch (IOException ioException) {
            defaultBeep();
            new PopUP(home_frame, ioException.toString(), "Error", 0);
        }
    }
    protected void updateLogValue(String key, String value){
        String[] logs = new String[getLogsSize()];
        String[] characters_in_log;
        String key_in_log = "";
        int size, deliemeter_position = 0, index = 0;
        getLogs(logs);
        for (String log : logs){
            size = log.length();
            characters_in_log = new String[size];
            getCharactersOfLog(log, characters_in_log);
            deliemeter_position = getDeliemeterLocationInCharactersOfLog(characters_in_log);
            key_in_log = log.substring(0, --deliemeter_position);
            if (key.equalsIgnoreCase(key_in_log)){
                logs[index] = key_in_log+":"+value;
                break;
            }
            index++;
        }
        updateLogs(logs);
    }
    protected String getLogValue(String key) {
        String[] logs = new String[getLogsSize()];
        String[] characters_in_log;
        String key_in_log = "";
        String value_in_log = "";
        int size, deliemeter_position = 0, index = 0;
        getLogs(logs);
        for (String log : logs){
            size = log.length();
            characters_in_log = new String[size];
            getCharactersOfLog(log, characters_in_log);
            deliemeter_position = getDeliemeterLocationInCharactersOfLog(characters_in_log);
            key_in_log = log.substring(0, --deliemeter_position);
            if (key.equalsIgnoreCase(key_in_log)){
                value_in_log = log.substring(++deliemeter_position);
                break;
            }
            index++;
        }
        return value_in_log;
    }
    protected void updateLogs(String[] logs){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\LOKIWHAN\\Desktop" +
                                                              "\\Stuff\\Projects\\IXAC BANK\\src\\Logs\\log.txt"));
            for (String log : logs){
                bufferedWriter.write(log+"\r\n");
            }
            bufferedWriter.close();
        }catch (IOException ioException){
            defaultBeep();
            new PopUP(home_frame, ioException.toString(), "Error", 0);
        }
    }
    protected void getCharactersOfLog(String log, String[] characters_in_log){
        for (int i = 0; i < log.length(); i++){
            characters_in_log[i] = String.valueOf(log.charAt(i));
        }
    }
    protected int getDeliemeterLocationInCharactersOfLog(String[] log_characters) {
        int location_number = 0;
        for (; location_number < log_characters.length; location_number++) {

            if (log_characters[location_number].equalsIgnoreCase(":")) {
                break;
            }
        }
        location_number++;
        return location_number;
        }

    protected int getLogsSize() {
        int i = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\LOKIWHAN\\Desktop" +
                                                              "\\Stuff\\Projects\\IXAC BANK\\src\\Logs\\log.txt"));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                i++;
            }
            bufferedReader.close();
        } catch (IOException ioException) {
            defaultBeep();
            new PopUP(home_frame, ioException.toString(), "Error", 0);
        }
        return i;
    }
    protected void getLogs(String[] logs) {
        int i = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\LOKIWHAN\\Desktop" +
                                                              "\\Stuff\\Projects\\IXAC BANK\\src\\Logs\\log.txt"));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                logs[i] = str;
                i++;
            }
            bufferedReader.close();
        } catch (IOException ioException) {
            defaultBeep();
            new PopUP(home_frame, ioException.toString(), "Error", 0);
        }
    }
}
