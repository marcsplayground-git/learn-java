package com.personalplayground.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public final class ConnectionPool {

    // Single shared DataSource instance (basic singleton style)
    private static DataSource dataSource;

    private ConnectionPool() {
        // prevent instantiation
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    dataSource = createDataSource();
                }
            }
        }
        return dataSource;
    }

    private static DataSource createDataSource() {
        HikariConfig config = new HikariConfig();

        // Basic JDBC URL, username, password from your existing config
        config.setJdbcUrl(DatabaseConfig.URL);
        config.setUsername(DatabaseConfig.USER);
        config.setPassword(DatabaseConfig.PASSWORD);

        // Some sensible defaults for learning (tweak later if needed)
        config.setMaximumPoolSize(5);        // max connections in pool
        config.setMinimumIdle(1);            // minimum idle connections
        config.setPoolName("MyJDBCPlaygroundPool");

        // Optional: timeouts (milliseconds)
        config.setConnectionTimeout(10_000); // 10 seconds to wait for a connection
        config.setIdleTimeout(60_000);       // 60 seconds before idle connection may be closed

        System.out.println("Initializing HikariCP DataSource...");
        return new HikariDataSource(config);
    }
}
