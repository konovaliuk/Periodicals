package persistence.dao;

import persistence.entities.PeriodicalPeriod;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * IPeriodicalPeriodDAO interface provides methods for manipulation of periodical_period table
 * Created by Julia on 27.08.2018
 */
public interface IPeriodicalPeriodDAO {
    /**
     * Period's selection by id
     *
     * @param id - period id
     * @return - period or null
     * @throws SQLException
     */
    PeriodicalPeriod findPeriodById(int id) throws SQLException;

    /**
     * Period's selection by period
     *
     * @param type period
     * @return period or null
     * @throws SQLException
     */
    PeriodicalPeriod findPeriodicalPeriodByPeriod(String type) throws SQLException;

    /**
     * Period's selection by term
     *
     * @param term term
     * @return period or null
     * @throws SQLException
     */
    public PeriodicalPeriod findPeriodicalPeriodByTerm(int term) throws SQLException;

    /**
     * Selection all period in the database
     *
     * @return - list of period
     * @throws SQLException
     */
    ArrayList<PeriodicalPeriod> findAllPeriods() throws SQLException;

    /**
     * Insert new period
     *
     * @param period - period to be inserted to the database
     * @throws SQLException
     */
    void insertPeriod(PeriodicalPeriod period) throws SQLException;

    /**
     * Update period info
     *
     * @param period - period info to be updated in the database
     * @throws SQLException
     */
    void updatePeriod(PeriodicalPeriod period) throws SQLException;

    /**
     * Delete period
     *
     * @param period - period to be deleted from the database
     * @throws SQLException
     */
    void deletePeriod(PeriodicalPeriod period) throws SQLException;

}
