package com.example.models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ClientTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Name", "Gender", "Phone", "Address", "Email", "Action"};
    private List<Object[]> data;

    public ClientTableModel() {
        this.data = new ArrayList<>();
        // Add some mock data
        addMockData();
    }

    private void addMockData() {
        // Mock data based on the database schema
        data.add(new Object[]{25001, "Nguyễn Văn An", "Nam", "0901234567", "123 Nguyễn Văn Cừ, Q.5, TP.HCM", "nguyenvanan@example.com", ""});
        data.add(new Object[]{25002, "Trần Thị Bình", "Nữ", "0912345678", "456 Lê Lợi, Q.1, TP.HCM", "tranthibinh@example.com", ""});
        data.add(new Object[]{25003, "Phạm Văn Cường", "Nam", "0923456789", "789 Cách Mạng T8, Q.3, TP.HCM", "phamvancuong@example.com", ""});
        data.add(new Object[]{25004, "Lê Thị Dung", "Nữ", "0934567890", "321 Võ Văn Tần, Q.10, TP.HCM", "lethidung@example.com", ""});
        data.add(new Object[]{25005, "Hoàng Văn Em", "Nam", "0945678901", "654 Nguyễn Thị Minh Khai, Q.1, TP.HCM", "hoangvanem@example.com", ""});
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 6) { // Action column
            return String.class;
        }
        if (data.isEmpty()) {
            return Object.class;
        }
        Object value = getValueAt(0, columnIndex);
        return value != null ? value.getClass() : Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Make the Action column editable to handle button clicks
        return columnIndex == 6;
    }

    public void setData(List<Object[]> newData) {
        this.data = newData;
        fireTableDataChanged();
    }

    public Object[] getRowData(int rowIndex) {
        return data.get(rowIndex);
    }
} 