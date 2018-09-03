package service.transactionManager;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IAccount;
import persistence.dao.IPayment;
import persistence.dao.ISubscription;
import persistence.dao.daoFactory.DAOFactory;
import persistence.dao.daoFactory.MySqlDAOFactory;
import persistence.entities.Subscription;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Created by Julia on 28.08.2018
 */
public class SubscribeManager extends TransactionManager {
    private static final Logger logger = LoggerLoader.getLogger(SubscribeManager.class);
    private MySqlDAOFactory mySqlDAOFactory = DAOFactory.getMySqlDAOFactory();

    public boolean subscribe(Subscription subscription) {
        IAccount iAccount = mySqlDAOFactory.getAccountDAO();
        IPayment iPayment = mySqlDAOFactory.getPaymentDAO();
        ISubscription iSubscription = mySqlDAOFactory.getSubscriptionDAO();
        Connection connection = null;
        try {
            connection = getConnection();
            iAccount.updateAccount(subscription.getUser().getAccount(), connection);
            iPayment.insertPayment(subscription.getPayment(), connection);
            iSubscription.insertSubscription(subscription, connection);
            connection.commit();
        } catch (SQLException e) {
            logger.error("Transaction failed", e);
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
