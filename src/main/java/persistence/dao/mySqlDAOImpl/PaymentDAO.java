package persistence.dao.mySqlDAOImpl;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IPaymentDAO;
import persistence.entities.Payment;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Julia on 14.08.2018
 */
public class PaymentDAO extends AbstractDAO implements IPaymentDAO {
    private final Logger logger = LoggerLoader.getLogger(PaymentDAO.class);

    private static PaymentDAO paymentDAO;

    private final String SELECT_ALL_FROM_PAYMENT = "SELECT * FROM payment ";

    private final String INSERT_PAYMENT = "INSERT INTO payment (date, amount) " +
            "VALUES (?,?)";

    private final String UPDATE_PAYMENT = "UPDATE payment SET payment.date = ?, payment.amount = ?" +
            "WHERE payment.id = ?";

    private final String DELETE_PAYMENT = "DELETE FROM payment WHERE payment.id = ?";

    private PaymentDAO() {
    }

    public static PaymentDAO getInstance() {
        if (paymentDAO == null) {
            paymentDAO = new PaymentDAO();
        }
        return paymentDAO;
    }

    @Override
    public Payment findPaymentById(int id) throws SQLException {
        Payment payment;
        try {
            payment = findById(SELECT_ALL_FROM_PAYMENT + " WHERE payment.id = ?", id,
                    set -> set != null ? new Payment(
                            set.getInt("id"),
                            set.getTimestamp("date"),
                            set.getBigDecimal("amount")) : null);
        } catch (SQLException e) {
            logger.error("Failed to find payment by id ", e);
            throw new SQLException();
        }
        return payment;
    }

    @Override
    public ArrayList<Payment> findAllPayments() throws SQLException {
        ArrayList<Payment> payments = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_PAYMENT)) {
                while (resultSet.next()) {
                    Payment payment = new Payment(resultSet.getInt("id"),
                            resultSet.getTimestamp("date"),
                            resultSet.getBigDecimal("amount"));
                    payments.add(payment);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to find all payments ", e);
            throw new SQLException();
        }
        return payments;
    }

    @Override
    public void insertPayment(Payment payment, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, payment.getDate());
            statement.setBigDecimal(2, payment.getAmount());
            statement.executeUpdate();
            payment.setId(getGeneratedKey(statement));
        } catch (SQLException e) {
            logger.error("Failed to insert payment ", e);
            throw new SQLException();
        }
    }

    @Override
    public void updatePayment(Payment payment) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PAYMENT)) {
            statement.setTimestamp(1, payment.getDate());
            statement.setBigDecimal(2, payment.getAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to update payment ", e);
            throw new SQLException();
        }
    }

    @Override
    public void deletePayment(Payment payment) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PAYMENT)) {
            statement.setInt(1, payment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to delete payment ", e);
            throw new SQLException();
        }
    }
}
