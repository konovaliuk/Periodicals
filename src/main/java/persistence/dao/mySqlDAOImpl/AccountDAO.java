package persistence.dao.mySqlDAOImpl;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IAccountDAO;
import persistence.entities.Account;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Julia on 28.08.2018
 */
public class AccountDAO extends AbstractDAO implements IAccountDAO {
    private final Logger logger = LoggerLoader.getLogger(AccountDAO.class);

    private static AccountDAO accountDAO;

    private final String SELECT_ALL_FROM_ACCOUNT = "SELECT * FROM account ";

    private final String INSERT_ACCOUNT = "INSERT INTO account (id,amount) " +
            "VALUES (?,?)";

    private final String UPDATE_ACCOUNT = "UPDATE account SET account.amount = ?" +
            " WHERE account.id = ?";

    private final String DELETE_ACCOUNT = "DELETE FROM account WHERE account.id = ?";

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
        try {
            account = findById(SELECT_ALL_FROM_ACCOUNT + " WHERE account.id = ?", id,
                    set -> set != null ? new Account(
                            set.getInt("id"),
                            set.getBigDecimal("amount")) : null);
        } catch (SQLException e) {
            logger.error("Failed to find account by id", e);
            throw new SQLException();
        }
        return account;
    }

    @Override
    public ArrayList<Account> findAllAccounts() throws SQLException {
        ArrayList<Account> accounts = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_ACCOUNT)) {
                while (resultSet.next()) {
                    Account account = new Account(resultSet.getInt("id"),
                            resultSet.getBigDecimal("amount"));
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to find all accounts ", e);
            throw new SQLException();
        }
        return accounts;
    }

    @Override
    public void insertAccount(Account account, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ACCOUNT)) {
            statement.setInt(1, account.getId());
            statement.setBigDecimal(2, account.getAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to insert account ", e);
            throw new SQLException();
        }
    }

    @Override
    public void updateAccount(Account account, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT)) {
            statement.setBigDecimal(1, account.getAmount());
            statement.setInt(2, account.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to update account ", e);
            throw new SQLException();
        }
    }

    @Override
    public void deleteAccount(Account account) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ACCOUNT)) {
            statement.setInt(1, account.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to delete account", e);
            throw new SQLException();
        }
    }
}
