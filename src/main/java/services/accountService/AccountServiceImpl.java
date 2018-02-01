package services.accountService;

import accounts.UserAccount;
import services.dbService.DBException;
import java.util.HashMap;
import java.util.Map;
import services.accountService.AccountService;
import services.dbService.DBService;

public class AccountServiceImpl implements AccountService {

    private int sessionsLimit;
    private final Map<String, UserAccount> loginToProfile;
    private final Map<String, UserAccount> sessionIdToProfile;
    private final DBService dbService;

    public AccountServiceImpl(int sessionsLimit, DBService dbService) {
        this.sessionsLimit = sessionsLimit;
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

    @Override
    public void addNewUser(String login, String password, String email) {
        UserAccount userAccount = new UserAccount(login, password, email);
        loginToProfile.put(login, userAccount);
        try {
            dbService.addUser(userAccount);
        } catch (DBException ex) {
            System.err.println("Can't add user account to database! " + ex.getMessage());
        }
    }
    
    @Override
    public UserAccount getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    @Override
    public UserAccount getUserByLogin(String login) {
        return loginToProfile.get(login);
    }
    
    @Override
    public String getUserPassword(String login) {
        return loginToProfile.get(login).getPassword();
    }
    
    @Override
    public String getUserEmail(String login) {
        return loginToProfile.get(login).getEmail();
    }

    @Override
    public void addSession(String sessionId, String login) {
        sessionIdToProfile.put(sessionId, loginToProfile.get(login));
    }

    @Override
    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }

    @Override
    public boolean isRegistered(String login) {
        return loginToProfile.containsKey(login);
    }

    @Override
    public boolean isLogin(String sessionId) {
        return sessionIdToProfile.containsKey(sessionId);
    }

    @Override
    public int getSessionsLimit() {
        return sessionsLimit;
    }

    @Override
    public void setSessionsLimit(int sessionsLimit) {
        this.sessionsLimit = sessionsLimit;
    }

    @Override
    public int getSessionCount() {
        return sessionIdToProfile.size();
    }
}
