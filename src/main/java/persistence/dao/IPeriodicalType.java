package persistence.dao;

import persistence.entities.PeriodicalType;

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
    PeriodicalType findTypeById(int id);

    /**
     * Type's selection by type
     *
     * @param type - type
     * @return - type or null
     */
    PeriodicalType findTypeByPeriodicalType(String type);

    /**
     * Insert new type
     *
     * @param type - type to be inserted to the database
     * @return - {@code true} if new type id added, {@code false} if no records is inserted
     */
    boolean insertType(PeriodicalType type);

    /**
     * Update type info
     *
     * @param type - type info to be updated in the database
     * @return - {@code true} if type info is updated, {@code false} if no records is updated
     */
    boolean updateType(PeriodicalType type);

    /**
     * Delete type
     *
     * @param type - type to be deleted from the database
     * @return - {@code true} if type is deleted, {@code false} if no records is deleted
     */
    boolean deleteType(PeriodicalType type);
}
