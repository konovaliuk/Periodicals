package persistence.dao.mySqlDAOImpl;

import persistence.dao.IAccount;
import persistence.entities.Account;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Julia on 28.08.2018
 */
public class AccountDAO extends AbstractDAO implements IAccount {

    private static AccountDAO accountDAO;

    private final String SELECT_ALL_FROM_ACCOUNT = "SELECT * FROM account ";

    private final String INSERT_ACCOUNT = "INSERT INTO account (amount) " +
            "VALUES (?)";

    private final String UPDATE_ACCOUNT = "UPDATE account SET account.amount = ?" +
            " WHERE account.id = ?";

    private AccountDAO() {
    }

    public static AccountDAO getInstance() {
        if (accountDAO == null) {
            accountDAO = new AccountDAO();
        }
        return accountDAO;
    }

    @Override
    public Account findAccountById(int id) throws SQLException {
        Account account;
        account = findById(SELECT_ALL_FROM_ACCOUNT + " WHERE account.id = ?", id,
                set -> set != null ? new Account(
                        set.getInt("id"),
                        set.getBigDecimal("amount")) : null);
        return account;
    }

    @Override
    public ArrayList<Account> findAllAccounts() throws SQLException {
        ArrayList<Account> accounts = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_ACCOUNT)) {
            while (resultSet.next()) {
                Account account = new Account(resultSet.getInt("id"),
                        resultSet.getBigDecimal("amount"));
                accounts.add(account);
            }
        }
        return accounts;
    }

    @Override
    public boolean insertAccount(Account account) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_ACCOUNT);
        statement.setBigDecimal(1, account.getAmount());
        if (statement.executeUpdate() != 0) {
            /*  account.setId(getGeneratedKey(statement));*/
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAccount(Account account, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT);
        statement.setBigDecimal(1, account.getAmount());
        statement.setInt(2, account.getId());
        if (statement.executeUpdate() != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAccount(Account account) throws SQLException {
        String query = "DELETE FROM account WHERE account.id = " + account.getId();
        if (execute(query) != 0) {
            return true;
        }
        return false;
    }
}
