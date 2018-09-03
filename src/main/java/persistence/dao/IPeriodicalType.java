package persistence.dao;

import persistence.entities.PeriodicalType;

import java.sql.SQLException;

/**
 * Created by Julia on 13.08.2018
 */
public interface IPeriodicalType {
    /**
     * Type's selection by id
     *
     * @param id - type id
     * @return - type or null
     */
    PeriodicalType findTypeById(int id) throws SQLException;

    /**
     * Type's selection by type
     *
     * @param type - type
     * @return - type or null
     */
    PeriodicalType findTypeByPeriodicalType(String type) throws SQLException;

    /**
     * Insert new type
     *
     * @param type - type to be inserted to the database
     */
    void insertType(PeriodicalType type) throws SQLException;

    /**
     * Update type info
     *
     * @param type - type info to be updated in the database
     */
    void updateType(PeriodicalType type) throws SQLException;

    /**
     * Delete type
     *
     * @param type - type to be deleted from the database
     */
    void deleteType(PeriodicalType type) throws SQLException;
}
