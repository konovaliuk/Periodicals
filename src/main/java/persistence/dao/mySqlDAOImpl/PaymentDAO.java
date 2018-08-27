package persistence.dao.mySqlDAOImpl;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.IPayment;
import persistence.entities.Payment;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Julia on 14.08.2018
 */
public class PaymentDAO extends AbstractDAO implements IPayment {

    private static final Logger logger = LoggerLoader.getLogger(PaymentDAO.class);

    private static PaymentDAO paymentDAO;

    private final String SELECT_ALL_FROM_PAYMENT = "SELECT * FROM payment ";

    private final String INSERT_PAYMENT = "INSERT INTO payment (date, price) " +
            "VALUES (?,?)";

    private final String UPDATE_PERIODICAL = "UPDATE payment SET payment.date = ?, payment.price = ?" +
            "WHERE payment.id = ?";

    private PaymentDAO() {
    }

    public static PaymentDAO getInstance() {
        if (paymentDAO == null) {
            paymentDAO = new PaymentDAO();
        }
        return paymentDAO;
    }

    @Override
    public Payment findPaymentById(int id) {
        Payment payment = null;
        try {
            payment = findById(SELECT_ALL_FROM_PAYMENT + " WHERE payment.id = ?", id,
                    set -> set != null ? new Payment(
                            set.getInt("id"),
                            set.getTimestamp("date"),
                            set.getBigDecimal("price")) : null);
        } catch (SQLException e) {
            logger.error("Failed to find payment by id", e);
        }
        return payment;
    }

    @Override
    public ArrayList<Payment> findAllPayments() {
        ArrayList<Payment> payments = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_PAYMENT)) {
                while (resultSet.next()) {
                    Payment payment = new Payment(resultSet.getInt("id"),
                            resultSet.getTimestamp("date"),
                            resultSet.getBigDecimal("price"));
                    payments.add(payment);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to find all payments", e);
        }
        return payments;
    }

    @Override
    public boolean insertPayment(Payment payment) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, payment.getDate());
            statement.setBigDecimal(2, payment.getPrice());
            if (statement.executeUpdate() != 0) {
                payment.setId(getGeneratedKey(statement));
                return true;
            }

        } catch (SQLException e) {
            logger.error("Failed to insert payment", e);
        }
        return false;
    }

    @Override
    public boolean updatePayment(Payment payment) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PERIODICAL)) {
            statement.setTimestamp(1, payment.getDate());
            statement.setBigDecimal(2, payment.getPrice());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Failed to update payment", e);
        }
        return false;
    }

    @Override
    public boolean deletePayment(Payment payment) {
        String query = "DELETE FROM payment WHERE payment.id = " + payment.getId();
        try {
            if (execute(query) != 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Failed to delete payment", e);
        }
        return false;
    }
}
