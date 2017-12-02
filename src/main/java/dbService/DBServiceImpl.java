package dbService;

import accounts.UserProfile;
import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import services.DBService;

public class DBServiceImpl implements DBService {
    private final Connection connection;

    public DBServiceImpl() {
        this.connection = MySQLConnection.instance();
    }

    @Override
    public UsersDataSet getUser(long id) throws DBException {
        try {
            return (new UsersDAO(connection).get(id));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    
    @Override
    public ArrayList<UserProfile> getUsers() throws DBException {
        try {
            return new UsersDAO(connection).getUsers();
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    @Override
    public long addUser(UserProfile userProfile) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.createTable();
            dao.insertUser(userProfile);
            connection.commit();
            return dao.getUserId(userProfile.getLogin());
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
        UsersDAO dao = new UsersDAO(connection);
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
