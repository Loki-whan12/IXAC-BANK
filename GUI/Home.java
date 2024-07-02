package com.IXACBANK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class Home extends PreTest{
    static JFrame home_frame;
    static JPanel panel;
    static JTextArea disclaimer;
    static JButton login_btn;
    static JButton sign_up_btn;

    Home(){
        home_frame = new JFrame("IXAC BANK");
        home_frame.setResizable(false);
        panel = new JPanel(null);
        panel.setBackground(Color.darkGray);
        disclaimer = new JTextArea();
        disclaimer.setEnabled(false);
        disclaimer.setBackground(Color.darkGray);
        disclaimer.setDisabledTextColor(Color.lightGray);
        login_btn = new JButton("Login");
        login_btn.setBackground(Color.gray);
        login_btn.setForeground(Color.black);
        login_btn.addActionListener(Home::login);
        sign_up_btn = new JButton("Sign Up");
        sign_up_btn.addActionListener(Home::sign_up);
        sign_up_btn.setBackground(Color.gray);
        sign_up_btn.setForeground(Color.black);
        if (isNewUser()) {
            createLogFile();
            addToLogs("IsNewUser");
            updateLogValue("IsNewUser", "true");
            new_user_disclaimer_text();
            updateLogValue("IsNewUser", "false");
        }else{
            user_disclaimer_text();
        }
        bound_components_to_frame();
        add_components_to_frame();
        home_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(400, 500);
        home_frame.setSize(400, 500);
        home_frame.setVisible(true);
    }

    public static void defaultBeep(){
        Toolkit.getDefaultToolkit().beep();
    }

    private boolean isNewUser(){
        File log = new File("C:\\Users\\LOKIWHAN\\Desktop\\Stuff\\Projects\\IXAC BANK\\src\\Logs\\log.txt");
        boolean is_new_user = log.exists();
        if (is_new_user){
            return false;
        }else {
            return true;
        }
    }

    private static void sign_up(ActionEvent actionEvent) {
        new SignUp();
        home_frame.dispose();
    }

    private static void login(ActionEvent e) {
        new Login();
        home_frame.setVisible(false);
    }

    private void bound_components_to_frame(){
        disclaimer.setBounds(15,25,360, 300);
        login_btn.setBounds(150, 350, 100, 20);
        sign_up_btn.setBounds(150, 390, 100, 20);
    }
    private void add_components_to_frame(){
        panel.add(disclaimer);
        panel.add(login_btn);
        panel.add(sign_up_btn);
        home_frame.add(panel);
    }
    private void new_user_disclaimer_text(){
        disclaimer.setText("""
                \tWelcome to IXAC BANK
                
                The following are the rules of the bank
                On the usage of this software
                This is the main Software which you can access all our services
                You are required to use the software according to the rules below
                
                1.You are supposed to give only valid inputs
                2.You must agree to the T&C's when creating an account
                3.Failure to so will result to the termination of the software
                """);
    }

    private void user_disclaimer_text(){
        disclaimer.setText("""
                \t-----------------------------------------
                \t-------------IXAC BANK-------------
                \t----------Welcome back-----------
                \t-----------------------------------------
                """);
    }

    public static void main(String[] args) {
        new Home();
    }
}
