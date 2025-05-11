package com.vtnn.app.ui;

import com.vtnn.app.dao.NguoiDungDAO;
import com.vtnn.app.models.NguoiDungDTO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class NguoiDungTablePanel extends JPanel {
    private JTable table;
    private NguoiDungTableModel tableModel;
    private JTextField searchField;
    private JButton addButton;
    private NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();

    public NguoiDungTablePanel() throws Exception {
        setLayout(new BorderLayout());

        // Search bar
        JPanel topPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        topPanel.add(new JLabel("Search: "), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);
        addButton = new JButton("Add User");
        topPanel.add(addButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Table
        tableModel = new NguoiDungTableModel(nguoiDungDAO.findAll());
        table = new JTable(tableModel);
        table.setRowHeight(35); // Increase row height
        table.setShowGrid(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(232, 242, 254));
        table.setSelectionForeground(Color.BLACK);
        
        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(150); // Username
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Password
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // MaNV
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Role
        table.getColumnModel().getColumn(4).setPreferredWidth(200); // Actions
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        add(scrollPane, BorderLayout.CENTER);

        // Action listeners
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            public void changedUpdate(DocumentEvent e) {
                filter();
            }

            private void filter() {
                String text = searchField.getText();
                tableModel.setFilter(text);
            }
        });

        addButton.addActionListener(e -> openForm(null));

        // Table action buttons
        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1)
                .setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1)
                .setCellEditor(new ButtonEditor(this));

        // Add padding and spacing
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        searchField.setPreferredSize(new Dimension(200, 25));
        addButton.setPreferredSize(new Dimension(100, 25));
    }

    public void openForm(NguoiDungDTO user) {
        try {
            NguoiDungFormDialog dialog = new NguoiDungFormDialog(user);
            dialog.setVisible(true);
            if (dialog.isSaved()) {
                tableModel.setUsers(nguoiDungDAO.findAll());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error opening form: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteUser(NguoiDungDTO user) {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to delete?", "Warning",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                nguoiDungDAO.delete(user.getTenDangNhap());
                tableModel.setUsers(nguoiDungDAO.findAll());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error deleting user: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}