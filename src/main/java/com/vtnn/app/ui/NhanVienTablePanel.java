package com.vtnn.app.ui;

import com.vtnn.app.dao.NhanVienDAO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class NhanVienTablePanel extends JPanel {
    private JTable table;
    private NhanVienTableModel tableModel;
    private JTextField searchField;
    private JButton addButton;
    private NhanVienDAO nhanVienDAO;

    public NhanVienTablePanel() throws Exception {
        setLayout(new BorderLayout());
        nhanVienDAO = new NhanVienDAO();

        // Search bar
        JPanel topPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        topPanel.add(new JLabel("Tìm kiếm: "), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);
        addButton = new JButton("Thêm Nhân Viên");
        topPanel.add(addButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Table
        tableModel = new NhanVienTableModel(nhanVienDAO.dsNhanVien());
        table = new JTable(tableModel);
        table.setRowHeight(35);
        table.setShowGrid(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(232, 242, 254));
        table.setSelectionForeground(Color.BLACK);
        
        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(80);  // Mã NV
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Họ Tên
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Ngày Sinh
        table.getColumnModel().getColumn(3).setPreferredWidth(80);  // Giới Tính
        table.getColumnModel().getColumn(4).setPreferredWidth(120); // Số Điện Thoại
        table.getColumnModel().getColumn(5).setPreferredWidth(200); // Địa Chỉ
        table.getColumnModel().getColumn(6).setPreferredWidth(150); // Email
        table.getColumnModel().getColumn(7).setPreferredWidth(120); // Vai Trò
        table.getColumnModel().getColumn(8).setPreferredWidth(200); // Actions
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        add(scrollPane, BorderLayout.CENTER);

        // Add search functionality
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { filter(); }
            public void removeUpdate(DocumentEvent e) { filter(); }
            public void insertUpdate(DocumentEvent e) { filter(); }
            private void filter() {
                tableModel.setFilter(searchField.getText());
            }
        });

        // Add button action
        addButton.addActionListener(e -> {
            try {
                NhanVienFormDialog dialog = new NhanVienFormDialog(null);
                dialog.setVisible(true);
                if (dialog.isSaved()) {
                    refreshTable();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Lỗi: " + ex.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add action buttons to table
        table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    private void refreshTable() throws Exception {
        tableModel.setStaff(nhanVienDAO.dsNhanVien());
    }

    // Custom renderer for buttons
    private class ButtonRenderer extends JPanel implements javax.swing.table.TableCellRenderer {
        private JButton editButton;
        private JButton deleteButton;

        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            editButton = new JButton("Sửa");
            deleteButton = new JButton("Xóa");
            add(editButton);
            add(deleteButton);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Custom editor for buttons
    private class ButtonEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton editButton;
        private JButton deleteButton;
        private Object[] staff;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            editButton = new JButton("Sửa");
            deleteButton = new JButton("Xóa");
            panel.add(editButton);
            panel.add(deleteButton);

            editButton.addActionListener(e -> {
                try {
                    NhanVienFormDialog dialog = new NhanVienFormDialog(staff);
                    dialog.setVisible(true);
                    if (dialog.isSaved()) {
                        refreshTable();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel,
                        "Lỗi: " + ex.getMessage(),
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                }
                fireEditingStopped();
            });

            deleteButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(panel,
                    "Bạn có chắc chắn muốn xóa nhân viên này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        nhanVienDAO.xoaNhanVien((int)staff[0]);
                        refreshTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel,
                            "Lỗi: " + ex.getMessage(),
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
                fireEditingStopped();
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            staff = (Object[])value;
            isPushed = true;
            return panel;
        }

        public Object getCellEditorValue() {
            isPushed = false;
            return staff;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
} 