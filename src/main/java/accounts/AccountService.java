package accounts;

import dbService.DBException;
import dbService.DBService;
import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    private final DBService dbService;

    public AccountService(DBService dbService) {
        this.dbService = dbService;
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();

        //load profiles from database
        try {
            for (UserProfile userProfile : dbService.getUsers()) {
                loginToProfile.put(userProfile.getLogin(), userProfile);
            }
        } catch (DBException ex) {
            System.err.println("Can't load user profiles from database!" + ex.getMessage());
        }
    }

    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
        try {
            dbService.addUser(userProfile);
        } catch (DBException ex) {
            System.err.println("Can't add user profile to database! " + ex.getMessage());
        }
    }

    public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
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
