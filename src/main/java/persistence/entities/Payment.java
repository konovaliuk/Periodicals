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
    private BigDecimal price;

    public Payment() {
    }

    public Payment(int id, Timestamp date, BigDecimal price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Timestamp getDate() {
        return date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id &&
                Objects.equals(date, payment.date) &&
                Objects.equals(price, payment.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, price);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
