package persistence.dao.mySqlDAOImpl;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.ISubscriptionDAO;
import persistence.dao.daoFactory.DAOFactory;
import persistence.dao.daoFactory.MySqlDAOFactory;
import persistence.entities.Subscription;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Julia on 09.08.2018
 */
public class SubscriptionDAO extends AbstractDAO implements ISubscriptionDAO {
    private final Logger logger = LoggerLoader.getLogger(SubscriptionDAO.class);

    private DAOFactory daoFactory = new MySqlDAOFactory();

    private static SubscriptionDAO subscriptionDAO;

    private final String SELECT_ALL_FROM_SUBSCRIPTION = "SELECT * FROM subscription ";

    private final String INSERT_SUBSCRIPTION = "INSERT INTO subscription (user_id, periodical_id, payment_id, expiration_date) " +
            "VALUES (?,?,?,?)";

    private final String UPDATE_SUBSCRIPTION = "UPDATE subscription SET subscription.user_id = ?, subscription.periodical_id = ?, " +
            "subscription.payment_id = ?, subscription.expiration_date = ? " +
            "WHERE subscription.id = ?";

    private final String DELETE_SUBSCRIPTION = "DELETE FROM subscription WHERE subscription.id = ?";

    private SubscriptionDAO() {
    }

    public static SubscriptionDAO getInstance() {
        if (subscriptionDAO == null) {
            subscriptionDAO = new SubscriptionDAO();
        }
        return subscriptionDAO;
    }


    @Override
    public Subscription findSubscriptionById(int id) throws SQLException {
        Subscription subscription;
        try {
            subscription = findById(SELECT_ALL_FROM_SUBSCRIPTION + "WHERE subscription.id = ?", id,
                    set -> set != null ? new Subscription(
                            set.getInt("id"),
                            daoFactory.getUserDAO().findUserById(set.getInt("user_id")),
                            daoFactory.getPeriodicalDAO().findPeriodicalById(set.getInt("periodical_id")),
                            daoFactory.getPaymentDAO().findPaymentById(set.getInt("payment_id")),
                            set.getTimestamp("expiration_date")) : null);
        } catch (SQLException e) {
            logger.error("Failed to find subscription by id ", e);
            throw new SQLException();
        }
        return subscription;
    }

    @Override
    public ArrayList<Subscription> findSubscriptionsByUser(int id) throws SQLException {
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_SUBSCRIPTION +
                    "WHERE subscription.user_id = " + id)) {
                while (resultSet.next()) {
                    Subscription subscription = new Subscription(resultSet.getInt("id"),
                            daoFactory.getUserDAO().findUserById(resultSet.getInt("user_id")),
                            daoFactory.getPeriodicalDAO().findPeriodicalById(resultSet.getInt("periodical_id")),
                            daoFactory.getPaymentDAO().findPaymentById(resultSet.getInt("payment_id")),
                            resultSet.getTimestamp("expiration_date"));
                    subscriptions.add(subscription);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to find subscriptions by user ", e);
            throw new SQLException();
        }
        return subscriptions;
    }

    @Override
    public ArrayList<Subscription> findSubscriptionsByPeriodical(int id) throws SQLException {
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_SUBSCRIPTION +
                    "WHERE subscription.periodical_id = " + id)) {
                while (resultSet.next()) {
                    Subscription subscription = new Subscription(resultSet.getInt("id"),
                            daoFactory.getUserDAO().findUserById(resultSet.getInt("user_id")),
                            daoFactory.getPeriodicalDAO().findPeriodicalById(resultSet.getInt("periodical_id")),
                            daoFactory.getPaymentDAO().findPaymentById(resultSet.getInt("payment_id")),
                            resultSet.getTimestamp("expiration_date"));
                    subscriptions.add(subscription);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to find subscriptions by periodical ", e);
            throw new SQLException();
        }
        return subscriptions;
    }

    @Override
    public ArrayList<Subscription> findAllSubscription() throws SQLException {
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_SUBSCRIPTION)) {
                while (resultSet.next()) {
                    Subscription subscription = new Subscription(resultSet.getInt("id"),
                            daoFactory.getUserDAO().findUserById(resultSet.getInt("user_id")),
                            daoFactory.getPeriodicalDAO().findPeriodicalById(resultSet.getInt("periodical_id")),
                            daoFactory.getPaymentDAO().findPaymentById(resultSet.getInt("payment_id")),
                            resultSet.getTimestamp("expiration_date"));
                    subscriptions.add(subscription);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to fins all subscriptions", e);
            throw new SQLException();
        }
        return subscriptions;
    }

    @Override
    public void insertSubscription(Subscription subscription, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SUBSCRIPTION, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, subscription.getUser().getId());
            statement.setInt(2, subscription.getPeriodical().getId());
            statement.setInt(3, subscription.getPayment().getId());
            statement.setTimestamp(4, subscription.getExpirationDate());
            statement.executeUpdate();
            subscription.setId(getGeneratedKey(statement));
        } catch (SQLException e) {
            logger.error("Failed to insert subscription ", e);
            throw new SQLException();
        }
    }

    @Override
    public void updateSubscription(Subscription subscription) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SUBSCRIPTION)) {
            statement.setInt(1, subscription.getUser().getId());
            statement.setInt(2, subscription.getPeriodical().getId());
            statement.setInt(3, subscription.getPayment().getId());
            statement.setTimestamp(4, subscription.getExpirationDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to update subscription ", e);
            throw new SQLException();
        }
    }

    @Override
    public void deleteSubscription(Subscription subscription) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SUBSCRIPTION)) {
            statement.setInt(1, subscription.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to delete subscription ", e);
            throw new SQLException();
        }
    }

}
