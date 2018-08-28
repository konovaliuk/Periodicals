package persistence.dao;

import persistence.entities.Subscription;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Julia on 09.08.2018
 */
public interface ISubscription {

    /**
     * subscription's selection by id
     *
     * @param id - subscription id
     * @return - subscription or null
     */
    Subscription findSubscriptionById(int id);

    public ArrayList<Subscription> findSubscriptionsByUser(int id);

    /**
     * Selection all subscriptions in the database
     *
     * @return - list of subscriptions
     */
    ArrayList<Subscription> getAllSubscription();

    /**
     * Insert new subscription
     *
     * @param subscription - subscription to be inserted to the database
     * @param connection - connection with setAutoCommit(false)
     * @return - {@code true} if new subscription id added, {@code false} if no records is inserted
     */
    boolean insertSubscription(Subscription subscription, Connection connection) throws SQLException;

    /**
     * Update subscription info
     *
     * @param subscription - subscription info to be updated in the database
     * @return - {@code true} if subscription info is updated, {@code false} if no records is updated
     */
    boolean updateSubscription(Subscription subscription);

    /**
     * Delete subscription
     *
     * @param subscription - subscription to be deleted from the database
     * @return - {@code true} if subscription is deleted, {@code false} if no records is deleted
     */
    boolean deleteSubscription(Subscription subscription);
}
