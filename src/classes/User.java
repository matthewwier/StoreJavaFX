package classes;
/**
 * Class defines user.
 *
 */
public class User {
    private String username;
    private String password;
    private String email;

    public User(String login, String password, String email) {
        this.username = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return username;
    }

    public void setLogin(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
