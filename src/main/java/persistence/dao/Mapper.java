package persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * Created by Julia on 09.08.2018
 */
@FunctionalInterface
public interface Mapper<T> {
    T map(ResultSet set) throws SQLException;
}
