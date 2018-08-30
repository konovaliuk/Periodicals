package persistence.dao;

import persistence.entities.User;

import java.sql.SQLException;

/**
 * Created by Julia on 09.08.2018
 */
public interface IUser {

    /**
     * User's selection by id
     *
     * @param id - user id
     * @return - user or null
     */
    User findUserById(int id) throws SQLException;


    /**
     * User's selection by login
     *
     * @param login - user's login
     * @return - user or null
     */
    User findUserByLogin(String login) throws SQLException;

    /**
     * Insert new user
     *
     * @param user - user to be inserted to the database
     * @return - {@code true} if new user id added, {@code false} if no records is inserted
     */
    boolean insertUser(User user) throws SQLException;

    /**
     * Update user info
     *
     * @param user - user info to be updated in the database
     * @return - {@code true} if user info is updated, {@code false} if no records is updated
     */
    boolean updateUser(User user) throws SQLException;

    /**
     * Delete user
     *
     * @param user - user to be deleted from the database
     * @return - {@code true} if user is deleted, {@code false} if no records is deleted
     */
    boolean deleteUser(User user) throws SQLException;

}
