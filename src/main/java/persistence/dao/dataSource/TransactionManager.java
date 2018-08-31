package persistence.dao.dataSource;

import logging.LoggerLoader;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Julia on 28.08.2018
 */
public class TransactionManager {
    private static final Logger logger = LoggerLoader.getLogger(TransactionManager.class);
    private static TransactionManager transactionManager;

    private TransactionManager() {
    }

    public static TransactionManager getInstance() {
        if (transactionManager == null) {
            transactionManager = new TransactionManager();
        }
        return transactionManager;
    }


    public Connection getConnection() throws SQLException {
        Connection connection = MySqlDataSource.getInstance().getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    public void close(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        connection.close();
    }
}
