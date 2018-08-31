package persistence.dao.dataSource;

import logging.LoggerLoader;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Julia on 16.08.2018
 */
public class MySqlDataSource {
    private static final Logger logger = LoggerLoader.getLogger(MySqlDataSource.class);

    private static MySqlDataSource dataSource;

    private MySqlDataSource() {
    }

    public static MySqlDataSource getInstance() {
        if (dataSource == null) {
            dataSource = new MySqlDataSource();
        }
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        DataSource dataSource;
        Connection connection = null;
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (javax.sql.DataSource) initialContext.lookup("java:/comp/env/jdbc/periodicals");
            connection = dataSource.getConnection();
        } catch (NamingException e) {
            logger.error("Failed to get connection ", e);
        }
        return connection;
    }
}
