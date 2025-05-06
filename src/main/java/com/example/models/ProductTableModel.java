package com.example.models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Name", "Unit", "Import Price", "Sell Price", "Stock", "Category", "Action"};
    private List<Object[]> data;

    public ProductTableModel() {
        this.data = new ArrayList<>();
        // Add some mock data
        addMockData();
    }

    private void addMockData() {
        // Mock data based on the database schema
        data.add(new Object[]{2001, "Đạm Phú Mỹ", "Bao", 250000.0, 280000.0, 50, "Phân bón gốc", ""});
        data.add(new Object[]{2002, "Lân Văn Điển", "Bao", 220000.0, 250000.0, 60, "Phân bón gốc", ""});
        data.add(new Object[]{2011, "Phân bón lá HVP 401", "Chai", 35000.0, 45000.0, 100, "Phân bón lá", ""});
        data.add(new Object[]{2021, "Thuốc trừ sâu Vibasu", "Chai", 60000.0, 75000.0, 45, "Thuốc trừ sâu", ""});
        data.add(new Object[]{2031, "Thuốc cỏ Glyphosate", "Chai", 85000.0, 100000.0, 40, "Thuốc diệt cỏ", ""});
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
        if (columnIndex == 7) { // Action column
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
        return columnIndex == 7;
    }

    public void setData(List<Object[]> newData) {
        this.data = newData;
        fireTableDataChanged();
    }

    public Object[] getRowData(int rowIndex) {
        return data.get(rowIndex);
    }
} 