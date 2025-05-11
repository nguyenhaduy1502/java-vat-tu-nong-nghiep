package com.vtnn.app.ui;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class KhachHangTableModel extends AbstractTableModel {
    private List<Object[]> customers, filtered;
    private String[] columns = { "Mã KH", "Tên KH", "Giới Tính", "Số Điện Thoại", "Địa Chỉ", "Email", "Actions" };

    public KhachHangTableModel(List<Object[]> customers) {
        setCustomers(customers);
    }

    public void setCustomers(List<Object[]> customers) {
        this.customers = customers;
        this.filtered = new ArrayList<>(customers);
        fireTableDataChanged();
    }

    public void setFilter(String text) {
        filtered = customers.stream()
                .filter(c -> c[1].toString().toLowerCase().contains(text.toLowerCase()) ||
                           c[0].toString().contains(text))
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
        Object[] c = filtered.get(row);
        if (col < c.length) {
            return c[col];
        }
        return c; // Return the entire row object for the Actions column
    }

    public boolean isCellEditable(int row, int col) {
        return col == 6; // Only the Actions column is editable
    }

    public Object[] getCustomerAt(int row) {
        return filtered.get(row);
    }
} 