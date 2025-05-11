package com.vtnn.app.ui;

import com.vtnn.app.charts.*;
import javax.swing.*;
import java.awt.*;

/**
 * Main panel for Reports and Statistics section
 * Contains all charts and reports for business analysis
 */
public class ReportsPanel extends JPanel {
    
    private JTabbedPane tabbedPane;
    
    public ReportsPanel() {
        initializeComponents();
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout());
        
        // Create tabbed pane for different charts
        tabbedPane = new JTabbedPane();
        
        // Add each chart as a separate tab
        try {
            // 1. Monthly Sales Revenue Trend
            tabbedPane.addTab("Doanh thu theo tháng", new MonthlySalesChart());
            
            // 2. Top-10 Best-Selling Products
            tabbedPane.addTab("Top 10 sản phẩm bán chạy", new TopProductsChart());
            
            // 3. Sales Revenue by Product Category
            tabbedPane.addTab("Doanh thu theo danh mục", new CategorySalesChart());
            
            // 4. Purchase Spend by Supplier
            tabbedPane.addTab("Chi tiêu theo nhà cung cấp", new SupplierSpendChart());
            
            // 5. Current Stock Levels
            tabbedPane.addTab("Tồn kho hiện tại", new InventoryLevelsChart());
            
            // 8. Profit Trend
            tabbedPane.addTab("Xu hướng lợi nhuận", new ProfitTrendChart());
            
            // 9. Purchase vs Sales Volume
            tabbedPane.addTab("So sánh nhập/xuất kho", new PurchaseSalesComparisonChart());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi khi tạo biểu đồ: " + e.getMessage(), 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        // Add tabbed pane to panel
        add(tabbedPane, BorderLayout.CENTER);
    }
} 