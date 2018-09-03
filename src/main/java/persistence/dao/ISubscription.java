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
    Subscription findSubscriptionById(int id) throws SQLException;

    /**
     * subscription's selection by user id
     *
     * @param id - user id
     * @return - list of subscriptions
     */
    ArrayList<Subscription> findSubscriptionsByUser(int id) throws SQLException;

    /**
     * subscription's selection by periodical id
     *
     * @param id - periodical id
     * @return - list of subscriptions
     */
    ArrayList<Subscription> findSubscriptionsByPeriodical(int id) throws SQLException;

    /**
     * Selection all subscriptions in the database
     *
     * @return - list of subscriptions
     */
    ArrayList<Subscription> getAllSubscription() throws SQLException;

    /**
     * Insert new subscription
     *
     * @param subscription - subscription to be inserted to the database
     * @param connection   - connection with setAutoCommit(false)
     */
    void insertSubscription(Subscription subscription, Connection connection) throws SQLException;

    /**
     * Update subscription info
     *
     * @param subscription - subscription info to be updated in the database
     */
    void updateSubscription(Subscription subscription) throws SQLException;

    /**
     * Delete subscription
     *
     * @param subscription - subscription to be deleted from the database
     */
    void deleteSubscription(Subscription subscription) throws SQLException;
}
