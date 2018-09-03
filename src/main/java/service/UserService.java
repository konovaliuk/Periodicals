package service;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IUser;
import persistence.dao.IUserRole;
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
    private static final Logger logger = LoggerLoader.getLogger(UserService.class);
    private static MySqlDAOFactory mySqlDAOFactory = DAOFactory.getMySqlDAOFactory();

    public static User login(String login, String password) {
        User user = getUserByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public static boolean register(User user) {
        RegisterManager registerManager = new RegisterManager();
        return registerManager.register(user);
    }

    public static UserRole getUserRole(String role) {
        IUserRole iUserRole = mySqlDAOFactory.getUserRoleDAO();
        try {
            return iUserRole.findRoleByRole(role);
        } catch (SQLException e) {
            logger.error("Failed to find userRole by role", e);
            return null;
        }
    }

    public static User getUserByLogin(String login) {
        IUser iUser = mySqlDAOFactory.getUserDAO();
        try {
            return iUser.findUserByLogin(login);
        } catch (SQLException e) {
            logger.error("Failed to find user by login", e);
            return null;
        }
    }

}
