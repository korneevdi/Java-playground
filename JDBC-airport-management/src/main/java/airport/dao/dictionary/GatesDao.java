package airport.dao.dictionary;

import airport.dao.AbstractDao;
import airport.entity.dictionary.Gate;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class GatesDao extends AbstractDao<Gate> {

    private static final String TABLE_NAME = "gates";
    private static final String ID_NAME = "gate_id";
    private final static List<String> ALL_FIELDS = List.of(
            "gate_number"
    );
    private final static List<String> UNIQUE_FIELDS = ALL_FIELDS;

    public GatesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, ALL_FIELDS, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT g.gate_id, g.gate_number
                FROM gates g
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT g.gate_id, g.gate_number
                FROM gates g
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT g.gate_id, g.gate_number
                FROM gates g
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, Gate gate) throws SQLException {
        ps.setString(1, gate.getNumber());
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Gate gate) throws SQLException {
        ps.setString(1, gate.getNumber());
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Gate gate) throws SQLException {
        ps.setString(1, gate.getNumber());
        ps.setInt(2, gate.getId());
    }

    @Override
    protected Gate mapRow(ResultSet resultSet) throws SQLException {
        return new Gate(
                resultSet.getInt("gate_id"),
                resultSet.getString("gate_number")
        );
    }

    @Override
    public Map<String, String> getColumnToFieldMap() {
        return Map.ofEntries(
                Map.entry("gate_id", "id"),
                Map.entry("gate_number", "number")
        );
    }
}
