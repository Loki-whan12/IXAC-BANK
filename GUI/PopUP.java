package com.IXACBANK;

import javax.swing.*;
import java.awt.*;

public class PopUP {
    private String message;
    PopUP(Frame frame, String message, String title, int pop_up_img_id){
        this.message = message;
        JOptionPane.showMessageDialog(frame, this.message, title, pop_up_img_id);
    }
}
