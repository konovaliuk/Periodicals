package persistence.entities;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Julia on 27.07.2018
 */
public class Periodical implements Serializable {

    private int id;
    private String title;
    private PeriodicalType periodicalType;
    private PeriodicalPeriod periodicalPeriod;
    private String category;
    private BigDecimal price;
    private String description;

    public Periodical() {
    }

    public Periodical(int id, String title, PeriodicalType periodicalType, PeriodicalPeriod periodicalPeriod,
                      String category, BigDecimal price, String description) {
        this.id = id;
        this.title = title;
        this.periodicalType = periodicalType;
        this.periodicalPeriod = periodicalPeriod;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public Periodical(String title, PeriodicalType periodicalType, PeriodicalPeriod periodicalPeriod,
                      String category, BigDecimal price, String description) {
        this.title = title;
        this.periodicalType = periodicalType;
        this.periodicalPeriod = periodicalPeriod;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public PeriodicalType getPeriodicalType() {
        return periodicalType;
    }

    public PeriodicalPeriod getPeriodicalPeriod() {
        return periodicalPeriod;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPeriodicalType(PeriodicalType periodicalType) {
        this.periodicalType = periodicalType;
    }

    public void setPeriodicalPeriod(PeriodicalPeriod periodicalPeriod) {
        this.periodicalPeriod = periodicalPeriod;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periodical that = (Periodical) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(periodicalType, that.periodicalType) &&
                Objects.equals(periodicalPeriod, that.periodicalPeriod) &&
                Objects.equals(category, that.category) &&
                Objects.equals(price, that.price) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, periodicalType, periodicalPeriod, category, price, description);
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", periodicalType=" + periodicalType +
                ", periodicalPeriod=" + periodicalPeriod +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
