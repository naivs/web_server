package dbService.dao;

import accounts.UserProfile;
import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UsersDataSet get(long id) throws SQLException {
        return executor.execQuery("select * from users where id=" + id, result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2));
        });
    }
    
    public ArrayList<UserProfile> getUsers() throws SQLException {
        return executor.execQuery("select * from users", (resultSet) -> {
            ArrayList<UserProfile> userProfiles = new ArrayList<>();
            while(resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                userProfiles.add(new UserProfile(login, password, email));
            }
            return userProfiles;
        });
    }

    public long getUserId(String login) throws SQLException {
        return executor.execQuery("select id from users where login='" + login + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(UserProfile userProfile) throws SQLException {
        executor.execUpdate(
                "insert into users (login, password, email) values ('" + userProfile.getLogin() + "', '" + userProfile.getPass() + "', '" + userProfile.getEmail() + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(250), email varchar(250), primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
}
