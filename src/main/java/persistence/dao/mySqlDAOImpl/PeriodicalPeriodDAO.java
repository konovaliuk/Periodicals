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

    private static PeriodicalPeriodDAO periodicalPeriodDAO;

    private PeriodicalPeriodDAO() {
    }

    private final String SELECT_ALL_FROM_PERIODICAL_PERIOD = "SELECT * FROM periodical_period ";

    private final String INSERT_PERIOD = "INSERT INTO periodical_period (period,term) VALUES (?,?)";

    private final String UPDATE_PERIOD = "UPDATE periodical_period SET periodical_period.period = ?, periodical_period.term = ?" +
            "WHERE periodical_period.id = ?";

    private final String DELETE_PERIODICAL_PERIOD = "DELETE FROM periodical_period WHERE periodical_period.id = ?";

    public static PeriodicalPeriodDAO getInstance() {
        if (periodicalPeriodDAO == null) {
            periodicalPeriodDAO = new PeriodicalPeriodDAO();
        }
        return periodicalPeriodDAO;
    }


    @Override
    public PeriodicalPeriod findPeriodById(int id) throws SQLException {
        PeriodicalPeriod period;

        period = findById(SELECT_ALL_FROM_PERIODICAL_PERIOD + "WHERE periodical_period.id= ?", id,
                set -> set != null ? new PeriodicalPeriod(
                        set.getInt("id"),
                        set.getString("period"),
                        set.getInt("term")) : null);
        return period;
    }

    @Override
    public PeriodicalPeriod findPeriodByPeriodicalPeriod(String type) throws SQLException {
        PeriodicalPeriod periodicalPeriod = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_PERIODICAL_PERIOD + "WHERE periodical_period.period= ?")) {
            statement.setString(1, type);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    periodicalPeriod = new PeriodicalPeriod(resultSet.getInt("id"),
                            resultSet.getString("period"),
                            resultSet.getInt("term"));
                }
            }
        }
        return periodicalPeriod;
    }

    @Override
    public ArrayList<PeriodicalPeriod> getAllPeriods() throws SQLException {
        ArrayList<PeriodicalPeriod> periods = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_PERIODICAL_PERIOD)) {
                while (resultSet.next()) {
                    PeriodicalPeriod period = new PeriodicalPeriod(resultSet.getInt("id"),
                            resultSet.getString("period"),
                            resultSet.getInt("term"));
                    periods.add(period);
                }
            }
        }
        return periods;
    }

    @Override
    public void insertPeriod(PeriodicalPeriod period) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PERIOD, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, period.getPeriod());
            statement.setInt(2, period.getTerm());
            statement.executeUpdate();
            period.setId(getGeneratedKey(statement));
        }
    }

    @Override
    public void updatePeriod(PeriodicalPeriod period) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PERIOD)) {
            statement.setString(1, period.getPeriod());
            statement.setInt(2, period.getTerm());
            statement.executeUpdate();
        }
    }

    @Override
    public void deletePeriod(PeriodicalPeriod period) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PERIODICAL_PERIOD)) {
            statement.setInt(1, period.getId());
            statement.executeUpdate();
        }
    }
}
