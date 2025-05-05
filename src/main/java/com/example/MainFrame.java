package com.example;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JLabel welcomeLabel;
    private JButton clickButton;
    private int clickCount = 0;

    public MainFrame() {
        // Set up the frame
        setTitle("Java Swing Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create and set up the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create welcome label
        welcomeLabel = new JLabel("Welcome to Java Swing!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Create button
        clickButton = new JButton("Click Me!");
        clickButton.addActionListener(e -> {
            clickCount++;
            welcomeLabel.setText("Button clicked " + clickCount + " times!");
        });
        mainPanel.add(clickButton, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        // Run the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
} 