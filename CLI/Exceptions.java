package com.IXACBANK.TERMINAL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
class Exceptions{
    public boolean validateInput(String input, String[] infoToDisplay){
        boolean validInput = false;
        try{
            int convertToInterger = Integer.valueOf(input);
            validInput = true;
        }catch(Exception ex){
            if(input.length() < 1){
                infoToDisplay[1] = "\nYou haven't entered any Input\nTry again...\n";    
            }
            infoToDisplay[0] = "\nInvalid input detected!!!\nOnly integers/numbers are allowed\nTry again...\n";
            validInput = false;
        }
        return validInput;
    }
    public void delayRun(int time){
            try{
                Thread.sleep(time*1000);
            }catch(InterruptedException ex){
                System.out.println(ex);
            }
    }
    public static void cmdCommand(String command, boolean condition){
        try{
            if(condition){
                new ProcessBuilder("cmd", "/c", command).inheritIO().start().waitFor();
            }else{
                Process process = Runtime.getRuntime().exec("cmd /c "+command);
                String str;
                System.out.println(process.getOutputStream());
                BufferedReader stdin = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while((str=stdin.readLine()) != null){
                    System.out.println(str);
                }
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public void clearScreen(){
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public boolean validateInputTransaction(String input, String[] infoToDisplay){
        boolean validInput = false;
        try{
            double convertToInterger = Double.valueOf(input);
            validInput = true;
        }catch(Exception ex){
            if(input.length() < 1){
                infoToDisplay[1] = "\nYou haven't entered any Input\nTry again...\n";    
            }
            infoToDisplay[0] = "\nInvalid input detected!!!\nOnly integers/numbers are allowed\nTry again...\n";
            validInput = false;
        }
        return validInput;
    }
    public static void main(String[] args){
        cmdCommand("dir", false);
    }
    public boolean validateName(String name){
        boolean isValidName = false;
        int counter = 0;
        String[] nameCharacters = new String[name.length()];
        for(int i = 0; i < name.length(); i++){
            nameCharacters[i] = name.substring(i, i+1);
        }
        for(String ecahCharcter : nameCharacters){
            if(ecahCharcter.equalsIgnoreCase(" ")){
                counter++;
            }
        }
        if(name.length() == counter){
            isValidName = false;
        }else{
            isValidName = true;
        }
        return isValidName;
    }
}