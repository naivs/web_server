/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ivan Naumov
 */
public class UserAccountTest {
    
    private UserAccount instance;
            
    private String login = "ivan";
    private String password = "password123_";
    private String email = "ivan@gmail.com";
    
    public UserAccountTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("getLogin");
        instance = new UserAccount(login, password, email);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLogin method, of class UserAccount.
     */
    @Test
    public void testGetLogin() {
        String expResult = login;
        String result = instance.getLogin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class UserAccount.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        UserAccount instance = new UserAccount();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class UserAccount.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        UserAccount instance = new UserAccount();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class UserAccount.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 0L;
        UserAccount instance = new UserAccount();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLogin method, of class UserAccount.
     */
    @Test
    public void testSetLogin() {
        System.out.println("setLogin");
        String login = "";
        UserAccount instance = new UserAccount();
        instance.setLogin(login);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class UserAccount.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        UserAccount instance = new UserAccount();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class UserAccount.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        UserAccount instance = new UserAccount();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class UserAccount.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        UserAccount instance = new UserAccount();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
