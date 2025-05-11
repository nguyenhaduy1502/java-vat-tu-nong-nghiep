package com.vtnn.app.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.awt.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Chart showing monthly sales revenue trend.
 */
public class MonthlySalesChart extends BaseChartPanel {
    
    private TimeSeriesCollection dataset;
    
    public MonthlySalesChart() throws Exception {
        super();
    }
    
    @Override
    protected void createChart() throws Exception {
        // Create dataset
        dataset = new TimeSeriesCollection();
        updateDataset();
        
        // Create chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Doanh thu theo tháng",      // chart title
            "Tháng",                      // x axis label
            "Doanh thu (VNĐ)",           // y axis label
            dataset,                      // data
            true,                         // include legend
            true,                         // tooltips
            false                         // urls
        );
        
        // Customize chart
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        
        // Format date axis
        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.setDateFormatOverride(new SimpleDateFormat("MM/yyyy"));
        
        // Format value axis
        NumberAxis valueAxis = (NumberAxis) plot.getRangeAxis();
        valueAxis.setNumberFormatOverride(new java.text.DecimalFormat("#,###"));
        
        // Format line renderer
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDefaultShapesFilled(true);
        renderer.setSeriesPaint(0, new Color(0, 120, 215));
        
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
        dataset.removeAllSeries();
        
        // Create time series
        TimeSeries series = new TimeSeries("Doanh thu");
        
        // Execute query
        String sql = "SELECT YEAR(NgayBan) AS Yr, MONTH(NgayBan) AS Mo, SUM(TongTien) AS Revenue " +
                     "FROM HoaDonBan " +
                     "GROUP BY YEAR(NgayBan), MONTH(NgayBan) " +
                     "ORDER BY Yr, Mo";
        
        ResultSet resultSet = executeQuery(sql);
        
        // Process results
        while (resultSet.next()) {
            int year = resultSet.getInt("Yr");
            int month = resultSet.getInt("Mo");
            double revenue = resultSet.getDouble("Revenue");
            
            series.add(new Month(month, year), revenue);
        }
        
        // Check if we have data, if not add sample data
        if (series.getItemCount() == 0) {
            // Add some sample data if no data exists
            Calendar cal = Calendar.getInstance();
            int currentYear = cal.get(Calendar.YEAR);
            
            for (int month = 1; month <= 12; month++) {
                // Generate random sample value
                double value = Math.random() * 100000000;
                series.add(new Month(month, currentYear), value);
            }
        }
        
        // Add series to dataset
        dataset.addSeries(series);
        
        // Close resources
        resultSet.close();
    }
} 