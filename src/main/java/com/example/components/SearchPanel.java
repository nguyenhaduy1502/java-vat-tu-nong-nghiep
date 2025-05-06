package com.example.components;

import com.example.utils.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {
    private final JTextField searchField;
    private final JComboBox<String> categoryFilter;
    private final JButton searchButton;

    public SearchPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Search field
        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(200, 30));

        // Category filter
        String[] categories = {"All Categories", "Phân bón gốc", "Phân bón lá", "Thuốc trừ sâu", "Thuốc diệt cỏ", "Nông cụ"};
        categoryFilter = new JComboBox<>(categories);
        categoryFilter.setPreferredSize(new Dimension(150, 30));

        // Search button
        searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 30));

        // Add components
        add(new JLabel("Search:"));
        add(searchField);
        add(new JLabel("Category:"));
        add(categoryFilter);
        add(searchButton);
    }

    public String getSearchText() {
        return searchField.getText();
    }

    public String getSelectedCategory() {
        return (String) categoryFilter.getSelectedItem();
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
        searchField.addActionListener(listener);
    }

    public void resetFilters() {
        searchField.setText("");
        categoryFilter.setSelectedIndex(0);
    }
} 