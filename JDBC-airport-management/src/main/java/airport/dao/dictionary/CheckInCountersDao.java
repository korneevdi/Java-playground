package airport.dao.dictionary;

import airport.entity.dictionary.CheckInCounter;

import java.sql.*;
import java.util.List;

public class CheckInCountersDao extends AbstractDictionaryDao<CheckInCounter> {

    private static final String TABLE_NAME = "check_in_counters";
    private static final String ID_NAME = "counter_id";
    private final static List<String> UNIQUE_FIELDS = List.of(
            "counter_number"
    );

    public CheckInCountersDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
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
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (counter_number) VALUES
                (?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, CheckInCounter checkInCounter) throws SQLException {
        ps.setString(1, checkInCounter.getNumber());
    }

    @Override
    protected String buildUpdateSql() {
        return """
                UPDATE %s
                SET counter_number = ?
                WHERE %s = ?
                """.formatted(TABLE_NAME, ID_NAME);
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
