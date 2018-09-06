package persistence.dao;

import persistence.entities.Periodical;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * IPeriodicalDAO interface provides methods for manipulation of periodical table
 * Created by Julia on 09.08.2018
 */
public interface IPeriodicalDAO {

    /**
     * periodical's selection by id
     *
     * @param id - periodical id
     * @return - periodical or null
     * @throws SQLException
     */
    Periodical findPeriodicalById(int id) throws SQLException;

    /**
     * Selection all periodicals in the database
     * @param start - start record
     * @param recordsPerPage - record count
     * @return - list of periodicals
     * @throws SQLException
     */
    ArrayList<Periodical> findAllPeriodicals(int start,int recordsPerPage) throws SQLException;


    /**
     * Selection count of rows in the table periodical
     *
     * @return - count of rows
     * @throws SQLException
     */
    int selectNumberOfRows() throws SQLException;

    /**
     * Insert new periodical
     *
     * @param periodical - periodical to be inserted to the database
     * @throws SQLException
     */
    void insertPeriodical(Periodical periodical) throws SQLException;

    /**
     * Update periodical info
     *
     * @param periodical - periodical info to be updated in the database
     * @throws SQLException
     */
    void updatePeriodical(Periodical periodical) throws SQLException;

    /**
     * Delete periodical
     *
     * @param periodical - periodical to be deleted from the database
     * @throws SQLException
     */
    void deletePeriodical(Periodical periodical) throws SQLException;

}
