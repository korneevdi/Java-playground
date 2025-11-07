package airport.dao.dictionary;

import airport.dao.AbstractDao;
import airport.entity.dictionary.CheckInCounter;

import java.sql.*;
import java.util.List;

public class CheckInCountersDao extends AbstractDao<CheckInCounter> {

    private static final String TABLE_NAME = "check_in_counters";
    private static final String ID_NAME = "counter_id";
    private final static List<String> ALL_FIELDS = List.of(
            "counter_number"
    );
    private final static List<String> UNIQUE_FIELDS = ALL_FIELDS;

    public CheckInCountersDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, ALL_FIELDS, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT cic.counter_id, cic.counter_number
                FROM check_in_counters cic
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT cic.counter_id, cic.counter_number
                FROM check_in_counters cic
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT cic.counter_id, cic.counter_number
                FROM check_in_counters cic
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, CheckInCounter checkInCounter) throws SQLException {
        ps.setString(1, checkInCounter.getNumber());
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, CheckInCounter checkInCounter) throws SQLException {
        ps.setString(1, checkInCounter.getNumber());
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, CheckInCounter checkInCounter) throws SQLException {
        ps.setString(1, checkInCounter.getNumber());
        ps.setInt(2, checkInCounter.getId());
    }

    @Override
    protected CheckInCounter mapRow(ResultSet resultSet) throws SQLException {
        return new CheckInCounter(
                resultSet.getInt("counter_id"),
                resultSet.getString("counter_number")
        );
    }
}
