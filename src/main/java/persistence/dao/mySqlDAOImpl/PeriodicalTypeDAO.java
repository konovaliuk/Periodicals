package persistence.dao.mySqlDAOImpl;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IPeriodicalType;
import persistence.entities.PeriodicalType;
import persistence.entities.User;
import persistence.entities.UserRole;

import java.sql.*;

/**
 * Created by Julia on 13.08.2018
 */
public class PeriodicalTypeDAO extends AbstractDAO implements IPeriodicalType {

    private static PeriodicalTypeDAO periodicalTypeDAO;

    private final String SELECT_ALL_FROM_PERIODICAL_TYPE = "SELECT * FROM periodical_type ";

    private final String INSERT_TYPE = "INSERT INTO periodical_type (type) VALUES (?)";

    private final String UPDATE_TYPE = "UPDATE periodical_type SET periodical_type.type = ? WHERE periodical_type.id = ?";

    private final String DELETE_PERIODICAL_TYPE = "DELETE FROM periodical_type WHERE periodical_type.id = ?";

    private PeriodicalTypeDAO() {
    }

    public static PeriodicalTypeDAO getInstance() {
        if (periodicalTypeDAO == null) {
            periodicalTypeDAO = new PeriodicalTypeDAO();
        }
        return periodicalTypeDAO;
    }


    @Override
    public PeriodicalType findTypeById(int id) throws SQLException {
        PeriodicalType type;
        type = findById(SELECT_ALL_FROM_PERIODICAL_TYPE + "WHERE periodical_type.id= ?", id,
                set -> set != null ? new PeriodicalType(
                        set.getInt("id"),
                        set.getString("type")) : null);
        return type;
    }

    @Override
    public PeriodicalType findTypeByPeriodicalType(String type) throws SQLException {
        PeriodicalType periodicalType = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_PERIODICAL_TYPE + "WHERE periodical_type.type= ?")) {
            statement.setString(1, type);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    periodicalType = new PeriodicalType(resultSet.getInt("id"),
                            resultSet.getString("type"));
                }
            }
        }
        return periodicalType;
    }

    @Override
    public void insertType(PeriodicalType type) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_TYPE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, type.getType());
            statement.executeUpdate();
            type.setId(getGeneratedKey(statement));
        }
    }

    @Override
    public void updateType(PeriodicalType type) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TYPE)) {
            statement.setString(1, type.getType());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteType(PeriodicalType type) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PERIODICAL_TYPE)) {
            statement.setInt(1, type.getId());
            statement.executeUpdate();
        }
    }
}
