package dbService.dataSets;

@SuppressWarnings("UnusedDeclaration")
public class UsersDataSet {
    private long id;
    private String name;
    private String password;
    private String email;

    public UsersDataSet(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\"' +
                ", E-Mail='" + email + '\"' +
                '}';
    }
}
