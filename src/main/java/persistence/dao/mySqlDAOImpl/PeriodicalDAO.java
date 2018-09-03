package persistence.dao.mySqlDAOImpl;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IPeriodical;
import persistence.entities.Periodical;
import persistence.entities.PeriodicalPeriod;
import persistence.entities.PeriodicalType;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Julia on 09.08.2018
 */
public class PeriodicalDAO extends AbstractDAO implements IPeriodical {

    private static PeriodicalDAO periodicalDAO;

    private final String SELECT_ALL_FROM_PERIODICAL = "SELECT periodical.id, periodical.title, periodical.periodical_type, " +
            "periodical_type.type, periodical.periodical_period, periodical_period.period, periodical_period.term, " +
            "periodical.category, periodical.price, periodical.description FROM periodical INNER JOIN periodical_type " +
            "ON(periodical.periodical_type = periodical_type.id) " +
            "INNER JOIN periodical_period ON (periodical.periodical_period = periodical_period.id) ";

    private final String SELECT_ALL_FROM_PERIODICAL_LIMIT = "SELECT periodical.id, periodical.title, periodical.periodical_type, " +
            "periodical_type.type, periodical.periodical_period, periodical_period.period, periodical_period.term, " +
            "periodical.category, periodical.price, periodical.description FROM periodical INNER JOIN periodical_type " +
            "ON(periodical.periodical_type = periodical_type.id) " +
            "INNER JOIN periodical_period ON (periodical.periodical_period = periodical_period.id) ORDER BY id LIMIT ?,?";

    private final String INSERT_PERIODICAL = "INSERT INTO periodical (title,periodical_type, periodical_period, " +
            "category, price, description) " +
            "VALUES (?,?,?,?,?,?)";

    private final String UPDATE_PERIODICAL = "UPDATE periodical SET periodical.title=?, periodical.periodical_type = ?, " +
            "periodical.periodical_period = ?, periodical.category = ?, periodical.price = ?, periodical.description =? " +
            "WHERE periodical.id = ?";

    private final String DELETE_PERIODICAL = "DELETE FROM periodical WHERE periodical.id =?";

    private final String SELECT_COUNT_ROWS = "SELECT COUNT(*) FROM periodicals.periodical";

    private PeriodicalDAO() {
    }

    public static PeriodicalDAO getInstance() {
        if (periodicalDAO == null) {
            periodicalDAO = new PeriodicalDAO();
        }
        return periodicalDAO;
    }


    @Override
    public Periodical findPeriodicalById(int id) throws SQLException {
        Periodical periodical;
        periodical = findById(SELECT_ALL_FROM_PERIODICAL + "WHERE periodical.id = ?;", id,
                set -> set != null ? new Periodical(
                        set.getInt("id"),
                        set.getString("title"),
                        new PeriodicalType(set.getInt("periodical_type"), set.getString("type")),
                        new PeriodicalPeriod(set.getInt("periodical_period"), set.getString("period"), set.getInt("term")),
                        set.getString("category"),
                        set.getBigDecimal("price"),
                        set.getString("description")) : null);
        return periodical;
    }

    @Override
    public ArrayList<Periodical> findAllPeriodicals(int start, int recordsPerPage) throws SQLException {
        ArrayList<Periodical> periodicals = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_PERIODICAL_LIMIT)) {
            statement.setInt(1, start);
            statement.setInt(2, recordsPerPage);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Periodical periodical = new Periodical(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            new PeriodicalType(resultSet.getInt("periodical_type"), resultSet.getString("type")),
                            new PeriodicalPeriod(resultSet.getInt("periodical_period"), resultSet.getString("period"), resultSet.getInt("term")), resultSet.getString("category"),
                            resultSet.getBigDecimal("price"),
                            resultSet.getString("description"));
                    periodicals.add(periodical);
                }
            }
        }
        return periodicals;
    }

    @Override
    public int selectNumberOfRows() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_COUNT_ROWS)) {
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    @Override
    public void insertPeriodical(Periodical periodical) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PERIODICAL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, periodical.getTitle());
            statement.setInt(2, periodical.getPeriodicalType().getId());
            statement.setInt(3, periodical.getPeriodicalPeriod().getId());
            statement.setString(4, periodical.getCategory());
            statement.setBigDecimal(5, periodical.getPrice());
            statement.setString(6, periodical.getDescription());
            statement.executeUpdate();
            periodical.setId(getGeneratedKey(statement));
        }
    }

    @Override
    public void updatePeriodical(Periodical periodical) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PERIODICAL)) {
            statement.setString(1, periodical.getTitle());
            statement.setInt(2, periodical.getPeriodicalType().getId());
            statement.setInt(3, periodical.getPeriodicalPeriod().getId());
            statement.setString(4, periodical.getCategory());
            statement.setBigDecimal(5, periodical.getPrice());
            statement.setString(6, periodical.getDescription());
            statement.setInt(7, periodical.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deletePeriodical(Periodical periodical) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PERIODICAL)) {
            statement.setInt(1, periodical.getId());
            statement.executeUpdate();
        }
    }

}
