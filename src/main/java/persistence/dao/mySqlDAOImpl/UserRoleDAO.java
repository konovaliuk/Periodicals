package persistence.dao.mySqlDAOImpl;


import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IUserRoleDAO;
import persistence.entities.UserRole;

import java.sql.*;

/**
 * Created by Julia on 09.08.2018
 */
public class UserRoleDAO extends AbstractDAO implements IUserRoleDAO {
    private final Logger logger = LoggerLoader.getLogger(UserRoleDAO.class);

    private static UserRoleDAO userRoleDAO;

    private final String SELECT_ALL_FROM_USER_ROLE = "SELECT * FROM user_role ";

    private final String INSERT_USER_ROLE = "INSERT INTO user_role (role) VALUES (?)";

    private final String UPDATE_USER_ROLE = "UPDATE user_role SET user_role.role = ? WHERE user_role.id = ?";

    private final String DELETE_USER_ROLE = "DELETE FROM user_role WHERE user_role.id = ?";

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
        UserRole role;
        try {
            role = findById(SELECT_ALL_FROM_USER_ROLE + "WHERE user_role.id= ?", id,
                    set -> set != null ? new UserRole(
                            set.getInt("id"),
                            set.getString("role")) : null);
        } catch (SQLException e) {
            logger.error("Failed to find role by id ", e);
            throw new SQLException();
        }
        return role;
    }

    @Override
    public UserRole findUserRoleByRole(String role) throws SQLException {
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
        } catch (SQLException e) {
            logger.error("Failed to find user role by role ", e);
            throw new SQLException();
        }
        return userRole;
    }

    @Override
    public void insertRole(UserRole role) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_ROLE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, role.getRole());
            statement.executeUpdate();
            role.setId(getGeneratedKey(statement));
        } catch (SQLException e) {
            logger.error("Failed to insert role ", e);
            throw new SQLException();
        }
    }

    @Override
    public void updateRole(UserRole role) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ROLE)) {
            statement.setString(1, role.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to update role ", e);
            throw new SQLException();
        }
    }

    @Override
    public void deleteRole(UserRole role) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_ROLE)) {
            statement.setInt(1, role.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to delete role ", e);
            throw new SQLException();
        }
    }
}

