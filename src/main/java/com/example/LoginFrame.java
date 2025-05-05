package com.example;

import com.example.frames.DashboardFrame;
import com.example.models.User;
import com.example.services.AuthService;
import com.example.utils.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {
    // Theme colors
    private static final Color PRIMARY_GREEN = new Color(76, 175, 80);
    private static final Color DARK_GREEN = new Color(56, 142, 60);
    private static final Color LIGHT_GREEN = new Color(200, 230, 201);
    private static final Color WHITE = Color.WHITE;
    
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel errorLabel;

    public LoginFrame() {
        setTitle("Agricultural Supplies Management - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, LIGHT_GREEN, 0, h, WHITE);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        // Logo/Title Panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        
        // App title
        JLabel titleLabel = new JLabel("Agricultural Supplies", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(DARK_GREEN);
        
        JLabel subtitleLabel = new JLabel("Management System", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(DARK_GREEN);
        
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(subtitleLabel, BorderLayout.SOUTH);
        titlePanel.setBorder(new EmptyBorder(0, 0, 30, 0));

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        // Email field
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        emailLabel.setForeground(DARK_GREEN);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(emailLabel, gbc);

        emailField = new JTextField("admin@testing.com"); // Default testing email
        emailField.setPreferredSize(new Dimension(300, 35));
        emailField.setMargin(new Insets(2, 10, 2, 10));
        gbc.gridy = 1;
        formPanel.add(emailField, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setForeground(DARK_GREEN);
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 0, 5, 0);
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField("admin123"); // Default testing password
        passwordField.setPreferredSize(new Dimension(300, 35));
        passwordField.setMargin(new Insets(2, 10, 2, 10));
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 0, 5, 0);
        formPanel.add(passwordField, gbc);

        // Error label
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 0, 10, 0);
        formPanel.add(errorLabel, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(300, 40));
        loginButton.setBackground(PRIMARY_GREEN);
        loginButton.setForeground(WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> handleLogin());
        
        // Button hover effect
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(DARK_GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(PRIMARY_GREEN);
            }
        });
        
        gbc.gridy = 5;
        gbc.insets = new Insets(15, 0, 5, 0);
        formPanel.add(loginButton, gbc);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Validate input
        if (!UIUtils.isValidEmail(email)) {
            errorLabel.setText("Please enter a valid email address");
            return;
        }

        if (password.isEmpty()) {
            errorLabel.setText("Password cannot be empty");
            return;
        }

        try {
            User user = AuthService.authenticate(email, password);
            // Login successful
            new DashboardFrame(user).setVisible(true);
            this.dispose();
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
} 