package persistence.dao;

import persistence.entities.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Julia on 14.08.2018
 */
public interface IPaymentDAO {

    /**
     * Payment's selection by id
     *
     * @param id - payment id
     * @return - payment or null
     */
    Payment findPaymentById(int id) throws SQLException;

    /**
     * Selection all payments in the database
     *
     * @return - list of payments
     */
    ArrayList<Payment> findAllPayments() throws SQLException;

    /**
     * Insert new payment
     *
     * @param payment    - payment to be inserted to the database
     * @param connection - connection with setAutoCommit(false)
     */
    void insertPayment(Payment payment, Connection connection) throws SQLException;

    /**
     * Update payment info
     *
     * @param payment    - payment info to be updated in the database
     */
    void updatePayment(Payment payment) throws SQLException;

    /**
     * Delete payment
     *
     * @param payment - payment to be deleted from the database
     */
    void deletePayment(Payment payment) throws SQLException;
}
