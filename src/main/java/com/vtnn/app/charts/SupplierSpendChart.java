package com.vtnn.app.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.sql.ResultSet;
import java.text.DecimalFormat;

/**
 * Chart showing purchase spend by supplier.
 */
public class SupplierSpendChart extends BaseChartPanel {
    
    private DefaultCategoryDataset dataset;
    
    public SupplierSpendChart() throws Exception {
        super();
    }
    
    @Override
    protected void createChart() throws Exception {
        // Create dataset
        dataset = new DefaultCategoryDataset();
        updateDataset();
        
        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
            "Chi tiêu theo nhà cung cấp",   // chart title
            "Nhà cung cấp",                 // domain axis label
            "Chi tiêu (VNĐ)",               // range axis label
            dataset,                         // data
            PlotOrientation.VERTICAL,        // orientation
            false,                           // include legend
            true,                            // tooltips
            false                            // urls
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
        valueAxis.setNumberFormatOverride(new DecimalFormat("#,###"));
        
        // Format bar renderer
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(142, 68, 173));
        renderer.setDrawBarOutline(false);
        renderer.setShadowVisible(false);
        
        // Set chart in panel
        setChartInPanel(chart);
    }
    
    @Override
    protected void refreshData() throws Exception {
        updateDataset();
        // The chart will automatically update because the dataset is changed
    }
    
    private void updateDataset() throws Exception {
        // Reset dataset
        dataset.clear();
        
        // Execute query to get purchase spend by supplier
        String sql = "SELECT ncc.TenNCC, SUM(ctn.SoLuong * ctn.DonGia) AS Spend " +
                     "FROM ChiTietNhap ctn " +
                     "JOIN PhieuNhap pn ON ctn.MaPN = pn.MaPN " +
                     "JOIN NhaCungCap ncc ON pn.MaNCC = ncc.MaNCC " +
                     "GROUP BY ncc.TenNCC " +
                     "ORDER BY Spend DESC";
        
        ResultSet resultSet = executeQuery(sql);
        
        // Process results
        boolean hasData = false;
        while (resultSet.next()) {
            String supplier = resultSet.getString("TenNCC");
            double spend = resultSet.getDouble("Spend");
            
            dataset.addValue(spend, "Chi tiêu", supplier);
            hasData = true;
        }
        
        // Check if we have data, if not add sample data
        if (!hasData) {
            // Add some sample data if no data exists
            dataset.addValue(8500000, "Chi tiêu", "Công ty TNHH Nông Dược Xanh");
            dataset.addValue(6200000, "Chi tiêu", "Công ty CP Phân Bón Việt Mỹ");
            dataset.addValue(5400000, "Chi tiêu", "DNTN Hóa Chất Nông Nghiệp Thanh Bình");
            dataset.addValue(4800000, "Chi tiêu", "Công ty TNHH Nông Nghiệp Sạch Miền Tây");
            dataset.addValue(3900000, "Chi tiêu", "Công ty TNHH TM Nông Dược Miền Nam");
        }
        
        // Close resources
        resultSet.close();
    }
} 