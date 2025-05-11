package com.vtnn.app.ui;

import com.vtnn.app.models.NguoiDungDTO;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton editButton;
    private JButton deleteButton;
    private NguoiDungDTO currentUser;
    private NguoiDungTablePanel tablePanel;

    public ButtonEditor(NguoiDungTablePanel tablePanel) {
        this.tablePanel = tablePanel;
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        panel.setOpaque(true);
        
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        
        // Style the buttons
        styleEditButton(editButton);
        styleDeleteButton(deleteButton);
        
        editButton.addActionListener(e -> {
            fireEditingStopped();
            tablePanel.openForm(currentUser);
        });
        
        deleteButton.addActionListener(e -> {
            fireEditingStopped();
            tablePanel.deleteUser(currentUser);
        });
        
        panel.add(editButton);
        panel.add(deleteButton);
    }
    
    private void styleEditButton(JButton button) {
        button.setPreferredSize(new Dimension(80, 25));
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        button.setBackground(new Color(240, 240, 240));
        button.setForeground(new Color(51, 51, 51));
    }
    
    private void styleDeleteButton(JButton button) {
        button.setPreferredSize(new Dimension(80, 25));
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        button.setBackground(new Color(255, 220, 220)); // Light red background
        button.setForeground(new Color(200, 0, 0)); // Dark red text
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        currentUser = (NguoiDungDTO) value;
        if (isSelected) {
            panel.setBackground(table.getSelectionBackground());
            editButton.setBackground(new Color(220, 220, 220));
            deleteButton.setBackground(new Color(255, 200, 200)); // Slightly darker red when selected
        } else {
            panel.setBackground(table.getBackground());
            editButton.setBackground(new Color(240, 240, 240));
            deleteButton.setBackground(new Color(255, 220, 220));
        }
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return currentUser;
    }
} 