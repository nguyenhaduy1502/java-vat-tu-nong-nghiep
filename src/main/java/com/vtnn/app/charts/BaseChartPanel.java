package com.vtnn.app.charts;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import com.vtnn.app.dbservice.SQLServerConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * BaseChartPanel serves as the foundation for all chart panels in the application.
 * Contains common functionalities and utility methods for chart creation.
 */
public abstract class BaseChartPanel extends JPanel {
    
    protected SQLServerConnection dbConnection;
    protected ChartPanel chartPanel;
    protected JPanel controlPanel;
    
    /**
     * Constructor. Initializes DB connection and sets up base panel structure.
     */
    public BaseChartPanel() throws Exception {
        this.dbConnection = new SQLServerConnection();
        setLayout(new BorderLayout());
        
        // Initialize controls panel at the top
        controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(controlPanel, BorderLayout.NORTH);
        
        // Add refresh button
        JButton refreshButton = new JButton("Cập nhật");
        refreshButton.addActionListener(e -> {
            try {
                refreshData();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Lỗi khi cập nhật dữ liệu: " + ex.getMessage(), 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        controlPanel.add(refreshButton);
        
        // Initialize the chart AFTER the constructor completes
        // This allows subclasses to set up their UI components first
        SwingUtilities.invokeLater(() -> {
            try {
                createChart();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Lỗi khi tạo biểu đồ: " + ex.getMessage(), 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
    
    /**
     * Creates and configures the chart. Must be implemented by subclasses.
     */
    protected abstract void createChart() throws Exception;
    
    /**
     * Refreshes the chart data. Should be implemented by subclasses.
     */
    protected abstract void refreshData() throws Exception;
    
    /**
     * Executes a SQL query and returns the result as a ResultSet.
     */
    protected ResultSet executeQuery(String sql) throws Exception {
        Connection conn = dbConnection.getConnect();
        Statement statement = conn.createStatement();
        return statement.executeQuery(sql);
    }
    
    /**
     * Executes a parameterized SQL query and returns the result as a ResultSet.
     */
    protected ResultSet executeQuery(String sql, Object... params) throws Exception {
        Connection conn = dbConnection.getConnect();
        PreparedStatement statement = conn.prepareStatement(sql);
        
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        
        return statement.executeQuery();
    }
    
    /**
     * Sets the chart in the panel with standard configuration.
     */
    protected void setChartInPanel(JFreeChart chart) {
        // Remove old chart panel if it exists
        if (chartPanel != null) {
            remove(chartPanel);
        }
        
        // Create new chart panel
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 500));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        
        // Add to panel
        add(chartPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
} 