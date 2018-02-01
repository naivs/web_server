/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversations;

import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author Ivan Naumov
 */
public class Conversation {
    
    private String name;
    private Set<Long> usersId;
    private LinkedList<Message> history;
    
    public Conversation() {
        
    }
}
