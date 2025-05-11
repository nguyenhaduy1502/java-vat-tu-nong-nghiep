package com.vtnn.app.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

/**
 * Chart showing current stock levels.
 */
public class InventoryLevelsChart extends BaseChartPanel {
    
    private DefaultCategoryDataset dataset;
    private JComboBox<String> categoryFilter;
    private JComboBox<String> sortOrder;
    
    public InventoryLevelsChart() throws Exception {
        super();
        setupControls();
    }
    
    private void setupControls() {
        // Add category filter
        JLabel categoryLabel = new JLabel("Danh mục: ");
        categoryFilter = new JComboBox<>(new String[] {"Tất cả", "Phân bón gốc", "Phân bón lá", "Thuốc trừ sâu", "Thuốc diệt cỏ", "Nông cụ"});
        categoryFilter.setPreferredSize(new Dimension(150, 25));
        categoryFilter.addActionListener(e -> {
            try {
                refreshData();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        // Add sort order
        JLabel sortLabel = new JLabel("Sắp xếp: ");
        sortOrder = new JComboBox<>(new String[] {"Tồn kho tăng dần", "Tồn kho giảm dần"});
        sortOrder.setPreferredSize(new Dimension(150, 25));
        sortOrder.addActionListener(e -> {
            try {
                refreshData();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        // Add to control panel
        controlPanel.add(categoryLabel);
        controlPanel.add(categoryFilter);
        controlPanel.add(Box.createHorizontalStrut(20));
        controlPanel.add(sortLabel);
        controlPanel.add(sortOrder);
    }
    
    @Override
    protected void createChart() throws Exception {
        // Create dataset if it doesn't exist
        if (dataset == null) {
            dataset = new DefaultCategoryDataset();
        }
        
        updateDataset();
        
        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
            "Tồn kho hiện tại",            // chart title
            "Sản phẩm",                    // domain axis label
            "Số lượng tồn",                // range axis label
            dataset,                        // data
            PlotOrientation.HORIZONTAL,     // orientation
            false,                          // include legend
            true,                           // tooltips
            false                           // urls
        );
        
        // Customize chart
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        
        // Format category axis
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setCategoryMargin(0.2);
        categoryAxis.setLowerMargin(0.05);
        categoryAxis.setUpperMargin(0.05);
        
        // Format value axis
        NumberAxis valueAxis = (NumberAxis) plot.getRangeAxis();
        valueAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        // Format bar renderer
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        
        // Set chart in panel
        setChartInPanel(chart);
        
        // Now that the chart is created, we can colorize the bars
        ColorizeInventoryBars();
    }
    
    @Override
    protected void refreshData() throws Exception {
        updateDataset();
        // The chart will automatically update because the dataset is changed
    }
    
    private void updateDataset() throws Exception {
        // Reset dataset
        dataset.clear();
        
        // Get filter values
        String category = categoryFilter != null ? (String) categoryFilter.getSelectedItem() : "Tất cả";
        boolean ascending = sortOrder != null ? sortOrder.getSelectedIndex() == 0 : true;
        
        // Build SQL query based on filters
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT sp.TenSP, sp.SoLuongTon, nh.TenNH ");
        sqlBuilder.append("FROM SanPham sp ");
        sqlBuilder.append("JOIN NhomHang nh ON sp.NhomHang = nh.MaNH ");
        
        // Apply category filter if not "All"
        if (category != null && !category.equals("Tất cả")) {
            sqlBuilder.append("WHERE nh.TenNH = '").append(category).append("' ");
        }
        
        // Apply sorting
        sqlBuilder.append("ORDER BY sp.SoLuongTon ").append(ascending ? "ASC" : "DESC");
        
        // Execute query
        ResultSet resultSet = executeQuery(sqlBuilder.toString());
        
        // Process results - limit to 15 items for readability
        int count = 0;
        while (resultSet.next() && count < 15) {
            String productName = resultSet.getString("TenSP");
            int stock = resultSet.getInt("SoLuongTon");
            String productCategory = resultSet.getString("TenNH");
            
            dataset.addValue(stock, productCategory, productName);
            count++;
        }
        
        // Colorize bars based on stock level in the chart - only if chart exists
        if (chartPanel != null && chartPanel.getChart() != null) {
            ColorizeInventoryBars();
        }
        
        // Check if we have data, if not add sample data
        if (dataset.getRowCount() == 0) {
            // Add some sample data if no data exists
            dataset.addValue(20, "Phân bón gốc", "Đạm Phú Mỹ");
            dataset.addValue(35, "Phân bón gốc", "Lân Văn Điển");
            dataset.addValue(15, "Thuốc trừ sâu", "Thuốc trừ sâu Confidor");
            dataset.addValue(50, "Phân bón lá", "Phân bón lá HVP 401");
            dataset.addValue(10, "Thuốc diệt cỏ", "Thuốc cỏ Glyphosate");
            dataset.addValue(45, "Nông cụ", "Bình xịt điện");
            dataset.addValue(25, "Phân bón gốc", "NPK Đầu Trâu 20-20-15");
            dataset.addValue(5, "Thuốc diệt cỏ", "Thuốc cỏ Dual Gold");
        }
        
        // Close resources
        resultSet.close();
    }
    
    private void ColorizeInventoryBars() {
        if (chartPanel == null) return;
        
        JFreeChart chart = chartPanel.getChart();
        if (chart == null) return;
        
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        
        // Set colors based on stock level
        for (int row = 0; row < dataset.getRowCount(); row++) {
            for (int col = 0; col < dataset.getColumnCount(); col++) {
                Number value = dataset.getValue(row, col);
                if (value != null) {
                    int stock = value.intValue();
                    if (stock <= 10) {
                        // Red for critically low stock
                        renderer.setSeriesPaint(row, new Color(231, 76, 60));
                    } else if (stock <= 25) {
                        // Orange for low stock
                        renderer.setSeriesPaint(row, new Color(230, 126, 34));
                    } else if (stock <= 50) {
                        // Yellow for moderate stock
                        renderer.setSeriesPaint(row, new Color(241, 196, 15));
                    } else {
                        // Green for good stock
                        renderer.setSeriesPaint(row, new Color(46, 204, 113));
                    }
                }
            }
        }
    }
} 