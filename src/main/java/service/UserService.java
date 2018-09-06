package service;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IUserDAO;
import persistence.dao.IUserRoleDAO;
import persistence.dao.daoFactory.DAOFactory;
import persistence.dao.daoFactory.MySqlDAOFactory;
import persistence.entities.User;
import persistence.entities.UserRole;
import service.transactionManager.RegisterManager;

import java.sql.SQLException;

/**
 * UserService is responsible for user DTO
 * Designed as singleton
 * Created by Julia on 15.08.2018
 */
public class UserService {
    private final Logger logger = LoggerLoader.getLogger(UserService.class);
    private static UserService userService;
    private MySqlDAOFactory mySqlDAOFactory = DAOFactory.getMySqlDAOFactory();

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    /**
     * Provides login operation and check password
     *
     * @param login    user login
     * @param password user password
     * @return user if login is success, otherwise null
     */
    public User login(String login, String password) {
        User user = getUserByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    /**
     * Provides register operation
     *
     * @param name     user name
     * @param login    user login
     * @param password user password
     * @return true if user is success registered, otherwise false
     */
    public boolean register(String name, String login, String password) {
        UserRole userRole;
        try {
            userRole = getUserRole("reader");
        } catch (SQLException e) {
            logger.error("Failed to find userRole by role", e);
            return false;
        }
        User user = new User(userRole, null, name, login, password);
        return RegisterManager.getInstance().register(user);
    }

    /**
     * Returns user role
     *
     * @param role user role
     * @return userRole if such role exists, otherwise null
     */
    private UserRole getUserRole(String role) throws SQLException {
        IUserRoleDAO iUserRoleDAO = mySqlDAOFactory.getUserRoleDAO();
        return iUserRoleDAO.findUserRoleByRole(role);
    }

    /**
     * Returns user that has certain login
     *
     * @param login user login
     * @return user if user with such login exists, otherwise null
     */
    public User getUserByLogin(String login) {
        IUserDAO iUserDAO = mySqlDAOFactory.getUserDAO();
        try {
            return iUserDAO.findUserByLogin(login);
        } catch (SQLException e) {
            logger.error("Failed to find user by login", e);
            return null;
        }
    }

}
