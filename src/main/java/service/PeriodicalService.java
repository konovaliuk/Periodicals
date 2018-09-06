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
 * PeriodicalService is responsible for periodical DTO
 * Designed as singleton
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


    /**
     * Finds periodical by id
     *
     * @param id periodical id
     * @return periodical, if such periodical doesn't exist then null
     */
    public Periodical getPeriodical(int id) {
        IPeriodicalDAO iPeriodicalDAO = mySqlDAOFactory.getPeriodicalDAO();
        try {
            return iPeriodicalDAO.findPeriodicalById(id);
        } catch (SQLException e) {
            logger.error("Failed to find periodical by id", e);
            return null;
        }
    }

    /**
     * Finds periodicals with a specified limit
     *
     * @param currentPage    number of current page
     * @param recordsPerPage count of elements on one page
     * @return ArrayList of periodicals, if table periodical is empty then null
     */
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

    /**
     * Creates periodical
     *
     * @param title       periodical title
     * @param type        periodical type
     * @param term        periodical term
     * @param category    periodical category
     * @param price       periodical price
     * @param description periodical description
     * @return true if new periodical is added, otherwise false
     */
    public boolean createPeriodical(String title, String type, int term,
                                    String category, BigDecimal price, String description) {
        IPeriodicalDAO iPeriodicalDAO = mySqlDAOFactory.getPeriodicalDAO();

        PeriodicalType periodicalType;
        try {
            periodicalType = getType(type);
        } catch (SQLException e) {
            logger.error("Failed to find type by periodical_type", e);
            return false;
        }
        PeriodicalPeriod periodicalPeriod;
        try {
            periodicalPeriod = getPeriod(term);
        } catch (SQLException e) {
            logger.error("Failed to find period by periodical_period ", e);
            return false;
        }
        Periodical periodical = new Periodical(title, periodicalType, periodicalPeriod, category, price, description);
        try {
            iPeriodicalDAO.insertPeriodical(periodical);
            return true;
        } catch (SQLException e) {
            logger.error("Failed to insert periodical ", e);
            return false;
        }
    }

    /**
     * Updates periodical
     *
     * @param title       periodical title
     * @param type        periodical type
     * @param term        periodical term
     * @param category    periodical category
     * @param price       periodical price
     * @param description periodical description
     * @return true if periodical is updated, otherwise false
     */
    public boolean updatePeriodical(int id, String title, String type, int term,
                                    String category, BigDecimal price, String description) {
        IPeriodicalDAO iPeriodicalDAO = mySqlDAOFactory.getPeriodicalDAO();

        PeriodicalType periodicalType;
        try {
            periodicalType = getType(type);
        } catch (SQLException e) {
            logger.error("Failed to find type by periodical_type", e);
            return false;
        }
        PeriodicalPeriod periodicalPeriod;
        try {
            periodicalPeriod = getPeriod(term);
        } catch (SQLException e) {
            logger.error("Failed to find period by periodical_period ", e);
            return false;
        }
        Periodical periodical = new Periodical(id, title, periodicalType, periodicalPeriod, category, price, description);
        try {
            iPeriodicalDAO.updatePeriodical(periodical);
            return true;
        } catch (SQLException e) {
            logger.error("Failed to update periodical ", e);
            return false;
        }
    }

    /**
     * Deletes periodical
     *
     * @param periodical periodical to delete
     * @return true if periodical is deleted, otherwise false
     */
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

    /**
     * Return number of rows of periodical table
     *
     * @return number of rows if there are rows in the table periodic, otherwise 0
     */
    public int getNumberOfRows() {
        IPeriodicalDAO iPeriodicalDAO = mySqlDAOFactory.getPeriodicalDAO();
        try {
            return iPeriodicalDAO.selectNumberOfRows();
        } catch (SQLException e) {
            logger.error("Failed to select number of rows ", e);
            return 0;
        }
    }

    /**
     * Returns periodical type
     *
     * @param type - periodical type
     * @return periodicalType if such periodicalType exists, otherwise null
     */
    private PeriodicalType getType(String type) throws SQLException {
        IPeriodicalTypeDAO iPeriodicalTypeDAO = mySqlDAOFactory.getPeriodicalTypeDAO();
        return iPeriodicalTypeDAO.findPeriodicalTypeByType(type);
    }

    /**
     * Returns periodical period
     *
     * @param term - periodical term
     * @return periodicalPeriod if such periodicalPeriod exists, otherwise null
     */
    private PeriodicalPeriod getPeriod(int term) throws SQLException {
        IPeriodicalPeriodDAO iPeriodicalPeriodDAO = mySqlDAOFactory.getPeriodicalPeriodDAO();
    /*    PeriodicalPeriod periodicalPeriod = null;
        periodicalPeriod = iPeriodicalPeriodDAO.findPeriodicalPeriodByTerm(term);*/
        return iPeriodicalPeriodDAO.findPeriodicalPeriodByTerm(term);
    }

}
