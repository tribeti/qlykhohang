package Servers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnect {

    // Private constructor để không tạo instance
    private MySQLConnect() {
    }

    public static Connection getConnection() {
        Properties props = new Properties();

        // Đọc cấu hình từ file env.properties
        try (FileInputStream fis = new FileInputStream(
                "D:\\am22_Quynh\\LapTrinhMang\\BTH\\env.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load database configuration from env.properties", e);
        }

        String host = props.getProperty("HOST");
        String port = props.getProperty("PORT");
        String database = props.getProperty("DATABASE");
        String dbUser = props.getProperty("USERNAME");
        String dbPass = props.getProperty("PASSWORD");

        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?sslMode=VERIFY_IDENTITY";
            return DriverManager.getConnection(url, dbUser, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
}
