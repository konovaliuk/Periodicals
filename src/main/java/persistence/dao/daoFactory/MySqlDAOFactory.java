package persistence.dao.daoFactory;

import persistence.dao.*;
import persistence.dao.mySqlDAOImpl.*;

/**
 * Created by Julia on 09.08.2018
 */
public class MySqlDAOFactory extends DAOFactory {

    @Override
    public IAccountDAO getAccountDAO() {
        return AccountDAO.getInstance();
    }

    @Override
    public IPaymentDAO getPaymentDAO() {
        return PaymentDAO.getInstance();
    }

    @Override
    public IPeriodicalDAO getPeriodicalDAO() {
        return PeriodicalDAO.getInstance();
    }

    @Override
    public IPeriodicalPeriodDAO getPeriodicalPeriodDAO() {
        return PeriodicalPeriodDAO.getInstance();
    }

    @Override
    public IPeriodicalTypeDAO getPeriodicalTypeDAO() {
        return PeriodicalTypeDAO.getInstance();
    }

    @Override
    public ISubscriptionDAO getSubscriptionDAO() {
        return SubscriptionDAO.getInstance();
    }

    @Override
    public IUserDAO getUserDAO() {
        return UserDAO.getInstance();
    }

    @Override
    public IUserRoleDAO getUserRoleDAO() {
        return UserRoleDAO.getInstance();
    }
}
