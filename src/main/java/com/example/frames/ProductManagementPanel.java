package com.example.frames;

import com.example.components.SearchPanel;
import com.example.models.ProductTableModel;
import com.example.utils.UIUtils;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProductManagementPanel extends JPanel {
    private final ProductTableModel tableModel = new ProductTableModel();
    private final JTable productTable = new JTable(tableModel);
    private final SearchPanel searchPanel = new SearchPanel();
    private final TableRowSorter<ProductTableModel> sorter = new TableRowSorter<>(tableModel);

    public ProductManagementPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Configure table
        productTable.setRowSorter(sorter);
        productTable.setFillsViewportHeight(true);
        productTable.setShowVerticalLines(true);
        productTable.setShowHorizontalLines(true);
        productTable.getTableHeader().setReorderingAllowed(false);

        // Set up the action column
        UIUtils.setupActionColumn(productTable, 7,
            e -> handleEdit(Integer.parseInt(e.getActionCommand())),
            e -> handleDelete(Integer.parseInt(e.getActionCommand()))
        );

        // Create scroll pane for table
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Create title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Product Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        titlePanel.add(titleLabel, BorderLayout.WEST);

        // Add action buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = UIUtils.createPrimaryButton("Add Product");
        addButton.addActionListener(e -> handleAdd());
        actionPanel.add(addButton);
        titlePanel.add(actionPanel, BorderLayout.EAST);

        // Add search listener
        searchPanel.addSearchListener(this::handleSearch);

        // Layout components
        add(titlePanel, BorderLayout.NORTH);
        add(searchPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Set preferred size for the panel
        setPreferredSize(new Dimension(800, 600));
    }

    private void handleSearch(ActionEvent e) {
        String searchText = searchPanel.getSearchText().toLowerCase();
        String category = searchPanel.getSelectedCategory();

        RowFilter<ProductTableModel, Object> rf = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends ProductTableModel, ? extends Object> entry) {
                // Check if the product name contains the search text
                String name = entry.getValue(1).toString().toLowerCase();
                boolean matchesSearch = name.contains(searchText);

                // Check if the category matches (if not "All Categories")
                if (!"All Categories".equals(category)) {
                    String productCategory = entry.getValue(6).toString();
                    return matchesSearch && category.equals(productCategory);
                }

                return matchesSearch;
            }
        };

        sorter.setRowFilter(rf);
    }

    private void handleAdd() {
        // TODO: Implement add product dialog
        JOptionPane.showMessageDialog(this, "Add Product functionality will be implemented here");
    }

    private void handleEdit(int row) {
        Object[] rowData = tableModel.getRowData(row);
        // TODO: Implement edit product dialog
        JOptionPane.showMessageDialog(this, 
            String.format("Edit Product functionality will be implemented here\nProduct: %s (ID: %s)", 
            rowData[1], rowData[0]));
    }

    private void handleDelete(int row) {
        Object[] rowData = tableModel.getRowData(row);
        int option = JOptionPane.showConfirmDialog(this,
            String.format("Are you sure you want to delete product:\n%s (ID: %s)?", 
            rowData[1], rowData[0]),
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (option == JOptionPane.YES_OPTION) {
            // TODO: Implement actual delete functionality
            JOptionPane.showMessageDialog(this, "Delete functionality will be implemented here");
        }
    }

    // Method to refresh the table data (will be used when connected to database)
    public void refreshData() {
        tableModel.fireTableDataChanged();
    }
} 