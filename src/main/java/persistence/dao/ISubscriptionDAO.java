package persistence.dao;

import persistence.entities.Subscription;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ISubscriptionDAO interface provides methods for manipulation of subscription table
 * Created by Julia on 09.08.2018
 */
public interface ISubscriptionDAO {

    /**
     * subscription's selection by id
     *
     * @param id - subscription id
     * @return - subscription or null
     * @throws SQLException
     */
    Subscription findSubscriptionById(int id) throws SQLException;

    /**
     * subscription's selection by user id
     *
     * @param id - user id
     * @return - list of subscriptions
     * @throws SQLException
     */
    ArrayList<Subscription> findSubscriptionsByUser(int id) throws SQLException;

    /**
     * subscription's selection by periodical id
     *
     * @param id - periodical id
     * @return - list of subscriptions
     * @throws SQLException
     */
    ArrayList<Subscription> findSubscriptionsByPeriodical(int id) throws SQLException;

    /**
     * Selection all subscriptions in the database
     *
     * @return - list of subscriptions
     * @throws SQLException
     */
    ArrayList<Subscription> findAllSubscription() throws SQLException;

    /**
     * Insert new subscription
     *
     * @param subscription - subscription to be inserted to the database
     * @param connection   - connection with setAutoCommit(false)
     * @throws SQLException
     */
    void insertSubscription(Subscription subscription, Connection connection) throws SQLException;

    /**
     * Update subscription info
     *
     * @param subscription - subscription info to be updated in the database
     * @throws SQLException
     */
    void updateSubscription(Subscription subscription) throws SQLException;

    /**
     * Delete subscription
     *
     * @param subscription - subscription to be deleted from the database
     * @throws SQLException
     */
    void deleteSubscription(Subscription subscription) throws SQLException;
}
