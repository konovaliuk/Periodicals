package service.transactionManager;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IAccount;
import persistence.dao.IUser;
import persistence.dao.daoFactory.DAOFactory;
import persistence.dao.daoFactory.MySqlDAOFactory;
import persistence.entities.Account;
import persistence.entities.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Julia on 01.09.2018
 */
public class RegisterManager extends TransactionManager {
    private static final Logger logger = LoggerLoader.getLogger(RegisterManager.class);

    private MySqlDAOFactory mySqlDAOFactory = DAOFactory.getMySqlDAOFactory();

    public boolean register(User user) {
        IUser iUser = mySqlDAOFactory.getUserDAO();
        IAccount iAccount = mySqlDAOFactory.getAccountDAO();
        Connection connection = null;
        try {
            connection = getConnection();
            iUser.insertUser(user, connection);
            Account account = new Account(user.getId(), new BigDecimal(100));
            iAccount.insertAccount(account, connection);
            connection.commit();
        } catch (SQLException e) {
            logger.error("Failed to insert user", e);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                logger.error("Failed to rollback", e1);
            }
            return false;
        } finally {
            close(connection);
        }
        return true;
    }
}
