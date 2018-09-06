package service.transactionManager;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IAccountDAO;
import persistence.dao.IPaymentDAO;
import persistence.dao.ISubscriptionDAO;
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
    private static SubscribeManager subscribeManager;
    private MySqlDAOFactory mySqlDAOFactory = DAOFactory.getMySqlDAOFactory();

    private SubscribeManager() {
    }

    public static SubscribeManager getInstance() {
        if (subscribeManager == null) {
            subscribeManager = new SubscribeManager();
        }
        return subscribeManager;
    }

    public boolean subscribe(Subscription subscription) {
        IAccountDAO iAccountDAO = mySqlDAOFactory.getAccountDAO();
        IPaymentDAO iPaymentDAO = mySqlDAOFactory.getPaymentDAO();
        ISubscriptionDAO iSubscriptionDAO = mySqlDAOFactory.getSubscriptionDAO();
        Connection connection = null;
        try {
            connection = getConnection();
            iAccountDAO.updateAccount(subscription.getUser().getAccount(), connection);
            iPaymentDAO.insertPayment(subscription.getPayment(), connection);
            iSubscriptionDAO.insertSubscription(subscription, connection);
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
