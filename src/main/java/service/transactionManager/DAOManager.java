package service.transactionManager;

import persistence.dao.dataSource.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Julia on 28.08.2018
 */
public class DAOManager {

    public Connection getConnection() throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    public void close(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        connection.close();
    }
}
