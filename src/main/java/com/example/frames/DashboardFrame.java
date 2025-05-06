package com.example.frames;

import com.example.LoginFrame;
import com.example.models.User;
import com.example.models.UserRole;
import com.example.utils.UIUtils;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private final User user;
    private final CardLayout cardLayout;
    private final JPanel contentPanel;

    public DashboardFrame(User user) {
        this.user = user;
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIUtils.PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(1024, 60));

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getEmail() + " (" + user.getRole() + ")", SwingConstants.LEFT);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        headerPanel.add(welcomeLabel, BorderLayout.WEST);

        // Navigation buttons
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        navPanel.setBackground(UIUtils.PRIMARY_COLOR);
        
        // Products button (available to all roles)
        JButton productsButton = UIUtils.createPrimaryButton("Products");
        productsButton.addActionListener(e -> showCard("products"));
        navPanel.add(productsButton);
        
        // Clients button (only for ADMIN and SALES_STAFF)
        if (user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.SALES_STAFF) {
            JButton clientsButton = UIUtils.createPrimaryButton("Clients");
            clientsButton.addActionListener(e -> showCard("clients"));
            navPanel.add(clientsButton);
        }
        
        // Staff button (only for ADMIN)
        if (user.getRole() == UserRole.ADMIN) {
            JButton staffButton = UIUtils.createPrimaryButton("Staff");
            staffButton.addActionListener(e -> showCard("staff"));
            navPanel.add(staffButton);
        }
        
        headerPanel.add(navPanel, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> handleLogout());
        headerPanel.add(logoutButton, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Content with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        
        // Add panels
        contentPanel.add(new ProductManagementPanel(), "products");
        contentPanel.add(new ClientManagementPanel(user), "clients");
        contentPanel.add(new StaffManagementPanel(user), "staff");
        contentPanel.add(new JPanel(), "default"); // Default empty panel

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
        
        // Show default card
        showCard("products");
    }

    private void showCard(String cardName) {
        cardLayout.show(contentPanel, cardName);
    }

    private void handleLogout() {
        new LoginFrame().setVisible(true);
        this.dispose();
    }
} 