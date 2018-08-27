package persistence.dao.mySqlDAOImpl;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IPeriodical;
import persistence.entities.Periodical;
import persistence.entities.PeriodicalPeriod;
import persistence.entities.PeriodicalType;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Julia on 09.08.2018
 */
public class PeriodicalDAO extends AbstractDAO implements IPeriodical {

    private static final Logger logger = LoggerLoader.getLogger(PeriodicalDAO.class);

    private static PeriodicalDAO periodicalDAO;

    private final String SELECT_ALL_FROM_PERIODICAL = "SELECT periodical.id, periodical.title, periodical.periodical_type, " +
            "            periodical_type.type, periodical.periodical_period, periodical_period.period, " +
            "            periodical.category, periodical.price, periodical.description FROM periodical INNER JOIN periodical_type " +
            "            ON(periodical.periodical_type = periodical_type.id) " +
            "            INNER JOIN periodical_period ON (periodical.periodical_period = periodical_period.id) ";

    private final String INSERT_PERIODICAL = "INSERT INTO periodical (title,periodical_type, periodical_period, " +
            "category, price, description) " +
            "VALUES (?,?,?,?,?,?)";

    private final String UPDATE_PERIODICAL = "UPDATE periodical SET periodical.title=?, periodical.periodical_type = ?, " +
            "periodical.periodical_period = ?, periodical.category = ?, periodical.price = ?, periodical.description =? " +
            "WHERE periodical.id = ?";

    private PeriodicalDAO() {
    }

    public static PeriodicalDAO getInstance() {
        if (periodicalDAO == null) {
            periodicalDAO = new PeriodicalDAO();
        }
        return periodicalDAO;
    }


    @Override
    public Periodical findPeriodicalById(int id) {
        Periodical periodical = null;
        try {
            periodical = findById(SELECT_ALL_FROM_PERIODICAL + "WHERE periodical.id = ?;", id,
                    set -> set != null ? new Periodical(
                            set.getInt("id"),
                            set.getString("title"),
                            new PeriodicalType(set.getInt("periodical_type"), set.getString("type")),
                            new PeriodicalPeriod(set.getInt("periodical_period"), set.getString("period")),
                            set.getString("category"),
                            set.getBigDecimal("price"),
                            set.getString("description")) : null);
        } catch (SQLException e) {
            logger.error("Failed to find periodical by id", e);
        }
        return periodical;
    }

    @Override
    public ArrayList<Periodical> findAllPeriodicals() {
        ArrayList<Periodical> periodicals = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_PERIODICAL)) {
                while (resultSet.next()) {
                    Periodical periodical = new Periodical(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            new PeriodicalType(resultSet.getInt("periodical_type"), resultSet.getString("type")),
                            new PeriodicalPeriod(resultSet.getInt("periodical_period"), resultSet.getString("period")),
                            resultSet.getString("category"),
                            resultSet.getBigDecimal("price"),
                            resultSet.getString("description"));
                    periodicals.add(periodical);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to find all periodicals ", e);
        }
        return periodicals;
    }

    @Override
    public boolean insertPeriodical(Periodical periodical) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PERIODICAL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, periodical.getTitle());
            statement.setInt(2, periodical.getPeriodicalType().getId());
            statement.setInt(3, periodical.getPeriodicalPeriod().getId());
            statement.setString(4, periodical.getCategory());
            statement.setBigDecimal(5, periodical.getPrice());
            statement.setString(6, periodical.getDescription());

            if (statement.executeUpdate() != 0) {
                periodical.setId(getGeneratedKey(statement));
                return true;
            }

        } catch (SQLException e) {
            logger.error("Failed to insert periodical ", e);

        }
        return false;
    }

    @Override
    public boolean updatePeriodical(Periodical periodical) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PERIODICAL)) {
            statement.setString(1, periodical.getTitle());
            statement.setInt(2, periodical.getPeriodicalType().getId());
            statement.setInt(3, periodical.getPeriodicalPeriod().getId());
            statement.setString(4, periodical.getCategory());
            statement.setBigDecimal(5, periodical.getPrice());
            statement.setString(6, periodical.getDescription());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Failed to update periodical ", e);
        }
        return false;
    }

    @Override
    public boolean deletePeriodical(Periodical periodical) {
        String query = "DELETE FROM periodical WHERE periodical.id = " + periodical.getId();
        try {
            if (execute(query) != 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Failed to delete periodical", e);
        }
        return false;
    }
}
