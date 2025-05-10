package dbservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class SQLServerConnection {
    private String username;
    private String password;
    private String database;
    private String server;
    private String port;
    private String encrypt;

    // Constructor đọc cấu hình từ file properties
    public SQLServerConnection() throws IOException {
        loadConfig("/dbservice/config.properties");
    }

    // Đọc thông tin cấu hình từ file .properties
    private void loadConfig(String configPath) throws IOException {
        Properties properties = new Properties();
        try (InputStream fis = getClass().getResourceAsStream(configPath)) {
            if (fis == null) {
                throw new IOException("Không tìm thấy file cấu hình: " + configPath);
            }
            properties.load(fis);
        }

        this.server = properties.getProperty("server");
        this.username = properties.getProperty("username");
        this.password = properties.getProperty("password");
        this.database = properties.getProperty("database");
        this.port = properties.getProperty("port");
        this.encrypt = properties.getProperty("encrypt", "false");
    }

    // Tạo kết nối mới mỗi lần gọi
    public Connection getConnect() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new Exception("Không tìm thấy driver SQL Server.", e);
        }

        String url = "jdbc:sqlserver://" + this.server + ":" + this.port +
                     ";databaseName=" + this.database + ";encrypt=" + this.encrypt;

        try {
            return DriverManager.getConnection(url, this.username, this.password);
        } catch (SQLException e) {
            throw new Exception("Không thể kết nối tới database: " + e.getMessage(), e);
        }
    }
}
