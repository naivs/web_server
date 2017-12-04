package dbService.dao;

import accounts.UserAccount;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDAOPattern {

    private Executor executor;

    public UsersDAOPattern(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UserAccount get(long id) throws SQLException {
        return executor.execQuery("select * from users where id=" + id, result -> {
            result.next();
            return new UserAccount(result.getLong("id"), result.getString("login"),
            result.getString("password"), result.getString("email"));
        });
    }
    
    public ArrayList<UserAccount> getUsers() throws SQLException {
        return executor.execQuery("select * from users", (resultSet) -> {
            ArrayList<UserAccount> userSets = new ArrayList<>();
            while(resultSet.next()) {
                long id = resultSet.getLong("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                userSets.add(new UserAccount(id, login, password, email));
            }
            return userSets;
        });
    }

    public long getUserId(String login) throws SQLException {
        return executor.execQuery("select id from users where login='" + login + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(UserAccount userAccount) throws SQLException {
        executor.execUpdate("insert into users (login, password, email) values ('" + userAccount.getLogin() + "', '" +
                        userAccount.getPassword() + "', '" + userAccount.getEmail() + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(250), email varchar(250), primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
}
