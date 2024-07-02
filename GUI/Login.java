package com.IXACBANK;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

import static com.IXACBANK.Home.defaultBeep;
import static com.IXACBANK.Home.home_frame;

public class Login {
    static JFrame login_frame;
    static JPanel panel;
    static JTextPane login_frame_info;
    static JLabel username_label;
    static JLabel password_label;
    static JTextField username;
    static JPasswordField password;
    static JButton login_btn;
    static JButton home_btn;

    Login(){
        login_frame = new JFrame("SIGN UP FORM");
        login_frame.setResizable(false);
        panel = new JPanel(null);
        panel.setBackground(Color.darkGray);
        login_frame_info = new JTextPane();
        login_frame_info.setEnabled(false);
        username_label = new JLabel("Username");
        password_label = new JLabel("Password");
        username = new JTextField();
        password = new JPasswordField();
        login_btn = new JButton("Login");
        home_btn = new JButton("Home");
        home_btn.addActionListener(Login::goHome);
        home_btn.setBackground(Color.gray);
        home_btn.setForeground(Color.black);
        login_btn.addActionListener(Login::login);
        addComponentsToFrame();
        boundComponentsToFrame();
        setColorsForLabelsAndComponents();
        setLoginFrameInfo();
        login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(400, 500);
        login_frame.setSize(400, 500);
        login_frame.setVisible(true);
    }

    private static void goHome(ActionEvent actionEvent) {
        home_frame.setVisible(true);
        login_frame.setVisible(false);
    }

    private void setLoginFrameInfo() {
        login_frame_info.setText("""
                --------------------------------------------
                -------------- IXAC BANK -------------
                --------------------------------------------
                -------------- User Login -------------
                --------------------------------------------
                """);
    }

    private void setColorsForLabelsAndComponents() {
        login_frame_info.setBackground(Color.darkGray);
        login_frame_info.setDisabledTextColor(Color.lightGray);
        username_label.setForeground(Color.lightGray);
        password_label.setForeground(Color.lightGray);
        username.setBackground(Color.gray);
        username.setForeground(Color.black);
        password.setBackground(Color.gray);
        password.setForeground(Color.black);
        login_btn.setBackground(Color.gray);
        login_btn.setForeground(Color.black);
    }

    private void addComponentsToFrame() {
        login_frame.add(panel);panel.add(username);panel.add(password);
        panel.add(login_frame_info);panel.add(username_label);
        panel.add(password_label);panel.add(login_btn);panel.add(home_btn);
    }

    private void boundComponentsToFrame() {
        login_frame_info.setBounds(100, 10, 200, 150);
        home_btn.setBounds(150, 170, 100, 20);
        username_label.setBounds(50, 230, 100, 15);
        username.setBounds(200, 230, 100, 20);
        password_label.setBounds(50, 270, 100, 15);
        password.setBounds(200, 270, 100, 20);
        login_btn.setBounds(150, 420, 100, 20);
    }

    private static void getUserCredentialsFromDatabase(String[] user_information){
        try {
            String command = "select username, password from user_credentials where username='"+username.getText()+"'";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ixacbank", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery(command);
            while (resultSet.next()) {
                user_information[0] = "true";
                user_information[1] = resultSet.getString(1);
                user_information[2] = resultSet.getString(2);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch (CommunicationsException communicationsException){
            user_information[3] = "false";
        }
        catch (SQLException sqlException){
            defaultBeep();
            new PopUP(login_frame, sqlException.toString(), "Error", 0);
        }
    }
    private static void login(ActionEvent actionEvent) {
        if (username.getText().length() > 1 || String.valueOf(password.getPassword()).length() > 1) {
            String[] user_information = new String[4];
            user_information[3] = "true";
            boolean[] valid_parameters = new boolean[2];
            getUserCredentialsFromDatabase(user_information);
            if (Boolean.parseBoolean(user_information[0])) {
                validate_logins(user_information, valid_parameters);
                if ((valid_parameters[0]) && (valid_parameters[1])) {
                    new MainPage(user_information);
                    login_frame.setVisible(false);
                } else {
                    if (!valid_parameters[1]) {
                        defaultBeep();
                        new PopUP(login_frame, "Sorry you entered an incorrect password!" +
                                "\nCheck the password and try again!",
                                "Login unsuccessful", 2);
                    }
                }
            } else {
                if (Boolean.parseBoolean(user_information[3])) {
                    defaultBeep();
                    new PopUP(login_frame, "No account found with your user name or password" +
                            "\nGo to the sign up section to create an account", "Login unsuccessful", 2);
                }else{
                    defaultBeep();
                    new PopUP(login_frame, "Sorry there's a problem on our side" +
                            "\nPlease bear with us as we fix it!", "Problem", 2);
                }
            }
        }else{
            defaultBeep();
            new PopUP(login_frame, "Sorry man you go to enter some input to login", "Invalid input", 2);
        }
    }

    private static void validate_logins(String[] user_information, boolean[] valid_parameters) {
        valid_parameters[0] = username.getText().equals(user_information[1]);
        valid_parameters[1] = String.valueOf(password.getPassword()).equals(user_information[2]);
    }
}
