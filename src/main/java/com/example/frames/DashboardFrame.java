package com.example.frames;

import com.example.LoginFrame;
import com.example.models.User;
import com.example.utils.UIUtils;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private final User user;

    public DashboardFrame(User user) {
        this.user = user;
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIUtils.PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(800, 60));

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getEmail() + " (" + user.getRole() + ")", SwingConstants.LEFT);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        headerPanel.add(welcomeLabel, BorderLayout.WEST);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> handleLogout());
        headerPanel.add(logoutButton, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Content
        JPanel contentPanel = new JPanel();
        contentPanel.add(new JLabel("Dashboard Content - Role: " + user.getRole()));
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void handleLogout() {
        new LoginFrame().setVisible(true);
        this.dispose();
    }
} 