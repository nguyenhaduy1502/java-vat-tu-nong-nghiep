package com.vtnn.app.ui;

import com.vtnn.app.models.KhachHangDTO;
import com.vtnn.app.dao.KhachHangDAO;
import javax.swing.*;
import java.awt.*;

public class KhachHangFormDialog extends JDialog {
    private JTextField tenKHField, soDienThoaiField, diaChiField, emailField;
    private JComboBox<String> gioiTinhBox;
    private boolean saved = false;
    private Object[] customer;
    private KhachHangDAO dao;

    public KhachHangFormDialog(Object[] customer) throws Exception {
        dao = new KhachHangDAO();
        setTitle(customer == null ? "Thêm Khách Hàng" : "Sửa Khách Hàng");
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
        
        // Tên KH field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Tên Khách Hàng:"), gbc);
        gbc.gridx = 1;
        tenKHField = new JTextField(20);
        formPanel.add(tenKHField, gbc);
        
        // Giới Tính field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Giới Tính:"), gbc);
        gbc.gridx = 1;
        gioiTinhBox = new JComboBox<>(new String[]{"Nam", "Nữ"});
        formPanel.add(gioiTinhBox, gbc);
        
        // Số Điện Thoại field
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Số Điện Thoại:"), gbc);
        gbc.gridx = 1;
        soDienThoaiField = new JTextField(20);
        formPanel.add(soDienThoaiField, gbc);
        
        // Địa Chỉ field
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Địa Chỉ:"), gbc);
        gbc.gridx = 1;
        diaChiField = new JTextField(20);
        formPanel.add(diaChiField, gbc);
        
        // Email field
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        formPanel.add(emailField, gbc);
        
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveBtn = new JButton("Lưu");
        JButton cancelBtn = new JButton("Hủy");
        
        // Style buttons
        styleButton(saveBtn);
        styleButton(cancelBtn);
        
        saveBtn.addActionListener(e -> {
            try {
                // Validate required fields
                if (tenKHField.getText().trim().isEmpty() || 
                    soDienThoaiField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Vui lòng điền đầy đủ thông tin bắt buộc", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create DTO
                KhachHangDTO khachHang = new KhachHangDTO();
                if (customer == null) {
                    khachHang.setMaKH(dao.taoMaKH());
                } else {
                    khachHang.setMaKH((int)customer[0]);
                }
                khachHang.setTenKH(tenKHField.getText().trim());
                khachHang.setGioiTinh(gioiTinhBox.getSelectedIndex() + 1);
                khachHang.setSoDienThoai(soDienThoaiField.getText().trim());
                khachHang.setDiaChi(diaChiField.getText().trim());
                khachHang.setEmail(emailField.getText().trim());

                // Save
                if (customer == null) {
                    dao.themKhachHang(khachHang);
                } else {
                    dao.suaKhachHang(khachHang);
                }
                
                saved = true;
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Lỗi: " + ex.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelBtn.addActionListener(e -> dispose());
        
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        mainPanel.add(buttonPanel);
        
        // Set dialog properties
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        
        // If editing, populate fields
        if (customer != null) {
            tenKHField.setText(customer[1].toString());
            gioiTinhBox.setSelectedItem(customer[2].toString());
            soDienThoaiField.setText(customer[3].toString());
            diaChiField.setText(customer[4].toString());
            emailField.setText(customer[5].toString());
        }
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