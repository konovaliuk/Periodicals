package service.transactionManager;

import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.dataSource.MySqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Julia on 28.08.2018
 */
public abstract class TransactionManager {
    private static final Logger logger = LoggerLoader.getLogger(TransactionManager.class);

    public Connection getConnection() throws SQLException {
        Connection connection = MySqlDataSource.getInstance().getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    public void close(Connection connection) {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            logger.error("Failed to close connection ",e);
        }
    }
}
