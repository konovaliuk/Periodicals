package service;

import persistence.dao.IPeriodical;
import persistence.dao.IPeriodicalPeriod;
import persistence.dao.IPeriodicalType;
import persistence.dao.daoFactory.DAOFactory;
import persistence.dao.daoFactory.MySqlDAOFactory;
import persistence.entities.Periodical;
import persistence.entities.PeriodicalPeriod;
import persistence.entities.PeriodicalType;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Julia on 15.08.2018
 */
public class PeriodicalService {

    private static MySqlDAOFactory mySqlDAOFactory = DAOFactory.getMySqlDAOFactory();
    private static IPeriodical iPeriodical = mySqlDAOFactory.getPeriodicalDAO();
    private static IPeriodicalType iPeriodicalType = mySqlDAOFactory.getPeriodicalTypeDAO();
    private static IPeriodicalPeriod iPeriodicalPeriod = mySqlDAOFactory.getPeriodicalPeriodDAO();

    public static ArrayList<Periodical> getPeriodicals() {
        return iPeriodical.findAllPeriodicals();
    }

    public static Periodical getPeriodical(int id) {
        return iPeriodical.findPeriodicalById(id);
    }

    public static boolean createPeriodical(String title, String type, String period,
                                           String category, BigDecimal price, String description) {

        PeriodicalType periodicalType = iPeriodicalType.findTypeByPeriodicalType(type);
        PeriodicalPeriod periodicalPeriod = iPeriodicalPeriod.findPeriodByPeriodicalPeriod(period);
        Periodical periodical = new Periodical(title, periodicalType, periodicalPeriod, category, price, description);
        return iPeriodical.insertPeriodical(periodical);
    }

}
