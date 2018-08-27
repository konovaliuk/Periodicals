package persistence.entities;


import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Julia on 27.07.2018
 */
public class User implements Serializable {

    private int id;
    private UserRole userRole;
    private String name;
    private String login;
    private String password;

    public User() {
    }

    public User(int id, UserRole userRole, String name, String login, String password) {
        this.id = id;
        this.userRole = userRole;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(UserRole userRole, String name, String login, String password) {
        this.userRole = userRole;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(userRole, user.userRole) &&
                Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userRole, name, login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userRole=" + userRole +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
