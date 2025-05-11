package com.vtnn.app.ui;

import com.vtnn.app.models.NhanVienDTO;
import com.vtnn.app.dao.NhanVienDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NhanVienFormDialog extends JDialog {
    private JTextField hoTenField, soDienThoaiField, diaChiField, emailField;
    private JTextField ngaySinhField;
    private JComboBox<String> gioiTinhBox, vaiTroBox;
    private boolean saved = false;
    private Object[] staff;
    private NhanVienDAO dao;

    public NhanVienFormDialog(Object[] staff) throws Exception {
        dao = new NhanVienDAO();
        setTitle(staff == null ? "Thêm Nhân Viên" : "Sửa Nhân Viên");
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
        
        // Họ Tên field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Họ Tên:"), gbc);
        gbc.gridx = 1;
        hoTenField = new JTextField(20);
        formPanel.add(hoTenField, gbc);
        
        // Ngày Sinh field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Ngày Sinh (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        ngaySinhField = new JTextField(20);
        formPanel.add(ngaySinhField, gbc);
        
        // Giới Tính field
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Giới Tính:"), gbc);
        gbc.gridx = 1;
        gioiTinhBox = new JComboBox<>(new String[]{"Nam", "Nữ"});
        formPanel.add(gioiTinhBox, gbc);
        
        // Số Điện Thoại field
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Số Điện Thoại:"), gbc);
        gbc.gridx = 1;
        soDienThoaiField = new JTextField(20);
        formPanel.add(soDienThoaiField, gbc);
        
        // Địa Chỉ field
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Địa Chỉ:"), gbc);
        gbc.gridx = 1;
        diaChiField = new JTextField(20);
        formPanel.add(diaChiField, gbc);
        
        // Email field
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        formPanel.add(emailField, gbc);
        
        // Vai Trò field
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Vai Trò:"), gbc);
        gbc.gridx = 1;
        vaiTroBox = new JComboBox<>(new String[]{"Quản lý", "Nhân viên bán hàng", "Kế toán", "Nhân viên kho"});
        formPanel.add(vaiTroBox, gbc);
        
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
                if (hoTenField.getText().trim().isEmpty() || 
                    ngaySinhField.getText().trim().isEmpty() || 
                    soDienThoaiField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Vui lòng điền đầy đủ thông tin bắt buộc", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Parse date
                Date ngaySinh;
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    ngaySinh = new Date(sdf.parse(ngaySinhField.getText().trim()).getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, 
                        "Định dạng ngày không hợp lệ (yyyy-MM-dd)", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create DTO
                NhanVienDTO nhanVien = new NhanVienDTO();
                if (staff != null) {
                    nhanVien.setMaNV((int)staff[0]);
                }
                nhanVien.setHoTen(hoTenField.getText().trim());
                nhanVien.setNgaySinh(ngaySinh);
                nhanVien.setGioiTinh(gioiTinhBox.getSelectedIndex() + 1);
                nhanVien.setSoDienThoai(soDienThoaiField.getText().trim());
                nhanVien.setDiaChi(diaChiField.getText().trim());
                nhanVien.setEmail(emailField.getText().trim());
                nhanVien.setVaiTro(vaiTroBox.getSelectedIndex() + 1);

                // Save
                if (staff == null) {
                    dao.themNhanVien(nhanVien);
                } else {
                    dao.suaNhanVien(nhanVien);
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
        if (staff != null) {
            hoTenField.setText(staff[1].toString());
            ngaySinhField.setText(staff[2].toString());
            gioiTinhBox.setSelectedItem(staff[3].toString());
            soDienThoaiField.setText(staff[4].toString());
            diaChiField.setText(staff[5].toString());
            emailField.setText(staff[6].toString());
            vaiTroBox.setSelectedItem(staff[7].toString());
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