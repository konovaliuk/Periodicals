package persistence.entities;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Julia on 27.07.2018
 */
public class Subscription implements Serializable {

    private int id;
    private User user;
    private Periodical periodical;
    private Payment payment;
    private Timestamp expirationDate;

    public Subscription() {
    }

    public Subscription(int id, User user, Periodical periodical, Payment payment, Timestamp expirationDate) {
        this.id = id;
        this.user = user;
        this.periodical = periodical;
        this.payment = payment;
        this.expirationDate = expirationDate;
    }

    public Subscription(User user, Periodical periodical, Payment payment, Timestamp expirationDate) {
        this.user = user;
        this.periodical = periodical;
        this.payment = payment;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Periodical getPeriodical() {
        return periodical;
    }

    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return id == that.id &&
                Objects.equals(user, that.user) &&
                Objects.equals(periodical, that.periodical) &&
                Objects.equals(payment, that.payment) &&
                Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, periodical, payment, expirationDate);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", user=" + user +
                ", periodical=" + periodical +
                ", payment=" + payment +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
