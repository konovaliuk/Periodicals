package persistence.dao.mySqlDAOImpl;


import persistence.dao.dataSource.MySqlDataSource;
import persistence.dao.Mapper;

import java.sql.*;

/**
 * Created by Julia on 09.08.2018
 */
public abstract class AbstractDAO {

    protected MySqlDataSource dataSource = MySqlDataSource.getInstance();


    protected <T> T findById(String query, int id, Mapper<T> mapper) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapper.map(resultSet);
                }
            }
        }
        return null;
    }

    protected int getGeneratedKey(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        throw new SQLException("Key is not generated");
    }
}
