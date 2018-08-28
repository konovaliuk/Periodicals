package service;

import persistence.dao.ISubscription;
import persistence.dao.daoFactory.DAOFactory;
import persistence.entities.Payment;
import persistence.entities.Periodical;
import persistence.entities.Subscription;
import persistence.entities.User;
import service.transactionManager.SubscribeManager;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Julia on 15.08.2018
 */
public class SubscriptionService {
    private static ISubscription iSubscription = DAOFactory.getMySqlDAOFactory().getSubscriptionDAO();
    private static SubscribeManager subscribeManager = new SubscribeManager();

    public static boolean subscribe(User user, Periodical periodical, BigDecimal totalAmount) {
        user.getAccount().setAmount(user.getAccount().getAmount().subtract(totalAmount));
        Payment payment = new Payment(new Timestamp(System.currentTimeMillis()), totalAmount);
        Subscription subscription = new Subscription(user, periodical, payment, new Timestamp(System.currentTimeMillis()));
        return subscribeManager.subscribe(subscription);
    }

    public static ArrayList<Periodical> getUserPeriodicals(User user) {
        ArrayList<Subscription> subscriptions = iSubscription.findSubscriptionsByUser(user.getId());
        ArrayList<Periodical> periodicals = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            periodicals.add(subscription.getPeriodical());
        }
        return periodicals;
    }

}
