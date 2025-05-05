package com.example.utils;

import javax.swing.*;
import java.awt.*;

public class UIUtils {
    public static final Color PRIMARY_COLOR = new Color(100, 149, 237);
    public static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 14);
    
    public static JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        return panel;
    }

    public static JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
} 