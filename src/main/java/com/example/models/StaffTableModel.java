package com.example.models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StaffTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Name", "Gender", "Phone", "Address", "Email", "Role", "Action"};
    private List<Object[]> data;

    public StaffTableModel() {
        this.data = new ArrayList<>();
        // Add some mock data
        addMockData();
    }

    private void addMockData() {
        // Mock data based on the database schema
        data.add(new Object[]{2501, "Nguyễn Thị Mai", "Nữ", "0901234567", "12/5 Nguyễn Văn Cừ, Q.5, TP.HCM", "nguyenmaithi@example.com", "Quản lý", ""});
        data.add(new Object[]{2502, "Trần Quang Hiếu", "Nam", "0912345678", "24/3 Lê Thị Riêng, Q.1, TP.HCM", "tranquanghieu@example.com", "Nhân viên kho", ""});
        data.add(new Object[]{2504, "Phạm Thị Lan", "Nữ", "0934567890", "45/6 Hồ Hảo Hớn, Q.11, TP.HCM", "phamthilan@example.com", "Nhân viên bán hàng", ""});
        data.add(new Object[]{2505, "Nguyễn Hoàng Nam", "Nam", "0945678901", "19/8 Trường Sa, Q.3, TP.HCM", "nguyenhoangnam@example.com", "Nhân viên bán hàng", ""});
        data.add(new Object[]{2510, "Vũ Thị Mai Linh", "Nữ", "0990123456", "68/2 Kinh Dương Vương, Q.Bình Tân, TP.HCM", "vuthimailinh@example.com", "Kế toán", ""});
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