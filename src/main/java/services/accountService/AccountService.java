/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.accountService;

import accounts.UserAccount;
import services.Service;

/**
 *
 * @author Ivan Naumov
 */
public interface AccountService extends Service {
    
    void addNewUser(String login, String password, String email);
    
    UserAccount getUserBySessionId(String sessionId);

    UserAccount getUserByLogin(String login);
    
    String getUserPassword(String login);
    
    String getUserEmail(String login);

    void addSession(String sessionId, String login);

    void deleteSession(String sessionId);

    boolean isRegistered(String login);

    boolean isLogin(String sessionId);

    int getSessionsLimit();

    void setSessionsLimit(int sessionsLimit);
    
    int getSessionCount();
}
