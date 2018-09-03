package persistence.dao;

import persistence.entities.Periodical;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Julia on 09.08.2018
 */
public interface IPeriodical {

    /**
     * periodical's selection by id
     *
     * @param id - periodical id
     * @return - periodical or null
     */
    Periodical findPeriodicalById(int id) throws SQLException;

    /**
     * Selection all periodicals in the database
     * @param start - start record
     * @param recordsPerPage - record count
     * @return - list of periodicals
     */
    ArrayList<Periodical> findAllPeriodicals(int start,int recordsPerPage) throws SQLException;


    /**
     * Selection count of rows in the table periodical
     *
     * @return - count of rows
     */
    int selectNumberOfRows() throws SQLException;

    /**
     * Insert new periodical
     *
     * @param periodical - periodical to be inserted to the database
     */
    void insertPeriodical(Periodical periodical) throws SQLException;

    /**
     * Update periodical info
     *
     * @param periodical - periodical info to be updated in the database
     */
    void updatePeriodical(Periodical periodical) throws SQLException;

    /**
     * Delete periodical
     *
     * @param periodical - periodical to be deleted from the database
     */
    void deletePeriodical(Periodical periodical) throws SQLException;

}
