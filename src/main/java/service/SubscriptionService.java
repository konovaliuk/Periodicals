package service;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.ISubscription;
import persistence.dao.daoFactory.DAOFactory;
import persistence.dao.mySqlDAOImpl.SubscriptionDAO;
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
    private static final Logger logger = LoggerLoader.getLogger(SubscriptionService.class);
    private static ISubscription iSubscription = DAOFactory.getMySqlDAOFactory().getSubscriptionDAO();
    private static SubscribeManager subscribeManager = new SubscribeManager();

    public static boolean subscribe(User user, Periodical periodical, BigDecimal totalAmount, int term) {
        user.getAccount().setAmount(user.getAccount().getAmount().subtract(totalAmount));
        Payment payment = new Payment(new Timestamp(System.currentTimeMillis()), totalAmount);
        Timestamp expirationDate = getExpirationDate(payment.getDate(), term);
        Subscription subscription = new Subscription(user, periodical, payment, expirationDate);
        return subscribeManager.subscribe(subscription);
    }

    public static ArrayList<Periodical> getUserPeriodicals(User user) {
        ArrayList<Subscription> subscriptions = null;
        try {
            subscriptions = iSubscription.findSubscriptionsByUser(user.getId());
        } catch (SQLException e) {
            logger.error("Failed to find subscriptions by user", e);
        }
        ArrayList<Periodical> periodicals = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            periodicals.add(subscription.getPeriodical());
        }
        return periodicals;
    }

    private static Timestamp getExpirationDate(Timestamp subscriptionDate, int term) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(subscriptionDate);
        calendar.add(Calendar.MONTH, term);
        return new Timestamp(calendar.getTime().getTime());
    }

}
