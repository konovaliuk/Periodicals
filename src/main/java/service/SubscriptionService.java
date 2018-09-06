package service;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.ISubscriptionDAO;
import persistence.dao.daoFactory.DAOFactory;
import persistence.dao.daoFactory.MySqlDAOFactory;
import persistence.entities.Payment;
import persistence.entities.Periodical;
import persistence.entities.Subscription;
import persistence.entities.User;
import service.transactionManager.SubscribeManager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * SubscriptionService is responsible for subscription DTO
 * Designed as singleton
 * Created by Julia on 15.08.2018
 */
public class SubscriptionService {
    private final Logger logger = LoggerLoader.getLogger(SubscriptionService.class);
    private static SubscriptionService subscriptionService;
    private MySqlDAOFactory mySqlDAOFactory = DAOFactory.getMySqlDAOFactory();


    private SubscriptionService() {
    }

    public static SubscriptionService getInstance() {
        if (subscriptionService == null) {
            subscriptionService = new SubscriptionService();
        }
        return subscriptionService;
    }

    /**
     * Subscribes
     *
     * @param user        user who wants to subscribe
     * @param periodical  subscription periodical
     * @param totalAmount calculated price
     * @param term        subscription term
     * @return true if subscription is success, otherwise false
     */
    public boolean subscribe(User user, Periodical periodical, BigDecimal totalAmount, int term) {
        user.getAccount().setAmount(user.getAccount().getAmount().subtract(totalAmount));
        Payment payment = new Payment(new Timestamp(System.currentTimeMillis()), totalAmount);
        Timestamp expirationDate = getExpirationDate(payment.getDate(), term);
        Subscription subscription = new Subscription(user, periodical, payment, expirationDate);
        return SubscribeManager.getInstance().subscribe(subscription);
    }

    /**
     * Returns the periodicals subscribed by the user
     *
     * @param user user
     * @return ArrayList of periodicals, otherwise null
     */
    public ArrayList<Periodical> getUserPeriodicals(User user) {
        ISubscriptionDAO iSubscriptionDAO = mySqlDAOFactory.getSubscriptionDAO();
        ArrayList<Subscription> subscriptions;
        try {
            subscriptions = iSubscriptionDAO.findSubscriptionsByUser(user.getId());
        } catch (SQLException e) {
            logger.error("Failed to find subscriptions by user", e);
            return null;
        }
        ArrayList<Periodical> periodicals = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            periodicals.add(subscription.getPeriodical());
        }
        return periodicals;
    }

    /**
     * Returns subscriptions with given periodical
     *
     * @param periodical periodical
     * @return ArrayList of subscriptions, otherwise null
     */
    public ArrayList<Subscription> getSubscriptionsByPeriodical(Periodical periodical) {
        ISubscriptionDAO iSubscriptionDAO = mySqlDAOFactory.getSubscriptionDAO();
        try {
            return iSubscriptionDAO.findSubscriptionsByPeriodical(periodical.getId());
        } catch (SQLException e) {
            logger.error("Failed to find subscriptions by periodical", e);
            return null;
        }
    }

    /**
     * Returns all subscriptions
     *
     * @return ArrayList of subscriptions, if table subscription is empty then null
     */
    public ArrayList<Subscription> getAllSubscriptions() {
        ISubscriptionDAO iSubscriptionDAO = mySqlDAOFactory.getSubscriptionDAO();
        try {
            return iSubscriptionDAO.findAllSubscription();
        } catch (SQLException e) {
            logger.error("Failed to get all subscriptions ", e);
            return null;
        }
    }

    /**
     * Deletes subscription
     *
     * @param subscription subscription to delete
     * @return true if subscription is deleted, otherwise false
     */
    public boolean deleteSubscription(Subscription subscription) {
        ISubscriptionDAO iSubscriptionDAO = mySqlDAOFactory.getSubscriptionDAO();
        try {
            iSubscriptionDAO.deleteSubscription(subscription);
            return true;
        } catch (SQLException e) {
            logger.error("Failed to delete subscription ", e);
            return false;
        }
    }

    /**
     * Checks if user is subscribed to periodical
     *
     * @param user       user to check
     * @param periodical periodical to check
     * @return true if user is subscribed to periodical, otherwise false
     */
    public boolean checkSubscription(User user, Periodical periodical) {
        ArrayList<Periodical> periodicals = getUserPeriodicals(user);
        if (periodicals != null) {
            for (Periodical tempPeriodical : periodicals) {
                if (tempPeriodical.getId() == periodical.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Return expiration date of subscription
     *
     * @param subscriptionDate date of subscription
     * @param term             term of subscription
     * @return expirationDate of subscription
     */
    private Timestamp getExpirationDate(Timestamp subscriptionDate, int term) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(subscriptionDate);
        calendar.add(Calendar.MONTH, term);
        return new Timestamp(calendar.getTime().getTime());
    }


}
