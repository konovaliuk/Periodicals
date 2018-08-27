package persistence.dao.mySqlDAOImpl;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IPeriodicalPeriod;
import persistence.entities.PeriodicalPeriod;
import persistence.entities.PeriodicalType;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Julia on 27.08.2018
 */
public class PeriodicalPeriodDAO extends AbstractDAO implements IPeriodicalPeriod {

    private static final Logger logger = LoggerLoader.getLogger(PeriodicalPeriodDAO.class);

    private static PeriodicalPeriodDAO periodicalPeriodDAO;

    private PeriodicalPeriodDAO() {
    }

    private final String SELECT_ALL_FROM_PERIODICAL_PERIOD = "SELECT * FROM periodical_period ";

    private final String INSERT_PERIOD = "INSERT INTO periodical_period (period) VALUES (?)";

    private final String UPDATE_PERIOD = "UPDATE periodical_period SET periodical_period.period = ? " +
            "WHERE periodical_period.id = ?";

    public static PeriodicalPeriodDAO getInstance() {
        if (periodicalPeriodDAO == null) {
            periodicalPeriodDAO = new PeriodicalPeriodDAO();
        }
        return periodicalPeriodDAO;
    }


    @Override
    public PeriodicalPeriod findPeriodById(int id) {
        PeriodicalPeriod period = null;

        try {
            period = findById(SELECT_ALL_FROM_PERIODICAL_PERIOD + "WHERE periodical_period.id= ?", id,
                    set -> set != null ? new PeriodicalPeriod(
                            set.getInt("id"),
                            set.getString("period")) : null);
        } catch (SQLException e) {
            logger.error("Failed to find period by id", e);
        }

        return period;
    }

    @Override
    public PeriodicalPeriod findPeriodByPeriodicalPeriod(String type) {
        PeriodicalPeriod periodicalPeriod = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_PERIODICAL_PERIOD + "WHERE periodical_period.period= ?")) {
            statement.setString(1, type);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    periodicalPeriod = new PeriodicalPeriod(resultSet.getInt("id"),
                            resultSet.getString("period"));
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to find period by periodical_period", e);
        }
        return periodicalPeriod;
    }

    @Override
    public ArrayList<PeriodicalPeriod> getAllPeriods() {
        ArrayList<PeriodicalPeriod> periods = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_PERIODICAL_PERIOD)) {
                while (resultSet.next()) {
                    PeriodicalPeriod period = new PeriodicalPeriod(resultSet.getInt("id"),
                            resultSet.getString("period"));
                    periods.add(period);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to get all period", e);
        }
        return periods;
    }

    @Override
    public boolean insertPeriod(PeriodicalPeriod period) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PERIOD, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, period.getPeriod());
            if (statement.executeUpdate() != 0) {
                period.setId(getGeneratedKey(statement));
                return true;
            }

        } catch (SQLException e) {
            logger.error("Failed to insert period", e);
        }
        return false;
    }

    @Override
    public boolean updatePeriod(PeriodicalPeriod period) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PERIOD)) {
            statement.setString(1, period.getPeriod());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Failed to update period", e);
        }
        return false;
    }

    @Override
    public boolean deletePeriod(PeriodicalPeriod period) {
        String query = "DELETE FROM periodical_period WHERE periodical_period.id = " + period.getId();
        try {
            if (execute(query) != 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Failed to delete period", e);
        }
        return false;
    }
}
