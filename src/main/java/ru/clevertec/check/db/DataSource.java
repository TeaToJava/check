package ru.clevertec.check.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config;
    private static HikariDataSource ds;
    
    private DataSource() {
    	
    }

    public static void setProperties(Properties props){
        config = new HikariConfig(props);
        ds = new HikariDataSource( config );
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
