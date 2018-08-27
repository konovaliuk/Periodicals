package persistence.dao.daoFactory;

import persistence.dao.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Julia on 09.08.2018
 */
public abstract class DAOFactory {

    public static MySqlDAOFactory getMySqlDAOFactory() {
        return new MySqlDAOFactory();
    }

    public abstract IPayment getPaymentDAO();

    public abstract IPeriodical getPeriodicalDAO();

    public abstract IPeriodicalPeriod getPeriodicalPeriodDAO();

    public abstract IPeriodicalType getPeriodicalTypeDAO();

    public abstract ISubscription getSubscriptionDAO();

    public abstract IUser getUserDAO();

    public abstract IUserRole getUserRoleDAO();
}
