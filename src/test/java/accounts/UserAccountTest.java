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
            
    private final String login = "ivan";
    private final String password = "password123_";
    private final String email = "ivan@gmail.com";
    
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
        System.out.println("setting up test...");
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
        String expResult = password;
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class UserAccount.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = email;
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class UserAccount.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 0L;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }
    
    /**
     * Test of setLogin method, of class UserAccount.
     */
    @Test
    public void testSetLogin() {
        System.out.println("setLogin");
        String loginTest = "test";
        instance.setLogin(loginTest);
        assertEquals(loginTest, instance.getLogin());
    }

    /**
     * Test of setPassword method, of class UserAccount.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String passwordTest = "123test123_";
        instance.setPassword(passwordTest);
        assertEquals(passwordTest, instance.getPassword());
    }

    /**
     * Test of setEmail method, of class UserAccount.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String emailTest = "test@hell.com";
        instance.setEmail(emailTest);
        assertEquals(emailTest, instance.getEmail());
    }

    /**
     * Test of toString method, of class UserAccount.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        long idTest = 666L;
        String loginTest = "satan";
        String passwordTest = "@crazY_666";
        String emailTest = "satan666@hell.com";
        
        String expResult = "UsersDataSet{" +
                "id=" + idTest +
                ", login='" + loginTest + '\'' +
                ", password='" + passwordTest + '\"' +
                ", E-Mail='" + emailTest + '\"' +
                '}';
        
        instance.setId(idTest);
        instance.setLogin(loginTest);
        instance.setPassword(passwordTest);
        instance.setEmail(emailTest);
        assertEquals(expResult, instance.toString());
    }
}
