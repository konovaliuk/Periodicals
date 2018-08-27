package persistence.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Julia on 12.08.2018
 */
public class PeriodicalType implements Serializable {

    private int id;
    private String type;

    public PeriodicalType() {
    }

    public PeriodicalType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodicalType that = (PeriodicalType) o;
        return id == that.id &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "PeriodicalType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
