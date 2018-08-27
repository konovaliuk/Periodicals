package persistence.dao;

import persistence.entities.Payment;

import java.util.ArrayList;

/**
 * Created by Julia on 14.08.2018
 */
public interface IPayment {

    /**
     * Payment's selection by id
     *
     * @param id - payment id
     * @return - payment or null
     */
    Payment findPaymentById(int id);

    /**
     * Selection all payments in the database
     *
     * @return - list of payments
     */
    ArrayList<Payment> findAllPayments();

    /**
     * Insert new payment
     *
     * @param payment - payment to be inserted to the database
     * @return - {@code true} if new payment id added, {@code false} if no records is inserted
     */
    boolean insertPayment(Payment payment);

    /**
     * Update payment info
     *
     * @param payment - payment info to be updated in the database
     * @return - {@code true} if payment info is updated, {@code false} if no records is updated
     */
    boolean updatePayment(Payment payment);

    /**
     * Delete payment
     *
     * @param payment - payment to be deleted from the database
     * @return - {@code true} if payment is deleted, {@code false} if no records is deleted
     */
    boolean deletePayment(Payment payment);
}
