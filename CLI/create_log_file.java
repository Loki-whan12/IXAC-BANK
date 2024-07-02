package com.IXACBANK.TERMINAL;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class create_log_file {
    public static void main(String[] args) {
        create_log();
        create_unique_number();
    }

    private static void create_unique_number() {
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\ProgramData\\IXAC BANK\\Database\\Unique Number.txt"));
            bufferedWriter.write("100");
            bufferedWriter.close();
        }catch (Exception exception){
            System.out.println(exception);
        }
    }

    private static void create_log() {
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\ProgramData\\IXAC BANK\\Logs\\log.txt"));
            bufferedWriter.write("IsNewUser:true\n");
            bufferedWriter.close();
        }catch (Exception exception){
            System.out.println(exception);
        }
    }
}
