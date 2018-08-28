package persistence.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Julia on 27.08.2018
 */
public class PeriodicalPeriod implements Serializable {

    private int id;
    private String period;
    private int term;

    public PeriodicalPeriod() {
    }

    public PeriodicalPeriod(int id, String period, int term) {
        this.id = id;
        this.period = period;
        this.term = term;
    }

    public int getId() {
        return id;
    }

    public String getPeriod() {
        return period;
    }

    public int getTerm() {
        return term;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodicalPeriod that = (PeriodicalPeriod) o;
        return id == that.id &&
                term == that.term &&
                Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, period, term);
    }

    @Override
    public String toString() {
        return "PeriodicalPeriod{" +
                "id=" + id +
                ", period='" + period + '\'' +
                ", term=" + term +
                '}';
    }
}
