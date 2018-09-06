package persistence.dao.daoFactory;

import persistence.dao.*;

/**
 * Created by Julia on 09.08.2018
 */
public abstract class DAOFactory {

    public static MySqlDAOFactory getMySqlDAOFactory() {
        return new MySqlDAOFactory();
    }

    public abstract IAccountDAO getAccountDAO();

    public abstract IPaymentDAO getPaymentDAO();

    public abstract IPeriodicalDAO getPeriodicalDAO();

    public abstract IPeriodicalPeriodDAO getPeriodicalPeriodDAO();

    public abstract IPeriodicalTypeDAO getPeriodicalTypeDAO();

    public abstract ISubscriptionDAO getSubscriptionDAO();

    public abstract IUserDAO getUserDAO();

    public abstract IUserRoleDAO getUserRoleDAO();
}
