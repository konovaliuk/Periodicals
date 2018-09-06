package service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import persistence.dao.IAccountDAO;
import persistence.dao.IPaymentDAO;
import persistence.dao.ISubscriptionDAO;
import persistence.dao.daoFactory.DAOFactory;
import persistence.dao.daoFactory.MySqlDAOFactory;
import persistence.entities.*;
import service.transactionManager.SubscribeManager;
import service.transactionManager.TransactionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Julia on 9/6/2018
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DAOFactory.class})
public class SubscriptionServiceTest {

    @InjectMocks
    SubscriptionService subscriptionService;
    @Mock
    private MySqlDAOFactory mySqlDAOFactory;
    @Mock
    ISubscriptionDAO iSubscriptionDAO;
    @Mock
    IAccountDAO iAccountDAO;
    @Mock
    IPaymentDAO iPaymentDAO;
    @Mock
    SubscribeManager subscribeManager;
    @Mock
    TransactionManager transactionManager;
    @Mock
    Connection connection;

    private User user = new User();
    private Periodical periodical = new Periodical();
    private Subscription subscription = new Subscription();
    private ArrayList<Subscription> subscriptions = new ArrayList<>();

    @Before
    public void setUp() {
        user.setId(1);
        periodical.setId(1);
        PowerMockito.mockStatic(DAOFactory.class);
        when(DAOFactory.getMySqlDAOFactory()).thenReturn(mySqlDAOFactory);
    }

    @Test
    public void getUserPeriodicals() throws SQLException {
        when(mySqlDAOFactory.getSubscriptionDAO()).thenReturn(iSubscriptionDAO);
        when(iSubscriptionDAO.findSubscriptionsByUser(user.getId())).thenReturn(subscriptions);
        assertNotNull(subscriptionService.getUserPeriodicals(user));
    }

    @Test
    public void getUserPeriodicalsFailed() throws SQLException {
        when(mySqlDAOFactory.getSubscriptionDAO()).thenReturn(iSubscriptionDAO);
        when(iSubscriptionDAO.findSubscriptionsByUser(user.getId())).thenThrow(new SQLException());
        assertNull(subscriptionService.getUserPeriodicals(user));
    }

    @Test
    public void getSubscriptionsByPeriodical() throws SQLException {
        when(mySqlDAOFactory.getSubscriptionDAO()).thenReturn(iSubscriptionDAO);
        when(iSubscriptionDAO.findSubscriptionsByPeriodical(periodical.getId())).thenReturn(subscriptions);
        assertNotNull(subscriptionService.getSubscriptionsByPeriodical(periodical));
    }

    @Test
    public void getSubscriptionsByPeriodicalFailed() throws SQLException {
        when(mySqlDAOFactory.getSubscriptionDAO()).thenReturn(iSubscriptionDAO);
        when(iSubscriptionDAO.findSubscriptionsByPeriodical(periodical.getId())).thenThrow(new SQLException());
        assertNull(subscriptionService.getSubscriptionsByPeriodical(periodical));
    }

    @Test
    public void getAllSubscriptions() throws SQLException {
        when(mySqlDAOFactory.getSubscriptionDAO()).thenReturn(iSubscriptionDAO);
        when(iSubscriptionDAO.findAllSubscription()).thenReturn(subscriptions);
        assertNotNull(subscriptionService.getAllSubscriptions());
    }

    @Test
    public void getAllSubscriptionsFailed() throws SQLException {
        when(mySqlDAOFactory.getSubscriptionDAO()).thenReturn(iSubscriptionDAO);
        when(iSubscriptionDAO.findAllSubscription()).thenThrow(new SQLException());
        assertNull(subscriptionService.getAllSubscriptions());
    }

    @Test
    public void deleteSubscription() throws SQLException {
        when(mySqlDAOFactory.getSubscriptionDAO()).thenReturn(iSubscriptionDAO);
        doNothing().when(iSubscriptionDAO).deleteSubscription(subscription);
        assertTrue(subscriptionService.deleteSubscription(subscription));
    }

    @Test
    public void deleteSubscriptionFailed() throws SQLException {
        when(mySqlDAOFactory.getSubscriptionDAO()).thenReturn(iSubscriptionDAO);
        doThrow(new SQLException()).when(iSubscriptionDAO).deleteSubscription(subscription);
        assertFalse(subscriptionService.deleteSubscription(subscription));
    }

    @Test
    public void checkSubscription() throws SQLException {
        subscription.setUser(user);
        subscription.setPeriodical(periodical);
        subscriptions.add(subscription);
        when(mySqlDAOFactory.getSubscriptionDAO()).thenReturn(iSubscriptionDAO);
        when(iSubscriptionDAO.findSubscriptionsByUser(user.getId())).thenReturn(subscriptions);
        assertTrue(subscriptionService.checkSubscription(user, periodical));
    }

    @Test
    public void checkSubscriptionFailed() throws SQLException {
        when(mySqlDAOFactory.getSubscriptionDAO()).thenReturn(iSubscriptionDAO);
        when(iSubscriptionDAO.findSubscriptionsByUser(user.getId())).thenReturn(subscriptions);
        assertFalse(subscriptionService.checkSubscription(user, periodical));
    }
}