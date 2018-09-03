package service;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IPeriodical;
import persistence.dao.IPeriodicalPeriod;
import persistence.dao.IPeriodicalType;
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

    private static final Logger logger = LoggerLoader.getLogger(PeriodicalService.class);
    private static MySqlDAOFactory mySqlDAOFactory = DAOFactory.getMySqlDAOFactory();

    public static ArrayList<Periodical> getAllPeriodicals(int currentPage, int recordsPerPage) {
        IPeriodical iPeriodical = mySqlDAOFactory.getPeriodicalDAO();
        int start = currentPage * recordsPerPage - recordsPerPage;
        try {
            return iPeriodical.findAllPeriodicals(start, recordsPerPage);
        } catch (SQLException e) {
            logger.error("Failed to find all periodicals ", e);
            return null;
        }
    }

    public static Periodical getPeriodical(int id) {
        IPeriodical iPeriodical = mySqlDAOFactory.getPeriodicalDAO();
        try {
            return iPeriodical.findPeriodicalById(id);
        } catch (SQLException e) {
            logger.error("Failed to find periodical by id", e);
            return null;
        }
    }

    public static boolean createPeriodical(String title, String type, String period,
                                           String category, BigDecimal price, String description) {
        IPeriodical iPeriodical = mySqlDAOFactory.getPeriodicalDAO();

        PeriodicalType periodicalType = getType(type);
        PeriodicalPeriod periodicalPeriod = getPeriod(period);
        Periodical periodical = new Periodical(title, periodicalType, periodicalPeriod, category, price, description);
        try {
            iPeriodical.insertPeriodical(periodical);
            return true;
        } catch (SQLException e) {
            logger.error("Failed to insert periodical ", e);
            return false;
        }
    }

    public static boolean updatePeriodical(int id, String title, String type, String period,
                                           String category, BigDecimal price, String description) {
        IPeriodical iPeriodical = mySqlDAOFactory.getPeriodicalDAO();

        PeriodicalType periodicalType = getType(type);
        PeriodicalPeriod periodicalPeriod = getPeriod(period);
        Periodical periodical = new Periodical(id, title, periodicalType, periodicalPeriod, category, price, description);
        try {
            iPeriodical.updatePeriodical(periodical);
            return true;
        } catch (SQLException e) {
            logger.error("Failed to update periodical ", e);
            return false;
        }
    }

    public static boolean deletePeriodical(Periodical periodical) {
        IPeriodical iPeriodical = mySqlDAOFactory.getPeriodicalDAO();

        ArrayList<Subscription> subscriptions = SubscriptionService.getSubscriptionsByPeriodical(periodical);

        if (subscriptions != null) {
            for (Subscription subscription : subscriptions) {
                if (!SubscriptionService.deleteSubscription(subscription)) {
                    return false;
                }
            }
        }

        try {
            iPeriodical.deletePeriodical(periodical);
            return true;
        } catch (SQLException e) {
            logger.error("Failed to delete periodical ", e);
            return false;
        }
    }

    public static int getNumberOfRows() {
        IPeriodical iPeriodical = mySqlDAOFactory.getPeriodicalDAO();
        try {
            return iPeriodical.selectNumberOfRows();
        } catch (SQLException e) {
            logger.error("Failed to select number of rows ", e);
            return 0;
        }
    }

    private static PeriodicalType getType(String type) {
        IPeriodicalType iPeriodicalType = mySqlDAOFactory.getPeriodicalTypeDAO();
        PeriodicalType periodicalType = null;
        try {
            periodicalType = iPeriodicalType.findTypeByPeriodicalType(type);
        } catch (SQLException e) {
            logger.error("Failed to find type by periodical_type", e);
        }
        return periodicalType;
    }

    private static PeriodicalPeriod getPeriod(String period) {
        IPeriodicalPeriod iPeriodicalPeriod = mySqlDAOFactory.getPeriodicalPeriodDAO();
        PeriodicalPeriod periodicalPeriod = null;
        try {
            periodicalPeriod = iPeriodicalPeriod.findPeriodByPeriodicalPeriod(period);
        } catch (SQLException e) {
            logger.error("Failed to find period by periodical_period", e);
        }
        return periodicalPeriod;
    }

}
