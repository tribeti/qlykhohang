package Servers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MySQLConnect {
    public static Connection getConnection() {
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("env.properties")) {
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
