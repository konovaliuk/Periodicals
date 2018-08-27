package persistence.dao;

import persistence.entities.PeriodicalPeriod;

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
    PeriodicalPeriod findPeriodById(int id);

    /**
     * Period's selection by period
     *
     * @param type - period
     * @return - period or null
     */
    PeriodicalPeriod findPeriodByPeriodicalPeriod(String type);

    /**
     * Selection all period in the database
     *
     * @return - list of period
     */
    ArrayList<PeriodicalPeriod> getAllPeriods();

    /**
     * Insert new period
     *
     * @param period - period to be inserted to the database
     * @return - {@code true} if new period id added, {@code false} if no records is inserted
     */
    boolean insertPeriod(PeriodicalPeriod period);

    /**
     * Update period info
     *
     * @param period - period info to be updated in the database
     * @return - {@code true} if period info is updated, {@code false} if no records is updated
     */
    boolean updatePeriod(PeriodicalPeriod period);

    /**
     * Delete period
     *
     * @param period - period to be deleted from the database
     * @return - {@code true} if period is deleted, {@code false} if no records is deleted
     */
    boolean deletePeriod(PeriodicalPeriod period);

}
