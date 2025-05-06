package com.example.frames;

import com.example.components.SearchPanel;
import com.example.models.StaffTableModel;
import com.example.models.User;
import com.example.models.UserRole;
import com.example.utils.UIUtils;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StaffManagementPanel extends JPanel {
    private final StaffTableModel tableModel = new StaffTableModel();
    private final JTable staffTable = new JTable(tableModel);
    private final SearchPanel searchPanel = new SearchPanel();
    private final TableRowSorter<StaffTableModel> sorter = new TableRowSorter<>(tableModel);

    public StaffManagementPanel(User currentUser) {
        // Check access permissions
        if (currentUser.getRole() != UserRole.ADMIN) {
            setLayout(new BorderLayout());
            add(new JLabel("Access Denied: Only administrators can view this page.", SwingConstants.CENTER));
            return;
        }

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Configure table
        staffTable.setRowSorter(sorter);
        staffTable.setFillsViewportHeight(true);
        staffTable.setShowVerticalLines(true);
        staffTable.setShowHorizontalLines(true);
        staffTable.getTableHeader().setReorderingAllowed(false);

        // Set up the action column
        UIUtils.setupActionColumn(staffTable, 7,
            e -> handleEdit(Integer.parseInt(e.getActionCommand())),
            e -> handleDelete(Integer.parseInt(e.getActionCommand()))
        );

        // Create scroll pane for table
        JScrollPane scrollPane = new JScrollPane(staffTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Create title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Staff Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        titlePanel.add(titleLabel, BorderLayout.WEST);

        // Add action buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = UIUtils.createPrimaryButton("Add Staff");
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

        RowFilter<StaffTableModel, Object> rf = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends StaffTableModel, ? extends Object> entry) {
                String name = entry.getValue(1).toString().toLowerCase();
                String phone = entry.getValue(3).toString().toLowerCase();
                String email = entry.getValue(5).toString().toLowerCase();
                String role = entry.getValue(6).toString().toLowerCase();

                return name.contains(searchText) || 
                       phone.contains(searchText) || 
                       email.contains(searchText) ||
                       role.contains(searchText);
            }
        };

        sorter.setRowFilter(rf);
    }

    private void handleAdd() {
        // TODO: Implement add staff dialog
        JOptionPane.showMessageDialog(this, "Add Staff functionality will be implemented here");
    }

    private void handleEdit(int row) {
        Object[] rowData = tableModel.getRowData(row);
        // TODO: Implement edit staff dialog
        JOptionPane.showMessageDialog(this, 
            String.format("Edit Staff functionality will be implemented here\nStaff: %s (ID: %s)", 
            rowData[1], rowData[0]));
    }

    private void handleDelete(int row) {
        Object[] rowData = tableModel.getRowData(row);
        int option = JOptionPane.showConfirmDialog(this,
            String.format("Are you sure you want to delete staff member:\n%s (ID: %s)?", 
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