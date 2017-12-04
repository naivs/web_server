/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbService.dao;

import accounts.UserAccount;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ivan Naumov
 */
public class UsersDAOHibernate {
    
    private Session session;

    public UsersDAOHibernate(Session session) {
        this.session = session;
    }

    public UserAccount get(long id) throws HibernateException {
        return (UserAccount) session.get(UserAccount.class, id);
    }

    public long getUserId(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UserAccount.class);
        return ((UserAccount) criteria.add(Restrictions.eq("login", login)).uniqueResult()).getId();
    }
    
    public ArrayList<UserAccount> getAll() throws HibernateException {
        Criteria criteria = session.createCriteria(UserAccount.class);
        return (ArrayList<UserAccount>) criteria.list();
    }

    public long insertUser(UserAccount usersDataSet) throws HibernateException {
        return (Long) session.save(usersDataSet);
    }
}
