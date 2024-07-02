package com.IXACBANK;

import javax.swing.*;
import java.awt.event.ActionEvent;

class Main {
    static JFrame frame = new JFrame();

    public static void main(String[] args) {
        JButton btn = new JButton("Click");
        btn.setBounds(180, 230, 80, 20);
        frame.add(btn);
        btn.addActionListener(Main::btn);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private static void btn(ActionEvent actionEvent) {
        new PopUP(frame, "An IOException has occurred!", "Error", 0);
    }
}
