package persistence.dao.daoFactory;

import persistence.dao.*;
import persistence.dao.mySqlDAOImpl.*;
import persistence.entities.PeriodicalPeriod;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Julia on 09.08.2018
 */
public class MySqlDAOFactory extends DAOFactory {

    @Override
    public IAccount getAccountDAO() {
        return AccountDAO.getInstance();
    }

    @Override
    public IPayment getPaymentDAO() {
        return PaymentDAO.getInstance();
    }

    @Override
    public IPeriodical getPeriodicalDAO() {
        return PeriodicalDAO.getInstance();
    }

    @Override
    public IPeriodicalPeriod getPeriodicalPeriodDAO() {
        return PeriodicalPeriodDAO.getInstance();
    }

    @Override
    public IPeriodicalType getPeriodicalTypeDAO() {
        return PeriodicalTypeDAO.getInstance();
    }

    @Override
    public ISubscription getSubscriptionDAO() {
        return SubscriptionDAO.getInstance();
    }

    @Override
    public IUser getUserDAO() {
        return UserDAO.getInstance();
    }

    @Override
    public IUserRole getUserRoleDAO() {
        return UserRoleDAO.getInstance();
    }
}
