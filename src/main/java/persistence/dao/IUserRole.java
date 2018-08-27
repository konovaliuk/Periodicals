package persistence.dao;

import persistence.entities.UserRole;

/**
 * Created by Julia on 09.08.2018
 */
public interface IUserRole {

    /**
     * Role's selection by id
     *
     * @param id - userRole id
     * @return - userRole or null
     */
    UserRole findRoleById(int id);

    /**
     * Role's selection by role
     *
     * @param role - userRole's role
     * @return - UserRole or null
     */
    UserRole findRoleByRole(String role);

    /**
     * Insert new role
     *
     * @param role - role to be inserted to the database
     * @return - {@code true} if new role id added, {@code false} if no records is inserted
     */
    boolean insertRole(UserRole role);

    /**
     * Update role info
     *
     * @param role - role info to be updated in the database
     * @return - {@code true} if role info is updated, {@code false} if no records is updated
     */
    boolean updateRole(UserRole role);

    /**
     * Delete role
     *
     * @param role - role to be deleted from the database
     * @return - {@code true} if role is deleted, {@code false} if no records is deleted
     */
    boolean deleteRole(UserRole role);

}
