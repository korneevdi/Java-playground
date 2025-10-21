package airport.dao.dictionary;

import airport.entity.dictionary.CheckInCounter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckInCountersDao extends AbstractDictionaryDao<CheckInCounter> {

    private static final String TABLE_NAME = "check_in_counters";
    private static final String ID_NAME = "counter_id";
    private final static List<String> UNIQUE_FIELDS = new ArrayList<>() {{
        add("counter_number");
    }};

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
    protected String buildExistsSql() {
        return """
                SELECT 1 FROM %s
                WHERE %s = ?
                """.formatted(TABLE_NAME, UNIQUE_FIELDS.get(0));
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, CheckInCounter checkInCounter) throws SQLException {
        ps.setString(1, checkInCounter.getNumber());
    }

    @Override
    protected CheckInCounter mapRow(ResultSet resultSet) throws SQLException {
        return new CheckInCounter(
                resultSet.getInt("counter_id"),
                resultSet.getString("counter_number")
        );
    }
}
