package persistence.dao;

import persistence.entities.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * IUserDAO interface provides methods for manipulation of user table
 * Created by Julia on 09.08.2018
 */
public interface IUserDAO {

    /**
     * User's selection by id
     *
     * @param id - user id
     * @return - user or null
     * @throws SQLException
     */
    User findUserById(int id) throws SQLException;


    /**
     * User's selection by login
     *
     * @param login - user's login
     * @return - user or null
     * @throws SQLException
     */
    User findUserByLogin(String login) throws SQLException;

    /**
     * Insert new user
     *
     * @param user - user to be inserted to the database
     * @throws SQLException
     */
    void insertUser(User user, Connection connection) throws SQLException;

    /**
     * Update user info
     *
     * @param user - user info to be updated in the database
     * @throws SQLException
     */
    void updateUser(User user) throws SQLException;

    /**
     * Delete user
     *
     * @param user - user to be deleted from the database
     * @throws SQLException
     */
    void deleteUser(User user) throws SQLException;

}
