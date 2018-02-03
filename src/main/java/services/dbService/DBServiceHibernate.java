/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.dbService;

import services.dbService.dao.UsersDAOHibernate;
import accounts.UserAccount;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import resources.HBNParametersResource;

/**
 *
 * @author Ivan Naumov
 */
public class DBServiceHibernate implements DBService {

    private final HBNParametersResource hbnParameters;
    private final SessionFactory sessionFactory;

    public DBServiceHibernate(HBNParametersResource hbnParameters) {
        this.hbnParameters = hbnParameters;
        Configuration configuration = getMySqlConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserAccount.class);

        configuration.setProperty("hibernate.dialect", hbnParameters.getDialect());
        configuration.setProperty("hibernate.connection.driver_class", hbnParameters.getDriver());
        configuration.setProperty("hibernate.connection.url", hbnParameters.getUrl());
        configuration.setProperty("hibernate.connection.username", hbnParameters.getUsername());
        configuration.setProperty("hibernate.connection.password", hbnParameters.getPassword());
        configuration.setProperty("hibernate.show_sql", hbnParameters.getHibernate_show_sql());
        configuration.setProperty("hibernate.hbm2ddl.auto", hbnParameters.getHibernate_hbm2ddl_auto());
        return configuration;
    }
    
//    private Configuration getH2Configuration() {
//        Configuration configuration = new Configuration();
//        configuration.addAnnotatedClass(UsersDataSet.class);
//
//        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
//        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
//        configuration.setProperty("hibernate.connection.username", "tully");
//        configuration.setProperty("hibernate.connection.password", "tully");
//        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
//        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
//        return configuration;
//    }

    @Override
    public UserAccount getUser(long id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UsersDAOHibernate dao = new UsersDAOHibernate(session);
            UserAccount dataSet = dao.get(id);
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    @Override
    public ArrayList<UserAccount> getUsers() throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UsersDAOHibernate dao = new UsersDAOHibernate(session);
            ArrayList<UserAccount> userAccounts = dao.getAll();
            session.close();
            return userAccounts;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    @Override
    public long addUser(UserAccount userAccount) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDAOHibernate dao = new UsersDAOHibernate(session);
            long id = dao.insertUser(userAccount);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void cleanUp() throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printConnectInfo() {
        try {
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
