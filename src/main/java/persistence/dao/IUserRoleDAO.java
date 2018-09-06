package persistence.dao;

import persistence.entities.UserRole;

import java.sql.SQLException;

/**
 * IUserRoleDAO interface provides methods for manipulation of user_role table
 * Created by Julia on 09.08.2018
 */
public interface IUserRoleDAO {

    /**
     * Role's selection by id
     *
     * @param id - userRole id
     * @return - userRole or null
     * @throws SQLException
     */
    UserRole findRoleById(int id) throws SQLException;

    /**
     * Role's selection by role
     *
     * @param role - userRole's role
     * @return - UserRole or null
     * @throws SQLException
     */
    UserRole findUserRoleByRole(String role) throws SQLException;

    /**
     * Insert new role
     *
     * @param role - role to be inserted to the database
     * @throws SQLException
     */
    void insertRole(UserRole role) throws SQLException;

    /**
     * Update role info
     *
     * @param role - role info to be updated in the database
     * @throws SQLException
     */
    void updateRole(UserRole role) throws SQLException;

    /**
     * Delete role
     *
     * @param role - role to be deleted from the database
     * @throws SQLException
     */
    void deleteRole(UserRole role) throws SQLException;

}
