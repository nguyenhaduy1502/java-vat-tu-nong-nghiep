package com.vtnn.app.ui;

import com.vtnn.app.models.NguoiDungDTO;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JPanel implements TableCellRenderer {
    private JButton editButton;
    private JButton deleteButton;

    public ButtonRenderer() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 8, 0));
        setOpaque(true);
        
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        
        // Style the buttons
        styleEditButton(editButton);
        styleDeleteButton(deleteButton);
        
        add(editButton);
        add(deleteButton);
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
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            editButton.setBackground(new Color(220, 220, 220));
            deleteButton.setBackground(new Color(255, 200, 200)); // Slightly darker red when selected
        } else {
            setBackground(table.getBackground());
            editButton.setBackground(new Color(240, 240, 240));
            deleteButton.setBackground(new Color(255, 220, 220));
        }
        return this;
    }
} 