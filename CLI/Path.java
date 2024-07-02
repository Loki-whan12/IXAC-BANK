package com.IXACBANK.TERMINAL;

import java.io.File;
import java.util.Arrays;

public class Path {
    public static void main(String[] args) {
        String absolute_path = "C:\\";
        File relative_path = new File(absolute_path+"ProgramData\\IXAC BANK\\Database\\");
        String[] stuff_in_dir;
        if (relative_path.exists()){
            stuff_in_dir = relative_path.list();
            System.out.println("The following were found in the "+relative_path.getPath()+" directory");
            System.out.println(Arrays.toString(stuff_in_dir));
        }else{
            System.out.println("Sorry you aren't on windows computer\nTry to install a version that satisfies your PC!");
        }
    }
}