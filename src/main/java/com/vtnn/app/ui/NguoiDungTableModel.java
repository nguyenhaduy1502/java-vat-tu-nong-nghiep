package com.vtnn.app.ui;

import com.vtnn.app.models.NguoiDungDTO;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class NguoiDungTableModel extends AbstractTableModel {
    private List<NguoiDungDTO> users, filtered;
    private String[] columns = { "Username", "Password", "MaNV", "Role", "Actions" };

    public NguoiDungTableModel(List<NguoiDungDTO> users) {
        setUsers(users);
    }

    public void setUsers(List<NguoiDungDTO> users) {
        this.users = users;
        this.filtered = new ArrayList<>(users);
        fireTableDataChanged();
    }

    public void setFilter(String text) {
        filtered = users.stream()
                .filter(u -> u.getTenDangNhap().toLowerCase().contains(text.toLowerCase()))
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
        NguoiDungDTO u = filtered.get(row);
        switch (col) {
            case 0:
                return u.getTenDangNhap();
            case 1:
                return u.getMatKhau();
            case 2:
                return u.getMaNV();
            case 3:
                return u.getVaiTro();
            case 4:
                return u;
        }
        return null;
    }

    public boolean isCellEditable(int row, int col) {
        return col == 4;
    }

    public NguoiDungDTO getUserAt(int row) {
        return filtered.get(row);
    }
}