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

    private static final Logger logger = LoggerLoader.getLogger(PeriodicalTypeDAO.class);

    private static PeriodicalTypeDAO periodicalTypeDAO;

    private final String SELECT_ALL_FROM_PERIODICAL_TYPE = "SELECT * FROM periodical_type ";

    private final String INSERT_TYPE = "INSERT INTO periodical_type (type) VALUES (?)";

    private final String UPDATE_TYPE = "UPDATE periodical_type SET periodical_type.type = ? WHERE periodical_type.id = ?";

    private PeriodicalTypeDAO() {
    }

    public static PeriodicalTypeDAO getInstance() {
        if (periodicalTypeDAO == null) {
            periodicalTypeDAO = new PeriodicalTypeDAO();
        }
        return periodicalTypeDAO;
    }


    @Override
    public PeriodicalType findTypeById(int id) {
        PeriodicalType type = null;

        try {
            type = findById(SELECT_ALL_FROM_PERIODICAL_TYPE + "WHERE periodical_type.id= ?", id,
                    set -> set != null ? new PeriodicalType(
                            set.getInt("id"),
                            set.getString("type")) : null);
        } catch (SQLException e) {
            logger.error("Failed to find type by id", e);
        }
        return type;
    }

    @Override
    public PeriodicalType findTypeByPeriodicalType(String type) {
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
        } catch (SQLException e) {
            logger.error("Failed to find type by periodical_type", e);
        }
        return periodicalType;
    }

    @Override
    public boolean insertType(PeriodicalType type) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_TYPE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, type.getType());
            if (statement.executeUpdate() != 0) {
                type.setId(getGeneratedKey(statement));
                return true;
            }

        } catch (SQLException e) {
            logger.error("Failed to insert type ", e);
        }
        return false;
    }

    @Override
    public boolean updateType(PeriodicalType type) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TYPE)) {
            statement.setString(1, type.getType());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Failed to update type ", e);

        }
        return false;
    }

    @Override
    public boolean deleteType(PeriodicalType type) {
        String query = "DELETE FROM periodical_type WHERE periodical_type.id = " + type.getId();
        try {
            if (execute(query) != 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Failed to delete type", e);
        }
        return false;
    }
}
