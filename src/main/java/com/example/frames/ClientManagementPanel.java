package com.example.frames;

import com.example.components.SearchPanel;
import com.example.models.ClientTableModel;
import com.example.models.User;
import com.example.models.UserRole;
import com.example.utils.UIUtils;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ClientManagementPanel extends JPanel {
    private final ClientTableModel tableModel = new ClientTableModel();
    private final JTable clientTable = new JTable(tableModel);
    private final SearchPanel searchPanel = new SearchPanel();
    private final TableRowSorter<ClientTableModel> sorter = new TableRowSorter<>(tableModel);

    public ClientManagementPanel(User currentUser) {
        // Check access permissions
        if (currentUser.getRole() != UserRole.ADMIN && currentUser.getRole() != UserRole.SALES_STAFF) {
            setLayout(new BorderLayout());
            add(new JLabel("Access Denied: You don't have permission to view this page.", SwingConstants.CENTER));
            return;
        }

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Configure table
        clientTable.setRowSorter(sorter);
        clientTable.setFillsViewportHeight(true);
        clientTable.setShowVerticalLines(true);
        clientTable.setShowHorizontalLines(true);
        clientTable.getTableHeader().setReorderingAllowed(false);

        // Set up the action column
        UIUtils.setupActionColumn(clientTable, 6,
            e -> handleEdit(Integer.parseInt(e.getActionCommand())),
            e -> handleDelete(Integer.parseInt(e.getActionCommand()))
        );

        // Create scroll pane for table
        JScrollPane scrollPane = new JScrollPane(clientTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Create title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Client Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        titlePanel.add(titleLabel, BorderLayout.WEST);

        // Add action buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = UIUtils.createPrimaryButton("Add Client");
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

        RowFilter<ClientTableModel, Object> rf = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends ClientTableModel, ? extends Object> entry) {
                String name = entry.getValue(1).toString().toLowerCase();
                String phone = entry.getValue(3).toString().toLowerCase();
                String email = entry.getValue(5).toString().toLowerCase();

                return name.contains(searchText) || 
                       phone.contains(searchText) || 
                       email.contains(searchText);
            }
        };

        sorter.setRowFilter(rf);
    }

    private void handleAdd() {
        // TODO: Implement add client dialog
        JOptionPane.showMessageDialog(this, "Add Client functionality will be implemented here");
    }

    private void handleEdit(int row) {
        Object[] rowData = tableModel.getRowData(row);
        // TODO: Implement edit client dialog
        JOptionPane.showMessageDialog(this, 
            String.format("Edit Client functionality will be implemented here\nClient: %s (ID: %s)", 
            rowData[1], rowData[0]));
    }

    private void handleDelete(int row) {
        Object[] rowData = tableModel.getRowData(row);
        int option = JOptionPane.showConfirmDialog(this,
            String.format("Are you sure you want to delete client:\n%s (ID: %s)?", 
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