package persistence.dao.mySqlDAOImpl;


import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IUserRole;
import persistence.entities.UserRole;

import java.sql.*;

/**
 * Created by Julia on 09.08.2018
 */
public class UserRoleDAO extends AbstractDAO implements IUserRole {

    private static UserRoleDAO userRoleDAO;

    private final String SELECT_ALL_FROM_USER_ROLE = "SELECT * FROM user_role ";

    private final String INSERT_USER_ROLE = "INSERT INTO user_role (role) VALUES (?)";

    private final String UPDATE_USER_ROLE = "UPDATE user_role SET user_role.role = ? WHERE user_role.id = ?";

    private UserRoleDAO() {
    }

    public static UserRoleDAO getInstance() {
        if (userRoleDAO == null) {
            userRoleDAO = new UserRoleDAO();
        }
        return userRoleDAO;
    }

    @Override
    public UserRole findRoleById(int id) throws SQLException {
        UserRole role = null;
        role = findById(SELECT_ALL_FROM_USER_ROLE + "WHERE user_role.id= ?", id,
                set -> set != null ? new UserRole(
                        set.getInt("id"),
                        set.getString("role")) : null);
        return role;
    }

    @Override
    public UserRole findRoleByRole(String role) throws SQLException {
        UserRole userRole = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_USER_ROLE + "WHERE user_role.role= ?")) {
            statement.setString(1, role);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    userRole = new UserRole(resultSet.getInt("id"),
                            resultSet.getString("role"));
                }
            }
        }
        return userRole;
    }

    @Override
    public boolean insertRole(UserRole role) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_ROLE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, role.getRole());
            if (statement.executeUpdate() != 0) {
                role.setId(getGeneratedKey(statement));
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean updateRole(UserRole role) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ROLE)) {
            statement.setString(1, role.getRole());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteRole(UserRole role) throws SQLException {
        String query = "DELETE FROM user_role WHERE user_role.id = " + role.getId();
        if (execute(query) != 0) {
            return true;
        }
        return false;
    }
}

