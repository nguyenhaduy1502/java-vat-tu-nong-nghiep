package com.example.utils;

import com.example.components.ButtonColumn;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.layout.StackPane;
import javafx.scene.image.WritableImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class UIUtils {
    private static boolean jfxInitialized = false;

    static {
        initializeJavaFX();
    }

    private static void initializeJavaFX() {
        if (!jfxInitialized) {
            // Initialize JavaFX Platform
            new JFXPanel(); // Creates JavaFX Platform
            jfxInitialized = true;
        }
    }

    public static final Color PRIMARY_COLOR = new Color(100, 149, 237);
    public static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 14);
    
    public static JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        return panel;
    }

    public static JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static void setupActionColumn(JTable table, int column, ActionListener editListener, ActionListener deleteListener) {
        ButtonColumn buttonColumn = new ButtonColumn(editListener, deleteListener);
        table.getColumnModel().getColumn(column).setCellRenderer(buttonColumn);
        table.getColumnModel().getColumn(column).setCellEditor(buttonColumn);
        table.getColumnModel().getColumn(column).setPreferredWidth(100);
        table.setRowHeight(35);
    }

    public static Icon createFontAwesomeIcon(FontAwesomeIcon icon, Color color) {
        if (!Platform.isFxApplicationThread()) {
            final CountDownLatch latch = new CountDownLatch(1);
            final Icon[] result = new Icon[1];
            
            Platform.runLater(() -> {
                result[0] = createFontAwesomeIconOnFXThread(icon, color);
                latch.countDown();
            });
            
            try {
                latch.await();
                return result[0];
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
        return createFontAwesomeIconOnFXThread(icon, color);
    }

    private static Icon createFontAwesomeIconOnFXThread(FontAwesomeIcon icon, Color color) {
        FontAwesomeIconView iconView = new FontAwesomeIconView(icon);
        iconView.setGlyphSize(20);
        iconView.setFill(javafx.scene.paint.Color.valueOf(String.format("#%02x%02x%02x", 
            color.getRed(), color.getGreen(), color.getBlue())));
        
        StackPane pane = new StackPane(iconView);
        Scene scene = new Scene(pane, 20, 20);
        scene.setFill(null);
        
        WritableImage image = new WritableImage(20, 20);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(javafx.scene.paint.Color.TRANSPARENT);
        
        return new ImageIcon(SwingFXUtils.fromFXImage(pane.snapshot(params, image), null));
    }
} 