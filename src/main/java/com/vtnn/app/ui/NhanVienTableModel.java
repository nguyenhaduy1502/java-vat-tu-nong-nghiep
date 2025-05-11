package com.vtnn.app.ui;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class NhanVienTableModel extends AbstractTableModel {
    private List<Object[]> staff, filtered;
    private String[] columns = { "Mã NV", "Họ Tên", "Ngày Sinh", "Giới Tính", "Số Điện Thoại", "Địa Chỉ", "Email", "Vai Trò", "Actions" };

    public NhanVienTableModel(List<Object[]> staff) {
        setStaff(staff);
    }

    public void setStaff(List<Object[]> staff) {
        this.staff = staff;
        this.filtered = new ArrayList<>(staff);
        fireTableDataChanged();
    }

    public void setFilter(String text) {
        filtered = staff.stream()
                .filter(s -> s[1].toString().toLowerCase().contains(text.toLowerCase()) ||
                           s[0].toString().contains(text))
                .collect(Collectors.toList());
        fireTableDataChanged();
    }

    public int getRowCount() {
        return filtered.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public Object getValueAt(int row, int col) {
        Object[] s = filtered.get(row);
        if (col < s.length) {
            return s[col];
        }
        return s; // Return the entire row object for the Actions column
    }

    public boolean isCellEditable(int row, int col) {
        return col == 8; // Only the Actions column is editable
    }

    public Object[] getStaffAt(int row) {
        return filtered.get(row);
    }
} 