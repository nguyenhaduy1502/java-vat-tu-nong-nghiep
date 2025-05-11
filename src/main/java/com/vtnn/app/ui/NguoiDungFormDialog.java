package com.vtnn.app.ui;

import com.vtnn.app.models.NguoiDungDTO;
import com.vtnn.app.models.UserRole;
import com.vtnn.app.dao.NguoiDungDAO;
import com.vtnn.app.dao.NhanVienDAO;
import javax.swing.*;
import java.awt.*;

public class NguoiDungFormDialog extends JDialog {
    private JTextField usernameField, passwordField, maNVField;
    private JComboBox<UserRole> roleBox;
    private boolean saved = false;
    private NguoiDungDTO user;
    private NguoiDungDAO dao;
    private NhanVienDAO nhanVienDAO;

    public NguoiDungFormDialog(NguoiDungDTO user) throws Exception {
        dao = new NguoiDungDAO();
        nhanVienDAO = new NhanVienDAO();
        setTitle(user == null ? "Add User" : "Edit User");
        setModal(true);
        
        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Username field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(20);
        formPanel.add(usernameField, gbc);
        
        // Password field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JTextField(20);
        formPanel.add(passwordField, gbc);
        
        // MaNV field
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("MaNV:"), gbc);
        gbc.gridx = 1;
        maNVField = new JTextField(20);
        formPanel.add(maNVField, gbc);
        
        // Role field
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        roleBox = new JComboBox<>(UserRole.values());
        formPanel.add(roleBox, gbc);
        
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");
        
        // Style buttons
        styleButton(saveBtn);
        styleButton(cancelBtn);
        
        saveBtn.addActionListener(e -> {
            try {
                // Validate required fields
                if (usernameField.getText().trim().isEmpty() || 
                    passwordField.getText().trim().isEmpty() || 
                    maNVField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields", "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate MaNV exists
                int maNV = Integer.parseInt(maNVField.getText().trim());
                if (!nhanVienDAO.exists(maNV)) {
                    JOptionPane.showMessageDialog(this, 
                        "MaNV does not exist in the NhanVien table", 
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                NguoiDungDTO newUser = new NguoiDungDTO(
                        usernameField.getText().trim(),
                        passwordField.getText().trim(),
                        maNV,
                        (UserRole) roleBox.getSelectedItem());
                if (user == null)
                    dao.insert(newUser);
                else
                    dao.update(newUser);
                saved = true;
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "MaNV must be a valid number", 
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error: " + ex.getMessage(), 
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelBtn.addActionListener(e -> dispose());
        
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        mainPanel.add(buttonPanel);
        
        // Set user data if editing
        if (user != null) {
            usernameField.setText(user.getTenDangNhap());
            usernameField.setEnabled(false);
            passwordField.setText(user.getMatKhau());
            maNVField.setText(String.valueOf(user.getMaNV()));
            roleBox.setSelectedItem(user.getVaiTro());
        }
        
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(100, 30));
        button.setFocusPainted(false);
        button.setBackground(new Color(240, 240, 240));
        button.setForeground(new Color(51, 51, 51));
    }

    public boolean isSaved() {
        return saved;
    }
}