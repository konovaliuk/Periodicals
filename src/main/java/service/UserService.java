package service;

import persistence.dao.IUser;
import persistence.dao.IUserRole;
import persistence.dao.daoFactory.DAOFactory;
import persistence.entities.User;
import persistence.entities.UserRole;

/**
 * Created by Julia on 15.08.2018
 */
public class UserService {

    private static IUser iUser = DAOFactory.getMySqlDAOFactory().getUserDAO();
    private static IUserRole iUserRole = DAOFactory.getMySqlDAOFactory().getUserRoleDAO();

    public static User login(String login, String password) {
        User user = getUserByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public static boolean register(User user) {
        return iUser.insertUser(user);
    }

    public static UserRole getUserRole(String role) {
        return iUserRole.findRoleByRole(role);
    }

    public static User getUserByLogin(String login) {
        return iUser.findUserByLogin(login);
    }

}
