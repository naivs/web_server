package dbService;

import dbService.dao.UsersDAOPattern;
import accounts.UserAccount;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import services.DBService;

public class DBServicePattern implements DBService {
    private final Connection connection;

    public DBServicePattern() {
        this.connection = MySQLConnection.instance();
    }

    @Override
    public UserAccount getUser(long id) throws DBException {
        try {
            return (new UsersDAOPattern(connection).get(id));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    
    @Override
    public ArrayList<UserAccount> getUsers() throws DBException {
        try {
            return new UsersDAOPattern(connection).getUsers();
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    @Override
    public long addUser(UserAccount userAccount) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAOPattern dao = new UsersDAOPattern(connection);
            dao.createTable();
            dao.insertUser(userAccount);
            connection.commit();
            return dao.getUserId(userAccount.getLogin());
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    @Override
    public void cleanUp() throws DBException {
        UsersDAOPattern dao = new UsersDAOPattern(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    
    @Override
    public void printConnectInfo() {
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
