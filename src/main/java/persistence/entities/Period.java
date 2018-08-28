package persistence.entities;


/**
 * Created by Julia on 29.08.2018
 */
public enum Period {

    ONCE_A_MONTH(1),
    ONCE_A_THREE_MONTH(3),
    ONCE_A_YEAR(12);

    private int term;

    Period(int term) {
        this.term = term;
    }

    public int getTerm() {
        return term;
    }

    @Override
    public String toString() {
        return "Period{" +
                "term=" + term +
                '}';
    }
}
