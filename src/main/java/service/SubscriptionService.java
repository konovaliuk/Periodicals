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

    public boolean subscribe(User user, Periodical periodical, BigDecimal totalAmount, int term) {
        user.getAccount().setAmount(user.getAccount().getAmount().subtract(totalAmount));
        Payment payment = new Payment(new Timestamp(System.currentTimeMillis()), totalAmount);
        Timestamp expirationDate = getExpirationDate(payment.getDate(), term);
        Subscription subscription = new Subscription(user, periodical, payment, expirationDate);
         return SubscribeManager.getInstance().subscribe(subscription);
    }

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

    public ArrayList<Subscription> getSubscriptionsByPeriodical(Periodical periodical) {
        ISubscriptionDAO iSubscriptionDAO = mySqlDAOFactory.getSubscriptionDAO();
        try {
            return iSubscriptionDAO.findSubscriptionsByPeriodical(periodical.getId());
        } catch (SQLException e) {
            logger.error("Failed to find subscriptions by periodical", e);
            return null;
        }
    }

    public ArrayList<Subscription> getAllSubscriptions() {
        ISubscriptionDAO iSubscriptionDAO = mySqlDAOFactory.getSubscriptionDAO();
        try {
            return iSubscriptionDAO.findAllSubscription();
        } catch (SQLException e) {
            logger.error("Failed to get all subscriptions ", e);
            return null;
        }
    }

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

    private Timestamp getExpirationDate(Timestamp subscriptionDate, int term) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(subscriptionDate);
        calendar.add(Calendar.MONTH, term);
        return new Timestamp(calendar.getTime().getTime());
    }


}
