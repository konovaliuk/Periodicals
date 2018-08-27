package persistence.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Julia on 27.07.2018
 */
public class UserRole implements Serializable {

   private int id;
   private String role;

    public UserRole(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public UserRole() {
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return id == userRole.id &&
                Objects.equals(role, userRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
