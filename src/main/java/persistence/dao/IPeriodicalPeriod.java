package persistence.dao;

import persistence.entities.PeriodicalPeriod;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Julia on 27.08.2018
 */
public interface IPeriodicalPeriod {
    /**
     * Period's selection by id
     *
     * @param id - period id
     * @return - period or null
     */
    PeriodicalPeriod findPeriodById(int id) throws SQLException;

    /**
     * Period's selection by period
     *
     * @param type - period
     * @return - period or null
     */
    PeriodicalPeriod findPeriodByPeriodicalPeriod(String type) throws SQLException;

    /**
     * Selection all period in the database
     *
     * @return - list of period
     */
    ArrayList<PeriodicalPeriod> getAllPeriods() throws SQLException;

    /**
     * Insert new period
     *
     * @param period - period to be inserted to the database
     */
    void insertPeriod(PeriodicalPeriod period) throws SQLException;

    /**
     * Update period info
     *
     * @param period - period info to be updated in the database
     */
    void updatePeriod(PeriodicalPeriod period) throws SQLException;

    /**
     * Delete period
     *
     * @param period - period to be deleted from the database
     */
    void deletePeriod(PeriodicalPeriod period) throws SQLException;

}
