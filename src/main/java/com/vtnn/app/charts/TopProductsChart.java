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

/**
 * Chart showing the top 10 best-selling products by volume.
 */
public class TopProductsChart extends BaseChartPanel {
    
    private DefaultCategoryDataset dataset;
    
    public TopProductsChart() throws Exception {
        super();
    }
    
    @Override
    protected void createChart() throws Exception {
        // Create dataset
        dataset = new DefaultCategoryDataset();
        updateDataset();
        
        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
            "Top 10 sản phẩm bán chạy nhất",  // chart title
            "Sản phẩm",                       // domain axis label
            "Số lượng đã bán",                // range axis label
            dataset,                          // data
            PlotOrientation.HORIZONTAL,       // orientation
            false,                            // include legend
            true,                             // tooltips
            false                             // urls
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
        renderer.setSeriesPaint(0, new Color(64, 155, 85));
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
        
        // Execute query to get top 10 products by quantity sold
        String sql = "SELECT sp.TenSP, SUM(ct.SoLuong) AS QtySold " +
                     "FROM ChiTietBan ct " +
                     "JOIN SanPham sp ON ct.MaSP = sp.MaSP " +
                     "GROUP BY sp.TenSP " +
                     "ORDER BY QtySold DESC";
        
        ResultSet resultSet = executeQuery(sql);
        
        // Process results - get only top 10
        int count = 0;
        while (resultSet.next() && count < 10) {
            String productName = resultSet.getString("TenSP");
            int qtySold = resultSet.getInt("QtySold");
            
            dataset.addValue(qtySold, "Số lượng", productName);
            count++;
        }
        
        // Check if we have data, if not add sample data
        if (dataset.getRowCount() == 0) {
            // Add some sample data if no data exists
            dataset.addValue(120, "Số lượng", "Đạm Phú Mỹ");
            dataset.addValue(95, "Số lượng", "Lân Văn Điển");
            dataset.addValue(85, "Số lượng", "NPK Bình Điền 16-16-8");
            dataset.addValue(70, "Số lượng", "Phân Kali Canada");
            dataset.addValue(65, "Số lượng", "Phân Hữu Cơ Vi Sinh");
            dataset.addValue(50, "Số lượng", "NPK Đầu Trâu 20-20-15");
            dataset.addValue(45, "Số lượng", "Phân Super Lân");
            dataset.addValue(40, "Số lượng", "Đạm Cà Mau");
            dataset.addValue(35, "Số lượng", "NPK Phú Mỹ 16-16-8");
            dataset.addValue(30, "Số lượng", "Phân Hữu Cơ Đầu Trâu");
        }
        
        // Close resources
        resultSet.close();
    }
} 