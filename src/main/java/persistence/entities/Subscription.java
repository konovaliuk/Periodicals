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
    private Timestamp expiration_date;

    public Subscription() {
    }

    public Subscription(int id, User user, Periodical periodical, Payment payment, Timestamp expiration_date) {
        this.id = id;
        this.user = user;
        this.periodical = periodical;
        this.payment = payment;
        this.expiration_date = expiration_date;
    }

    public Subscription(User user, Periodical periodical, Payment payment, Timestamp expiration_date) {
        this.user = user;
        this.periodical = periodical;
        this.payment = payment;
        this.expiration_date = expiration_date;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Periodical getPeriodical() {
        return periodical;
    }

    public Payment getPayment() {
        return payment;
    }

    public Timestamp getExpiration_date() {
        return expiration_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setExpiration_date(Timestamp expiration_date) {
        this.expiration_date = expiration_date;
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
                Objects.equals(expiration_date, that.expiration_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, periodical, payment, expiration_date);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", user=" + user +
                ", periodical=" + periodical +
                ", payment=" + payment +
                ", expiration_date=" + expiration_date +
                '}';
    }
}
