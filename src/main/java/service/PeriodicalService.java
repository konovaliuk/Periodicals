package service;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IPeriodicalDAO;
import persistence.dao.IPeriodicalPeriodDAO;
import persistence.dao.IPeriodicalTypeDAO;
import persistence.dao.daoFactory.DAOFactory;
import persistence.dao.daoFactory.MySqlDAOFactory;
import persistence.entities.Periodical;
import persistence.entities.PeriodicalPeriod;
import persistence.entities.PeriodicalType;
import persistence.entities.Subscription;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Julia on 15.08.2018
 */
public class PeriodicalService {

    private static PeriodicalService periodicalService;
    private final Logger logger = LoggerLoader.getLogger(PeriodicalService.class);
    private MySqlDAOFactory mySqlDAOFactory = DAOFactory.getMySqlDAOFactory();

    private PeriodicalService() {
    }

    public static PeriodicalService getInstance() {
        if (periodicalService == null) {
            periodicalService = new PeriodicalService();
        }
        return periodicalService;
    }

    public Periodical getPeriodical(int id) {
        IPeriodicalDAO iPeriodicalDAO = mySqlDAOFactory.getPeriodicalDAO();
        try {
            return iPeriodicalDAO.findPeriodicalById(id);
        } catch (SQLException e) {
            logger.error("Failed to find periodical by id", e);
            return null;
        }
    }

    public ArrayList<Periodical> getAllPeriodicals(int currentPage, int recordsPerPage) {
        IPeriodicalDAO iPeriodicalDAO = mySqlDAOFactory.getPeriodicalDAO();
        int start = currentPage * recordsPerPage - recordsPerPage;
        try {
            return iPeriodicalDAO.findAllPeriodicals(start, recordsPerPage);
        } catch (SQLException e) {
            logger.error("Failed to find all periodicals ", e);
            return null;
        }
    }

    public boolean createPeriodical(String title, String type, String period,
                                    String category, BigDecimal price, String description) {
        IPeriodicalDAO iPeriodicalDAO = mySqlDAOFactory.getPeriodicalDAO();

        PeriodicalType periodicalType = getType(type);
        PeriodicalPeriod periodicalPeriod = getPeriod(period);
        Periodical periodical = new Periodical(title, periodicalType, periodicalPeriod, category, price, description);
        try {
            iPeriodicalDAO.insertPeriodical(periodical);
            return true;
        } catch (SQLException e) {
            logger.error("Failed to insert periodical ", e);
            return false;
        }
    }

    public boolean updatePeriodical(int id, String title, String type, String period,
                                    String category, BigDecimal price, String description) {
        IPeriodicalDAO iPeriodicalDAO = mySqlDAOFactory.getPeriodicalDAO();

        PeriodicalType periodicalType = getType(type);
        PeriodicalPeriod periodicalPeriod = getPeriod(period);
        Periodical periodical = new Periodical(id, title, periodicalType, periodicalPeriod, category, price, description);
        try {
            iPeriodicalDAO.updatePeriodical(periodical);
            return true;
        } catch (SQLException e) {
            logger.error("Failed to update periodical ", e);
            return false;
        }
    }

    public boolean deletePeriodical(Periodical periodical) {
        IPeriodicalDAO iPeriodicalDAO = mySqlDAOFactory.getPeriodicalDAO();

        ArrayList<Subscription> subscriptions = SubscriptionService.getInstance().getSubscriptionsByPeriodical(periodical);

        if (subscriptions != null) {
            for (Subscription subscription : subscriptions) {
                if (!SubscriptionService.getInstance().deleteSubscription(subscription)) {
                    return false;
                }
            }
        }

        try {
            iPeriodicalDAO.deletePeriodical(periodical);
            return true;
        } catch (SQLException e) {
            logger.error("Failed to delete periodical ", e);
            return false;
        }
    }

    public int getNumberOfRows() {
        IPeriodicalDAO iPeriodicalDAO = mySqlDAOFactory.getPeriodicalDAO();
        try {
            return iPeriodicalDAO.selectNumberOfRows();
        } catch (SQLException e) {
            logger.error("Failed to select number of rows ", e);
            return 0;
        }
    }

    private PeriodicalType getType(String type) {
        IPeriodicalTypeDAO iPeriodicalTypeDAO = mySqlDAOFactory.getPeriodicalTypeDAO();
        PeriodicalType periodicalType = null;
        try {
            periodicalType = iPeriodicalTypeDAO.findPeriodicalTypeByType(type);
        } catch (SQLException e) {
            logger.error("Failed to find type by periodical_type", e);
        }
        return periodicalType;
    }

    private PeriodicalPeriod getPeriod(String period) {
        IPeriodicalPeriodDAO iPeriodicalPeriodDAO = mySqlDAOFactory.getPeriodicalPeriodDAO();
        PeriodicalPeriod periodicalPeriod = null;
        try {
            periodicalPeriod = iPeriodicalPeriodDAO.findPeriodicalPeriodByPeriod(period);
        } catch (SQLException e) {
            logger.error("Failed to find period by periodical_period", e);
        }
        return periodicalPeriod;
    }

}
