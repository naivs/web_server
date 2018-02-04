/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

/**
 *
 * @author ivan
 */
public class HBNParametersResource implements Resource {

    private final String hibernate_show_sql;
    private final String hibernate_hbm2ddl_auto;
    private final String dialect;
    private final String driver;
    private final String url;
    private final String username;
    private final String password;

    public HBNParametersResource() {
        hibernate_show_sql = "";
        hibernate_hbm2ddl_auto = "";
        dialect = "";
        driver = "";
        url = "";
        username = "";
        password = "";
    }

    public HBNParametersResource(
            String hibernate_show_sql,
            String hibernate_hbm2ddl_auto,
            String dialect,
            String driver,
            String url,
            String username,
            String password
    ) {
        this.hibernate_show_sql = hibernate_show_sql;
        this.hibernate_hbm2ddl_auto = hibernate_hbm2ddl_auto;
        this.dialect = dialect;
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getHibernate_show_sql() {
        return hibernate_show_sql;
    }

    public String getHibernate_hbm2ddl_auto() {
        return hibernate_hbm2ddl_auto;
    }

    public String getDialect() {
        return dialect;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "HBNParametersResource{"
                + "hibernate_show_sql=" + hibernate_show_sql
                + ", hibernate_hbm2ddl_auto=" + hibernate_hbm2ddl_auto
                + ", dialect=" + dialect
                + ", driver=" + driver
                + ", url=" + url
                + ", username=" + username
                + ", password=" + password
                + '}';
    }
}
