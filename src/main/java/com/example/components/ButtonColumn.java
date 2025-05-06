package com.example.components;

import com.example.utils.UIUtils;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
    private JPanel panel;
    private JButton editButton;
    private JButton deleteButton;
    private int row;
    private ActionListener editListener;
    private ActionListener deleteListener;

    public ButtonColumn(ActionListener editListener, ActionListener deleteListener) {
        this.editListener = editListener;
        this.deleteListener = deleteListener;
        
        // Create panel with buttons
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.setOpaque(true);
        
        // Create edit button
        editButton = new JButton();
        editButton.setIcon(UIUtils.createFontAwesomeIcon(FontAwesomeIcon.EDIT, new Color(0, 120, 212)));
        editButton.setToolTipText("Edit");
        editButton.setBorderPainted(false);
        editButton.setContentAreaFilled(false);
        editButton.setFocusPainted(false);
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editButton.addActionListener(this);
        
        // Create delete button
        deleteButton = new JButton();
        deleteButton.setIcon(UIUtils.createFontAwesomeIcon(FontAwesomeIcon.TRASH, new Color(220, 53, 69)));
        deleteButton.setToolTipText("Delete");
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(this);
        
        // Add buttons to panel
        panel.add(editButton);
        panel.add(deleteButton);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            panel.setBackground(table.getSelectionBackground());
        } else {
            panel.setBackground(table.getBackground());
        }
        return panel;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.row = row;
        panel.setBackground(table.getSelectionBackground());
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton && editListener != null) {
            ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(row));
            editListener.actionPerformed(event);
        } else if (e.getSource() == deleteButton && deleteListener != null) {
            ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(row));
            deleteListener.actionPerformed(event);
        }
        fireEditingStopped();
    }
} 