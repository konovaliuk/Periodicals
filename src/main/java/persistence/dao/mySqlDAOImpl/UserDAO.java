package persistence.dao.mySqlDAOImpl;


import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IUser;
import persistence.entities.User;
import persistence.entities.UserRole;

import java.sql.*;

/**
 * Created by Julia on 09.08.2018
 */
public class UserDAO extends AbstractDAO implements IUser {

    private static final Logger logger = LoggerLoader.getLogger(UserDAO.class);

    private static UserDAO userDAO;

    private final String SELECT_ALL_FROM_USER = "SELECT user.id,user.user_role, user_role.role, user.name, " +
            "user.login, user.password " +
            "FROM user INNER JOIN user_role ON(user.user_role = user_role.id) ";

    private final String INSERT_USER = "INSERT INTO user (user_role,name,login,password) VALUES (?,?,?,?)";

    private final String UPDATE_USER = "UPDATE user SET user.user_role = ?, user.name = ?, user.login = ?, user.password = ?\n" +
            "WHERE user.id = ?";

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    @Override
    public User findUserById(int id) {
        User user = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_USER + "WHERE user.id= ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User(resultSet.getInt("id"),
                            new UserRole(resultSet.getInt("user_role"), resultSet.getString("role")),
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to find user by id", e);
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) {
        User user = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_USER + "WHERE user.login= ?")) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User(resultSet.getInt("id"),
                            new UserRole(resultSet.getInt("user_role"), resultSet.getString("role")),
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to find user by login", e);
        }
        return user;
    }

    @Override
    public boolean insertUser(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, user.getUserRole().getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            if (statement.executeUpdate() != 0) {
                user.setId(getGeneratedKey(statement));
                return true;
            }
        } catch (SQLException e) {
            logger.error("Failed to insert user", e);
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setInt(1, user.getUserRole().getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getId());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Failed to update user", e);
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        String query = "DELETE FROM user WHERE user.id = " + user.getId();

        try {
            if (execute(query) != 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Failed to delete user", e);
        }
        return false;
    }
}
