package com.IXACBANK;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Date;

import static com.IXACBANK.Home.defaultBeep;
import static com.IXACBANK.Login.password;
import static com.IXACBANK.Login.username;

//five panels on the main page main_page_frame
public class MainPage {
    static JFrame main_page_frame;
    static JPanel background_panel;
    static JLabel bank_name_label;
    static JButton account_menu;
    static JPopupMenu popupMenu;
    static JLabel user_name;
    static JLabel account_balance_label;
    static JLabel account_balance;
    static JButton refresh_account_balance;
    static JButton tasks1;
    static JButton tasks2;
    static JButton tasks3;
    static JButton tasks4;
    static JButton tasks5;
    static JPanel account_info_panel;
    static JPanel bottom_top_ruler;
    static JMenuItem refresh_balance_menu_item;
    static JMenuItem profile_menu_item;
    static JMenuItem settings_menu_item;
    static JMenuItem about_menu_item;
    static JMenuItem logout_menu_item;
    static JLabel account_type;
    static JLabel account_status;
    static JLabel premium_account_status;
    static JLabel account_limit;
    static JLabel bank_name_bottom;
    static JLabel time_logged_in_system;
    static String[] account_information = new String[7];
    final static int width = 800;
    final static int height = 800;

    MainPage(String[] login_details){
        // account_information[0] = login_details[1];
        account_information[0] = "lokiwhan";
        getAccountInformation();


        instantiateAllObjects(account_information);
        addComponentsToPanel();
        AddActionListenersToComponents();
        boundComponentsToPanel();
        addColorToComponents();
        addStyleToComponents();

        main_page_frame.setSize(width, height);
        background_panel.setSize(width, height);

        main_page_frame.setResizable(false);
        main_page_frame.setVisible(true);
        main_page_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addStyleToComponents() {
        bank_name_label.setFont(new Font("Sans-Serif", Font.BOLD, 30));
        user_name.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        account_balance_label.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        account_balance.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        account_type.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        account_status.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        premium_account_status.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        account_limit.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        bank_name_bottom.setFont(new Font("Sans-Serif", Font.ITALIC, 12));
        time_logged_in_system.setFont(new Font("Sans-Serif", Font.BOLD, 12));

        account_menu.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.darkGray, Color.black));
        refresh_account_balance.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.black, Color.darkGray));
    }

    private void boundComponentsToPanel() {
        bank_name_label.setBounds(5, 0, 200, 50);
        account_menu.setBounds(720, 10, 47, 45);
        user_name.setBounds(300, 50, 200, 25);
        account_balance_label.setBounds(275, 80, 200, 20);
        account_balance.setBounds(280, 100, 200, 20);
        refresh_account_balance.setBounds(440, 100, 27, 25);
        tasks1.setBounds(100, 265, 200, 30);
        tasks2.setBounds(100, 345, 200, 30);
        tasks3.setBounds(100, 425, 200, 30);
        tasks4.setBounds(100, 505, 200, 30);
        tasks5.setBounds(100, 585, 200, 30);
        account_info_panel.setBounds(400, 300, 370, 200);
        account_info_panel.setVisible(false);
        bottom_top_ruler.setBounds(0, 734, width, 1);
        account_type.setBounds(400, 350, 300, 25);
        account_status.setBounds(400, 400, 300, 20);
        premium_account_status.setBounds(400, 450, 300, 20);
        account_limit.setBounds(400 , 500, 300 , 20);
        bank_name_bottom.setBounds(5,736,100,20);
        time_logged_in_system.setBounds(530,736,250,20);
    }

    private void AddActionListenersToComponents() {
        account_menu.addActionListener(MainPage::accountMenuPopUp);
        profile_menu_item.addActionListener(MainPage::profile);
        refresh_balance_menu_item.addActionListener(MainPage::refreshBalance);
        settings_menu_item.addActionListener(MainPage::setting);
        about_menu_item.addActionListener(MainPage::about);
        logout_menu_item.addActionListener(MainPage::logoutUser);
        refresh_account_balance.addActionListener(MainPage::refreshBalance);
        tasks1.addActionListener(MainPage::depositCash);
        tasks2.addActionListener(MainPage::withdrawCash);
        tasks3.addActionListener(MainPage::checkStatements);
        tasks4.addActionListener(MainPage::loanApplication);
        tasks5.addActionListener(MainPage::premiumApplication);
    }

    private static void premiumApplication(ActionEvent actionEvent) {
        defaultBeep();
        new PopUP(main_page_frame,
                "Sorry we're still working on this feature\n" +
                        "Expect it in future updates!",
                "Coming feature",
                1);
    }

    private static void loanApplication(ActionEvent actionEvent) {
        defaultBeep();
        new PopUP(main_page_frame,
                "Sorry we're still working on this feature\n" +
                        "Expect it in future updates!",
                "Coming feature",
                1);
    }

    private static void checkStatements(ActionEvent actionEvent) {
    }

    private static void withdrawCash(ActionEvent actionEvent) {
    }

    private static void depositCash(ActionEvent actionEvent) {
    }

    private void addColorToComponents() {
        bank_name_label.setForeground(Color.lightGray);
        background_panel.setBackground(Color.darkGray);
        account_balance_label.setForeground(Color.lightGray);
        account_balance.setForeground(Color.lightGray);
        tasks1.setBackground(Color.gray);
        tasks1.setForeground(Color.black);
        tasks2.setBackground(Color.gray);
        tasks2.setForeground(Color.black);
        tasks3.setBackground(Color.gray);
        tasks3.setForeground(Color.black);
        tasks4.setBackground(Color.gray);
        tasks4.setForeground(Color.black);
        tasks5.setBackground(Color.gray);
        tasks5.setForeground(Color.black);
        account_info_panel.setBackground(Color.gray);
        bottom_top_ruler.setBackground(Color.black);
        user_name.setForeground(Color.lightGray);
        account_type.setForeground(Color.lightGray);
        account_status.setForeground(Color.lightGray);
        premium_account_status.setForeground(Color.lightGray);
        account_limit.setForeground(Color.lightGray);
        bank_name_bottom.setForeground(Color.black);
        time_logged_in_system.setForeground(Color.black);
    }


    private void addComponentsToPanel() {
        main_page_frame.add(background_panel);
        background_panel.add(account_balance_label);
        background_panel.add(account_balance);
        background_panel.add(refresh_account_balance);
        background_panel.add(bottom_top_ruler);
        background_panel.add(user_name);
        background_panel.add(bank_name_label);
        background_panel.add(account_menu);
        background_panel.add(tasks1);
        background_panel.add(tasks2);
        background_panel.add(tasks3);
        background_panel.add(tasks4);
        background_panel.add(tasks5);
        background_panel.add(account_info_panel);
        background_panel.add(account_type);
        background_panel.add(account_status);
        background_panel.add(premium_account_status);
        background_panel.add(account_limit);
        background_panel.add(bank_name_bottom);
        background_panel.add(time_logged_in_system);

        popupMenu.add(refresh_balance_menu_item);
        popupMenu.add(profile_menu_item);
        popupMenu.add(settings_menu_item);
        popupMenu.add(about_menu_item);
        popupMenu.add(logout_menu_item);
    }

    private void instantiateAllObjects(String[] account_information) {
        main_page_frame = new JFrame("IXAC BANK");
        Icon myAccountIcon = new ImageIcon("C:\\Users\\LOKIWHAN\\Desktop\\img5.png");
        account_menu = new JButton(myAccountIcon);
        popupMenu = new JPopupMenu();
        tasks1 = new JButton("Deposit Cash");
        tasks2 = new JButton("Withdraw Cash");
        tasks3 = new JButton("Check Statement");
        tasks4 = new JButton("Apply For Loan");
        tasks5 = new JButton("Apply For Premium");
        account_info_panel = new JPanel(null);
        background_panel = new JPanel(null);
        bottom_top_ruler = new JPanel(null);
        user_name = new JLabel("Hello "+ account_information[1]);
        bank_name_label = new JLabel("IXAC BANK");
        account_balance_label = new JLabel("Account Balance");
        account_balance = new JLabel("$"+account_information[6]);
        Icon refresh_account_balance_icon = new ImageIcon("C:\\Users\\LOKIWHAN\\Desktop\\img4.png");
        refresh_account_balance = new JButton(refresh_account_balance_icon);
        account_type = new JLabel("Account type : "+account_information[2]);
        account_status = new JLabel("Account status : "+account_information[3]);
        premium_account_status = new JLabel("Premium status : "+account_information[4]);
        account_limit = new JLabel("Account limit : $"+account_information[5]);
        bank_name_bottom = new JLabel("IXAC BANK");
        time_logged_in_system = new JLabel("Logged in at "+getTimeLoggedIn());
        refresh_balance_menu_item = new JMenuItem("Refresh balance");
        profile_menu_item = new JMenuItem("Profile");
        settings_menu_item = new JMenuItem("Settings");
        about_menu_item = new JMenuItem("About");
        logout_menu_item = new JMenuItem("Logout");
    }

    private String getTimeLoggedIn() {
        Date current_date = new Date();
        return String.valueOf(current_date);
    }

    private void getAccountInformation() {
        try {
            String command = "select A.last_name, B.Account_type, B.Account_status, B.Premium_status, B.Account_limit, " +
                    "C.balance from biodata AS A, account_info AS B, account_balances AS C where " +
                    "A.username='"+account_information[0]+"'";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ixacbank", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery(command);
            while (resultSet.next()) {
                account_information[1] = resultSet.getString(1);
                account_information[2] = resultSet.getString(2);
                account_information[3] = resultSet.getString(3);
                account_information[4] = resultSet.getString(4);
                account_information[5] = resultSet.getString(5);
                account_information[6] = resultSet.getString(6);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch (CommunicationsException communicationsException){
            defaultBeep();
            new PopUP(main_page_frame, "Sorry there's a problem on our side\nPlease bear with us as we fix it",
                    "Problem", 2);
        }
        catch (SQLException sqlException){
            defaultBeep();
            new PopUP(main_page_frame, sqlException.toString(), "Error", 0);
        }
    }

    private static void profile(ActionEvent actionEvent) {
        System.out.println("Profile menu item clicked!");
    }

    private static void logoutUser(ActionEvent actionEvent) {
        new Login();
        main_page_frame.dispose();
        username.setText(account_information[0]);
    }

    private static void about(ActionEvent actionEvent) {
        System.out.println("About button clicked");
    }

    private static void setting(ActionEvent actionEvent) {
        new Settings();
        main_page_frame.setVisible(false);
    }

    private static void refreshBalance(ActionEvent actionEvent) {
        try {
            String command = "Select balance from account_balances where username= '"+account_information[0]+"'";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ixacbank", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery(command);
            while (resultSet.next()) {
                account_information[6] = resultSet.getString(1);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch (CommunicationsException communicationsException){
            defaultBeep();
            new PopUP(main_page_frame, "Sorry there's a problem on our side\nPlease bear with us as we fix it",
                    "Problem", 2);
        }
        catch (SQLException sqlException){
            defaultBeep();
            new PopUP(main_page_frame, sqlException.toString(), "Error", 0);
        }
        updateAccountBalance();
    }

    private static void updateAccountBalance() {
        account_balance.setText("$"+account_information[6]);
    }

    private static void accountMenuPopUp(ActionEvent actionEvent) {
        popupMenu.show(main_page_frame, 660, 90);
    }

    public static void main(String[] args) {
        new MainPage(new String[2]);
    }
}
