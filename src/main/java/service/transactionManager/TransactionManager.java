package service.transactionManager;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.dataSource.MySqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * TransactionManager is responsible for providing connection with AutoCommit(false)
 * Created by Julia on 28.08.2018
 */
public abstract class TransactionManager {
    private static final Logger logger = LoggerLoader.getLogger(TransactionManager.class);

    /**
     * Get connection form MySqlDataSource and set AutoCommit(false)
     *
     * @return connection with AutoCommit(false)
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        Connection connection = MySqlDataSource.getInstance().getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    /**
     * Close connection
     */
    public void close(Connection connection) {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            logger.error("Failed to close connection ", e);
        }
    }
}
