package java13.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConfig {
    public static Connection getConnection(){
        try {
            return  DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "1234"

            );
        } catch (SQLException  e){
            throw new RuntimeException(e.getMessage());
        }

    }
//private static final HikariDataSource dataSource;
//    static {
//        Properties properties = new Properties();
//        properties.setProperty("jdbcUrl", "jdbc:postgresql://localhost:5432/postgres");
//        properties.setProperty("username", "postgres");
//        properties.setProperty("password", "1234");
//        HikariConfig config = new HikariConfig(properties);
//        dataSource = new HikariDataSource(config);
//    }
//    public static Connection getConnection() {
//        System.out.println("Successfully connected");
//        Connection connection = null;
//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        return connection;
//    }
}
