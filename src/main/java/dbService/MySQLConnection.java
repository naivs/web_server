/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbService;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ivan Naumov
 */
public class MySQLConnection {

    private static Connection connection;

    public static Connection instance() {
        if (connection == null) {
            connection = getConnection();
            printConnectInfo();
        }

        return connection;
    }

    private static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://"). //db type
                    append("localhost:"). //host name
                    append("3306/"). //port
                    append("web?"). //db name
                    append("user=root&"). //login
                    append("password=root");       //password

            System.out.println("URL: " + url + "\n");

            Connection con = DriverManager.getConnection(url.toString());
            return con;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.err.println("Create connection exception. " + e.getMessage());
        }
        return null;
    }

    private static void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            System.err.println("Can't get database connection info. " + e.getMessage());
        }
    }
}
