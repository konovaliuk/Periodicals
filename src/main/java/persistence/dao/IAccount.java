package persistence.dao;

import persistence.entities.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Julia on 28.08.2018
 */
public interface IAccount {
    /**
     * Account's selection by id
     *
     * @param id - account id
     * @return - account or null
     */
    Account findAccountById(int id);

    /**
     * Selection all accounts in the database
     *
     * @return - list of accounts
     */
    ArrayList<Account> findAllAccounts();

    /**
     * Insert new account
     *
     * @param account - account to be inserted to the database
     * @return - {@code true} if new account id added, {@code false} if no records is inserted
     */
    boolean insertAccount(Account account);

    /**
     * Update account info
     *
     * @param account    - account info to be updated in the database
     * @param connection - connection with setAutoCommit(false)
     * @return - {@code true} if account info is updated, {@code false} if no records is updated
     */
    boolean updateAccount(Account account, Connection connection) throws SQLException;

    /**
     * Delete account
     *
     * @param account - account to be deleted from the database
     * @return - {@code true} if account is deleted, {@code false} if no records is deleted
     */
    boolean deleteAccount(Account account);
}
