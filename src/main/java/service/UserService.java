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

    public User login(String login, String password) {
        User user = getUserByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean register(String name, String login, String password) {
        UserRole userRole = getUserRole("reader");
        User user = new User(userRole, null, name, login, password);
        return RegisterManager.getInstance().register(user);
    }

    public UserRole getUserRole(String role) {
        IUserRoleDAO iUserRoleDAO = mySqlDAOFactory.getUserRoleDAO();
        try {
            return iUserRoleDAO.findUserRoleByRole(role);
        } catch (SQLException e) {
            logger.error("Failed to find userRole by role", e);
            return null;
        }
    }

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
