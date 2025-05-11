package com.vtnn.app.ui;

import com.vtnn.app.models.NguoiDungDTO;
import com.vtnn.app.models.UserRole;
import com.vtnn.app.dao.NguoiDungDAO;
import com.vtnn.app.dao.NhanVienDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NguoiDungFormDialog extends JDialog {
    private JPasswordField passwordField;
    private JComboBox<Object[]> staffBox;
    private boolean saved = false;
    private NguoiDungDTO user;
    private NguoiDungDAO dao;
    private NhanVienDAO nhanVienDAO;
    private JTextField usernameField;

    public NguoiDungFormDialog(NguoiDungDTO user) throws Exception {
        dao = new NguoiDungDAO();
        nhanVienDAO = new NhanVienDAO();
        setTitle(user == null ? "Thêm Người Dùng" : "Sửa Người Dùng");
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
        
        // Staff selection field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Nhân viên:"), gbc);
        gbc.gridx = 1;
        staffBox = new JComboBox<>();
        staffBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    Object[] staff = (Object[])value;
                    setText(staff[1].toString()); // Display staff name
                }
                return this;
            }
        });
        populateStaffBox();
        formPanel.add(staffBox, gbc);
        
        // Username field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Tên đăng nhập:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(20);
        formPanel.add(usernameField, gbc);
        
        // Password field
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Mật khẩu:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        formPanel.add(passwordField, gbc);
        
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
                if (usernameField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Vui lòng nhập tên đăng nhập", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (passwordField.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(this, 
                        "Vui lòng nhập mật khẩu", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get selected staff
                Object[] selectedStaff = (Object[])staffBox.getSelectedItem();
                if (selectedStaff == null) {
                    JOptionPane.showMessageDialog(this, 
                        "Vui lòng chọn nhân viên", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create DTO
                NguoiDungDTO newUser = new NguoiDungDTO(
                    usernameField.getText().trim(),
                    new String(passwordField.getPassword()),
                    (int)selectedStaff[0]
                );

                if (user == null) {
                    dao.insert(newUser);
                } else {
                    dao.update(newUser);
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
        if (user != null) {
            // Find and select the staff in the combo box
            for (int i = 0; i < staffBox.getItemCount(); i++) {
                Object[] staff = (Object[])staffBox.getItemAt(i);
                if ((int)staff[0] == user.getMaNV()) {
                    staffBox.setSelectedIndex(i);
                    break;
                }
            }
            staffBox.setEnabled(false); // Disable staff selection when editing
            usernameField.setText(user.getTenDangNhap());
            passwordField.setText(user.getMatKhau());
        }
    }

    private void populateStaffBox() throws Exception {
        List<Object[]> staffList = nhanVienDAO.dsNhanVien();
        List<NguoiDungDTO> userList = dao.findAll();
        java.util.Set<Integer> usedMaNV = new java.util.HashSet<>();
        for (NguoiDungDTO u : userList) {
            usedMaNV.add(u.getMaNV());
        }
        for (Object[] staff : staffList) {
            int maNV = (int) staff[0];
            // If adding, only show staff not already used. If editing, always show the current user's staff.
            if (user == null) {
                if (!usedMaNV.contains(maNV)) {
                    staffBox.addItem(staff);
                }
            } else {
                if (!usedMaNV.contains(maNV) || maNV == user.getMaNV()) {
                    staffBox.addItem(staff);
                }
            }
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