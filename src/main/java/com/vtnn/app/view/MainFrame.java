package com.vtnn.app.view;

import com.vtnn.app.models.UserRole;
import com.vtnn.app.ui.NguoiDungTablePanel;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel navPanel;
    private CardLayout cardLayout;
    private Map<String, JPanel> cardPanels;
    private UserRole currentUserRole;

    public MainFrame(UserRole userRole) {
        this.currentUserRole = userRole;
        initializeFrame();
        setupNavigation();
        setupCards();
    }

    private void initializeFrame() {
        setTitle("Hệ thống quản lý vật tư nông nghiệp");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up main panel with card layout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        cardPanels = new HashMap<>();

        // Set up navigation panel
        navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(new Color(51, 51, 51));
        navPanel.setPreferredSize(new Dimension(200, 0));

        // Add panels to frame
        add(navPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void setupNavigation() {
        // Add logo/header
        JLabel logoLabel = new JLabel("VTNN");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        navPanel.add(logoLabel);

        // Add navigation buttons based on user role
        addNavButton("Trang chủ", "home");

        switch (currentUserRole) {
            case ADMIN:
                addNavButton("Quản lý người dùng", "users");
                addNavButton("Quản lý nhân viên", "staff");
                addNavButton("Báo cáo thống kê", "reports");
                break;
            case SALES_STAFF:
                addNavButton("Bán hàng", "sales");
                addNavButton("Quản lý khách hàng", "customers");
                break;
            case ACCOUNTANT:
                addNavButton("Quản lý tài chính", "finance");
                addNavButton("Báo cáo tài chính", "financial-reports");
                break;
            case WAREHOUSE_STAFF:
                addNavButton("Quản lý kho", "inventory");
                addNavButton("Nhập hàng", "import");
                break;
        }

        // Add logout button at bottom
        navPanel.add(Box.createVerticalGlue());
        JButton logoutBtn = createNavButton("Đăng xuất", "logout");
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        navPanel.add(logoutBtn);
    }

    private void addNavButton(String text, String cardName) {
        JButton button = createNavButton(text, cardName);
        navPanel.add(button);
        navPanel.add(Box.createVerticalStrut(5));
    }

    private JButton createNavButton(String text, String cardName) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 51, 51));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 40));
        button.setPreferredSize(new Dimension(180, 40));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 70, 70));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(51, 51, 51));
            }
        });

        button.addActionListener(e -> cardLayout.show(mainPanel, cardName));
        return button;
    }

    private void setupCards() {
        // Add home panel
        JPanel homePanel = new JPanel();
        homePanel.add(new JLabel("Chào mừng đến với hệ thống quản lý vật tư nông nghiệp"));
        cardPanels.put("home", homePanel);
        mainPanel.add(homePanel, "home");

        // Add other panels based on user role
        switch (currentUserRole) {
            case ADMIN:
                try {
                    // Add user management panel
                    NguoiDungTablePanel userPanel = new NguoiDungTablePanel();
                    cardPanels.put("users", userPanel);
                    mainPanel.add(userPanel, "users");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error loading user management: " + e.getMessage());
                }
                break;
            case SALES_STAFF:
                // Add sales staff panels
                break;
            case ACCOUNTANT:
                // Add accountant panels
                break;
            case WAREHOUSE_STAFF:
                // Add warehouse staff panels
                break;
        }

        // Show home panel by default
        cardLayout.show(mainPanel, "home");
    }
}