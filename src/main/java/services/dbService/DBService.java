/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.dbService;

import accounts.UserAccount;
import java.util.ArrayList;

/**
 *
 * @author Ivan Naumov
 */
public interface DBService {
    
    public UserAccount getUser(long id) throws DBException;
    
    public ArrayList<UserAccount> getUsers() throws DBException;
    
    public long addUser(UserAccount userAccount) throws DBException;
    
    public void cleanUp() throws DBException;
    
    public void printConnectInfo();
}
