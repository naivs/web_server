package accounts;

import dbService.DBException;
import java.util.HashMap;
import java.util.Map;
import services.DBService;

public class AccountService {

    private final Map<String, UserAccount> loginToProfile;
    private final Map<String, UserAccount> sessionIdToProfile;
    private final DBService dbService;

    public AccountService(DBService dbService) {
        this.dbService = dbService;
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();

        //load profiles from database
        try {
            for (UserAccount userAccount : dbService.getUsers()) {
                loginToProfile.put(userAccount.getLogin(), userAccount);
            }
        } catch (DBException ex) {
            System.err.println("Can't load user accounts from database!" + ex.getMessage());
        }
    }

    public void addNewUser(String login, String password, String email) {
        UserAccount userAccount = new UserAccount(login, password, email);
        loginToProfile.put(login, userAccount);
        try {
            dbService.addUser(userAccount);
        } catch (DBException ex) {
            System.err.println("Can't add user account to database! " + ex.getMessage());
        }
    }

    public UserAccount getUserAccount(String login) {
        return loginToProfile.get(login);
    }
    
    public String getUserPassword(String login) {
        return loginToProfile.get(login).getPassword();
    }
    
    public String getUserEmail(String login) {
        return loginToProfile.get(login).getEmail();
    }

    public void addSession(String sessionId, String login) {
        sessionIdToProfile.put(sessionId, loginToProfile.get(login));
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }

    public boolean isRegistered(String login) {
        return loginToProfile.containsKey(login);
    }

    public boolean isLogin(String sessionId) {
        return sessionIdToProfile.containsKey(sessionId);
    }
}
