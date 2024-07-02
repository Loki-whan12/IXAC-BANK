package com.IXACBANK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static com.IXACBANK.MainPage.*;

public class Settings {
    static JFrame settings_frame;
    static JPanel settings_panel;
    static JButton back_btn;
    static JButton save_btn;
    static JLabel info;
    Settings(){
        settings_frame = new JFrame("IXAC BANK SETTINGS");
        settings_panel = new JPanel(null);
        info = new JLabel("Settings");
        back_btn = new JButton("Back");
        back_btn.setBackground(Color.gray);
        back_btn.setForeground(Color.black);
        save_btn = new JButton("Save changes");
        save_btn.setForeground(Color.black);
        save_btn.setBackground(Color.gray);
        settings_panel.setBackground(Color.darkGray);
        info.setForeground(Color.black);
        settings_frame.add(settings_panel);
        settings_panel.add(info);
        settings_panel.add(back_btn);
        settings_panel.add(save_btn);
        info.setBounds(350, 390, 100, 20);
        back_btn.setBounds(5, 10, 80, 20);
        save_btn.setBounds(340, 720, 120, 20);
        back_btn.addActionListener(Settings::back);
        save_btn.addActionListener(Settings::update);
        settings_frame.setResizable(false);
        settings_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settings_frame.setSize(height, width);
        settings_frame.setVisible(true);
    }

    private static void update(ActionEvent actionEvent) {
        System.out.println("save changes button clicked!");
    }

    private static void back(ActionEvent actionEvent) {
        main_page_frame.setVisible(true);
        settings_frame.dispose();
    }
}
