package service.transactionManager;

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
public class SubscribeManager extends DAOManager {
    private MySqlDAOFactory mySqlDAOFactory = DAOFactory.getMySqlDAOFactory();
    private IAccount iAccount = mySqlDAOFactory.getAccountDAO();
    private IPayment iPayment = mySqlDAOFactory.getPaymentDAO();
    private ISubscription iSubscription = mySqlDAOFactory.getSubscriptionDAO();

    public boolean subscribe(Subscription subscription) {
        Connection connection = null;
        try {
            connection = getConnection();
            iAccount.updateAccount(subscription.getUser().getAccount(), connection);
            iPayment.insertPayment(subscription.getPayment(), connection);
            iSubscription.insertSubscription(subscription, connection);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } finally {
            try {
                close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
