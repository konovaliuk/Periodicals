package persistence.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Julia on 27.08.2018
 */
public class PeriodicalPeriod implements Serializable {

    private int id;
    private String period;

    public PeriodicalPeriod() {
    }

    public PeriodicalPeriod(int id, String period) {
        this.id = id;
        this.period = period;
    }

    public int getId() {
        return id;
    }

    public String getPeriod() {
        return period;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodicalPeriod that = (PeriodicalPeriod) o;
        return id == that.id &&
                Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, period);
    }

    @Override
    public String toString() {
        return "PeriodicalPeriod{" +
                "id=" + id +
                ", period='" + period + '\'' +
                '}';
    }
}
