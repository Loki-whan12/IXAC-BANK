package com.IXACBANK;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.IXACBANK.Home.defaultBeep;

public class SignUp {
    static JFrame frame;
    static JPanel panel;
    static JTextPane sign_up_disclaimer;
    static JLabel username_label;
    static JLabel password_label;
    static JLabel first_name_label;
    static JLabel middle_name_label;
    static JLabel last_name_label;
    static JLabel age_label;
    static JLabel address_label;
    static JLabel default_initial_deposit_label;
    static JTextField username;
    static JPasswordField password;
    static JTextField first_name;
    static JTextField middle_name;
    static JTextField last_name;
    static JTextField age;
    static JTextField address;
    static JTextField default_initial_deposit;
    static JButton sign_up_btn;
    static JButton home_btn;

    SignUp (){
        frame = new JFrame("SIGN UP FORM");
        frame.setResizable(false);
        panel = new JPanel(null);
        panel.setBackground(Color.darkGray);
        sign_up_disclaimer = new JTextPane();
        sign_up_disclaimer.setEnabled(false);
        username_label = new JLabel("Username *");
        password_label = new JLabel("Password *");
        first_name_label = new JLabel("First name *");
        middle_name_label = new JLabel("Middle name");
        last_name_label = new JLabel("Last name *");
        age_label = new JLabel("Age *");
        address_label = new JLabel("Address *");
        default_initial_deposit_label = new JLabel("Initial deposit");
        username = new JTextField();
        password = new JPasswordField();
        first_name = new JTextField();
        middle_name = new JTextField();
        last_name = new JTextField();
        age = new JTextField();
        address = new JTextField();
        default_initial_deposit = new JTextField();
        default_initial_deposit.setEnabled(false);
        sign_up_btn = new JButton("Create Account");
        home_btn = new JButton("Home");
        home_btn.setBackground(Color.gray);
        home_btn.setForeground(Color.black);
        home_btn.addActionListener(SignUp::goHome);
        sign_up_btn.addActionListener(SignUp::sign_up);
        sign_up_btn.setBackground(Color.gray);
        sign_up_btn.setForeground(Color.black);
        bound_components_to_frame();
        add_components_to_frame();
        setColorsForLabelsAndComponents();
        set_sign_up_disclaimer_text();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(400, 550);
        frame.setSize(400, 550);
        frame.setVisible(true);
    }

    private static void goHome(ActionEvent actionEvent) {
        new Home();
        frame.dispose();
    }

    private void setColorsForLabelsAndComponents(){
        sign_up_disclaimer.setBackground(Color.darkGray);
        sign_up_disclaimer.setDisabledTextColor(Color.lightGray);
        username_label.setForeground(Color.lightGray);
        password_label.setForeground(Color.lightGray);
        first_name_label.setForeground(Color.lightGray);
        middle_name_label.setForeground(Color.lightGray);
        last_name_label.setForeground(Color.lightGray);
        age_label.setForeground(Color.lightGray);
        address_label.setForeground(Color.lightGray);
        username.setBackground(Color.gray);
        username.setForeground(Color.black);
        password.setBackground(Color.gray);
        password.setForeground(Color.black);
        first_name.setBackground(Color.gray);
        first_name.setForeground(Color.black);
        middle_name.setBackground(Color.gray);
        middle_name.setForeground(Color.black);
        last_name.setBackground(Color.gray);
        last_name.setForeground(Color.black);
        age.setBackground(Color.gray);
        age.setForeground(Color.black);
        address.setBackground(Color.gray);
        address.setForeground(Color.black);
        default_initial_deposit_label.setForeground(Color.lightGray);
        default_initial_deposit.setBackground(Color.gray);
        default_initial_deposit.setDisabledTextColor(Color.black);
        sign_up_btn.setBackground(Color.gray);
        sign_up_btn.setForeground(Color.black);
    }
    private void set_sign_up_disclaimer_text() {
        sign_up_disclaimer.setText("""
                This is the user registration form for IXAC Bank
                All information you provide are kept safe and secure
                Some information are required whiles other optional
                * indicates required and those without optional
                Password must be greater than 5 digits
                """);
        default_initial_deposit.setText("$100.00");
    }
    private static void sign_up(ActionEvent actionEvent) {
        boolean[] information = new boolean[1];
        default_initial_deposit.setText("100.00");
        information[0] = true;
        storeUserDataInDatabase(information);
        if (verifyAllInput()) {
            if (information[0]) {
                defaultBeep();
                new PopUP(frame, "Your account has been created successfully" +
                        "\nPlease login to access your account", "Account Created", 1);
                new Login();
                frame.dispose();
            } else {
                defaultBeep();
                new PopUP(frame, "Sorry there's a problem on our side" +
                        "\nPlease bear with us as we fix it!", "Problem", 2);
            }
        }else{
            defaultBeep();
            new PopUP(frame, "Sorry you haven't provided all the required input",
                    "Invalid input", 2);
        }
    }

    private static void storeUserDataInDatabase(boolean[] information) {
        try {
            String mid_name;
            if (middle_name.getText().length() > 1){
                mid_name = middle_name.getText();
            }else{
                mid_name = "";
            }
            String command1 = "INSERT INTO biodata values('" + username.getText() + "','" + first_name.getText() + "','"
                    + mid_name + "','" + last_name.getText() + "'," + age.getText() + ",'" + address.getText()
                    + "')";
            String command2 = "INSERT INTO user_credentials values('"+ username.getText() + "' , '"
                    + String.valueOf(password.getPassword()) + "')";
            String command3 = "INSERT INTO account_info values('"+ username.getText() + "', 'SAVINGS', 'BLUE', 'NOT ACTIVE'," +
                    10000.00 + ")";
            String command4 = "INSERT INTO account_balances values('"+ username.getText() + "'," +
                    default_initial_deposit.getText() + ")";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ixacbank", "root", "");
            Statement statement = connection.createStatement();
            statement.executeUpdate(command1);
            statement.executeUpdate(command2);
            statement.executeUpdate(command3);
            statement.executeUpdate(command4);
            statement.close();
            connection.close();
        } catch (CommunicationsException communicationsException){
            defaultBeep();
            information[0] = false;
        }
        catch (SQLException sqlException){
            defaultBeep();
            new PopUP(frame, sqlException.toString(), "Error", 0);
        }
    }

    private static boolean verifyAllInput() {
        boolean is_all_valid = false;
        if (username.getText().length() <= 1 ){
            is_all_valid = false;
        }else {
            if (password.getPassword().length <= 5){
                is_all_valid = false;
            }else {
                if (first_name.getText().length() <= 1 || last_name.getText().length() <= 1 ||
                        age.getText().length() < 1 || address.getText().length() <= 1){
                    is_all_valid = false;
                }else {
                    is_all_valid = true;
                }
            }
        }
        return  is_all_valid;
    }

    private void bound_components_to_frame(){
        sign_up_disclaimer.setBounds(50, 10, 300, 150);
        home_btn.setBounds(150, 150, 100, 20);
        username_label.setBounds(50, 230, 100, 15);
        username.setBounds(200, 230, 80, 20);
        password_label.setBounds(50, 260, 100, 15);
        password.setBounds(200, 260, 80, 20);
        first_name_label.setBounds(50, 290, 100, 15);
        first_name.setBounds(200, 290, 100, 20);
        middle_name_label.setBounds(50, 320, 100, 15);
        middle_name.setBounds(200, 320, 100, 20);
        last_name_label.setBounds(50, 350, 100, 15);
        last_name.setBounds(200, 350, 100, 20);
        age_label.setBounds(50, 380, 80, 15);
        age.setBounds(200, 380, 30, 20);
        address_label.setBounds(50, 410, 100, 15);
        address.setBounds(200, 410, 120, 20);
        default_initial_deposit_label.setBounds(50, 440, 100, 15);
        default_initial_deposit.setBounds(200, 440, 80, 20);
        sign_up_btn.setBounds(125, 470, 150, 20);
    }
    private void add_components_to_frame(){
        panel.add(sign_up_disclaimer);panel.add(username);panel.add(password);
        panel.add(first_name);panel.add(middle_name);panel.add(last_name);
        panel.add(age);panel.add(address);panel.add(default_initial_deposit);
        panel.add(username_label);panel.add(password_label);panel.add(first_name_label);
        panel.add(middle_name_label);panel.add(last_name_label);panel.add(age_label);
        panel.add(address_label);panel.add(default_initial_deposit_label);
        panel.add(sign_up_btn);panel.add(home_btn);
        frame.add(panel);
    }
}
