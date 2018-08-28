package persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Julia on 14.08.2018
 */
public class Payment implements Serializable {
    private int id;
    private Timestamp date;
    private BigDecimal amount;

    public Payment() {
    }

    public Payment(int id, Timestamp date, BigDecimal amount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
    }

    public Payment(Timestamp date, BigDecimal amount) {
        this.date = date;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public Timestamp getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id &&
                Objects.equals(date, payment.date) &&
                Objects.equals(amount, payment.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, amount);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}
