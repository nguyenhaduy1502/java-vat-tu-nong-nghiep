package com.vtnn.app.view.base;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class BaseCrudPanel<T> extends JPanel {
    protected JPanel contentPanel;
    protected JPanel buttonPanel;
    protected JButton btnAdd;
    protected JButton btnEdit;
    protected JButton btnDelete;
    protected JButton btnRefresh;
    protected JTable table;
    protected JScrollPane scrollPane;

    public BaseCrudPanel() {
        setLayout(new BorderLayout());
        initializeComponents();
        setupLayout();
        setupListeners();
    }

    protected void initializeComponents() {
        contentPanel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        btnAdd = new JButton("Thêm mới");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnRefresh = new JButton("Làm mới");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);

        table = new JTable();
        scrollPane = new JScrollPane(table);
    }

    protected void setupLayout() {
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    protected void setupListeners() {
        btnAdd.addActionListener(e -> onAdd());
        btnEdit.addActionListener(e -> onEdit());
        btnDelete.addActionListener(e -> onDelete());
        btnRefresh.addActionListener(e -> refreshData());
    }

    // Abstract methods to be implemented by subclasses
    protected abstract void onAdd();
    protected abstract void onEdit();
    protected abstract void onDelete();
    protected abstract void refreshData();
    protected abstract void loadData(List<T> data);
} 