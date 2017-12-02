/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import accounts.UserProfile;
import dbService.DBException;
import dbService.dataSets.UsersDataSet;
import java.util.ArrayList;

/**
 *
 * @author Ivan Naumov
 */
public interface DBService {
    
    public UsersDataSet getUser(long id) throws DBException;
    
    public ArrayList<UserProfile> getUsers() throws DBException;
    
    public long addUser(UserProfile userProfile) throws DBException;
    
    public void cleanUp() throws DBException;
    
    public void printConnectInfo();
}
