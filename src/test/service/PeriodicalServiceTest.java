package service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import persistence.dao.IPeriodicalDAO;
import persistence.dao.IPeriodicalPeriodDAO;
import persistence.dao.IPeriodicalTypeDAO;
import persistence.dao.ISubscriptionDAO;
import persistence.dao.daoFactory.DAOFactory;
import persistence.dao.daoFactory.MySqlDAOFactory;
import persistence.entities.Periodical;
import persistence.entities.PeriodicalPeriod;
import persistence.entities.PeriodicalType;
import persistence.entities.Subscription;

import java.math.BigDecimal;
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
public class PeriodicalServiceTest {

    @InjectMocks
    private PeriodicalService periodicalService;
    @Mock
    private MySqlDAOFactory mySqlDAOFactory;
    @Mock
    private IPeriodicalDAO iPeriodicalDAO;
    @Mock
    private IPeriodicalTypeDAO iPeriodicalTypeDAO;
    @Mock
    private IPeriodicalPeriodDAO iPeriodicalPeriodDAO;
    @Mock
    private SubscriptionService subscriptionService;
    @Mock
    private ISubscriptionDAO iSubscriptionDAO;

    private ArrayList<Periodical> periodicals = new ArrayList<>();
    private ArrayList<Subscription> subscriptions = new ArrayList<>();
    private Periodical periodical = new Periodical();

    private String type = "type";
    private int term = 0;


    @Before
    public void setUp() {
        periodical.setId(0);
        periodical.setTitle("title");
        periodical.setPeriodicalType(null);
        periodical.setPeriodicalPeriod(null);
        periodical.setCategory("category");
        periodical.setPrice(new BigDecimal(10));
        periodical.setDescription("description");

        PowerMockito.mockStatic(DAOFactory.class);
        when(DAOFactory.getMySqlDAOFactory()).thenReturn(mySqlDAOFactory);
    }

    @Test
    public void getPeriodical() throws SQLException {
        when(mySqlDAOFactory.getPeriodicalDAO()).thenReturn(iPeriodicalDAO);
        when(iPeriodicalDAO.findPeriodicalById(periodical.getId())).thenReturn(periodical);
        assertNotNull(periodicalService.getPeriodical(periodical.getId()));
    }

    @Test
    public void getPeriodicalFailed() throws SQLException {
        when(mySqlDAOFactory.getPeriodicalDAO()).thenReturn(iPeriodicalDAO);
        when(iPeriodicalDAO.findPeriodicalById(periodical.getId())).thenThrow(new SQLException());
        assertNull(periodicalService.getPeriodical(periodical.getId()));
    }

    @Test
    public void getAllPeriodicals() throws SQLException {
        when(mySqlDAOFactory.getPeriodicalDAO()).thenReturn(iPeriodicalDAO);
        when(iPeriodicalDAO.findAllPeriodicals(2, 4)).thenReturn(periodicals);
        assertNotNull(periodicalService.getAllPeriodicals(2, 4));
    }

    @Test
    public void createPeriodical() throws SQLException {
        when(mySqlDAOFactory.getPeriodicalDAO()).thenReturn(iPeriodicalDAO);
        when(mySqlDAOFactory.getPeriodicalTypeDAO()).thenReturn(iPeriodicalTypeDAO);
        when(mySqlDAOFactory.getPeriodicalPeriodDAO()).thenReturn(iPeriodicalPeriodDAO);
        doNothing().when(iPeriodicalDAO).insertPeriodical(periodical);
        assertTrue(periodicalService.createPeriodical(periodical.getTitle(), type, term, periodical.getCategory(), periodical.getPrice(), periodical.getDescription()));
    }

    @Test
    public void createPeriodicalFailed() throws SQLException {
        when(mySqlDAOFactory.getPeriodicalDAO()).thenReturn(iPeriodicalDAO);
        when(mySqlDAOFactory.getPeriodicalTypeDAO()).thenReturn(iPeriodicalTypeDAO);
        when(mySqlDAOFactory.getPeriodicalPeriodDAO()).thenReturn(iPeriodicalPeriodDAO);
        doThrow(new SQLException()).when(iPeriodicalDAO).insertPeriodical(periodical);
        assertFalse(periodicalService.createPeriodical(periodical.getTitle(), null, term, periodical.getCategory(), periodical.getPrice(), periodical.getDescription()));
    }

    @Test
    public void updatePeriodical() throws SQLException {
        when(mySqlDAOFactory.getPeriodicalDAO()).thenReturn(iPeriodicalDAO);
        when(mySqlDAOFactory.getPeriodicalTypeDAO()).thenReturn(iPeriodicalTypeDAO);
        when(mySqlDAOFactory.getPeriodicalPeriodDAO()).thenReturn(iPeriodicalPeriodDAO);
        doNothing().when(iPeriodicalDAO).updatePeriodical(periodical);
        assertTrue(periodicalService.updatePeriodical(periodical.getId(), periodical.getTitle(), type, term, periodical.getCategory(), periodical.getPrice(), periodical.getDescription()));
    }

    @Test
    public void updatePeriodicalFailed() throws SQLException {
        when(mySqlDAOFactory.getPeriodicalDAO()).thenReturn(iPeriodicalDAO);
        when(mySqlDAOFactory.getPeriodicalTypeDAO()).thenReturn(iPeriodicalTypeDAO);
        when(mySqlDAOFactory.getPeriodicalPeriodDAO()).thenReturn(iPeriodicalPeriodDAO);
        doThrow(new SQLException()).when(iPeriodicalDAO).updatePeriodical(periodical);
        assertFalse(periodicalService.updatePeriodical(periodical.getId(), periodical.getTitle(), type, term, periodical.getCategory(), periodical.getPrice(), periodical.getDescription()));
    }

    @Test
    public void deletePeriodical() throws SQLException {
        when(mySqlDAOFactory.getPeriodicalDAO()).thenReturn(iPeriodicalDAO);
        when(mySqlDAOFactory.getSubscriptionDAO()).thenReturn(iSubscriptionDAO);
        when(subscriptionService.getSubscriptionsByPeriodical(periodical)).thenReturn(subscriptions);
        doNothing().when(iPeriodicalDAO).deletePeriodical(periodical);
        assertTrue(periodicalService.deletePeriodical(periodical));
    }

    @Test
    public void deletePeriodicalFailed() throws SQLException {
        when(mySqlDAOFactory.getPeriodicalDAO()).thenReturn(iPeriodicalDAO);
        when(mySqlDAOFactory.getSubscriptionDAO()).thenReturn(iSubscriptionDAO);
        when(subscriptionService.getSubscriptionsByPeriodical(periodical)).thenReturn(subscriptions);
        doThrow(new SQLException()).when(iPeriodicalDAO).deletePeriodical(periodical);
        assertFalse(periodicalService.deletePeriodical(periodical));
    }

    @Test
    public void getNumberOfRows() throws SQLException {
        when(mySqlDAOFactory.getPeriodicalDAO()).thenReturn(iPeriodicalDAO);
        when(iPeriodicalDAO.selectNumberOfRows()).thenReturn(10);
        assertEquals(periodicalService.getNumberOfRows(), 10);
    }

    @Test
    public void getNumberOfRowsFailed() throws SQLException {
        when(mySqlDAOFactory.getPeriodicalDAO()).thenReturn(iPeriodicalDAO);
        when(iPeriodicalDAO.selectNumberOfRows()).thenThrow(new SQLException());
        assertEquals(periodicalService.getNumberOfRows(), 0);
    }
}