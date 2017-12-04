package accounts;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserAccount implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "login", unique = true, updatable = false)
    private String login;
    
    @Column(name = "password", unique = true, updatable = false)
    private String password;
    
    @Column(name = "email", unique = true, updatable = false)
    private String email;

    @SuppressWarnings("UnusedDeclaration")
    public UserAccount() {
    }
    
    public UserAccount(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
    
    public UserAccount(long id, String login, String password, String email) {
        this(login, password, email);
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
       
    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\"' +
                ", E-Mail='" + email + '\"' +
                '}';
    }
}
