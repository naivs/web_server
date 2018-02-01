/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.dbService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.h2.jdbcx.JdbcDataSource;

/**
 *
 * @author Ivan Naumov
 */
public class H2SQLConnection {

    private static Connection connection;

    public static Connection instance() {
        if (connection == null) {
            connection = getConnection();
        }

        return connection;
    }

    private static Connection getConnection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "tully";
            String pass = "tully";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection con = DriverManager.getConnection(url, name, pass);
            return con;
        } catch (SQLException e) {
            System.err.println("Create connection exception. " + e.getMessage());
        }
        return null;
    }
}
