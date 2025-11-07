package airport.dao.dictionary;

import airport.entity.dictionary.Gate;

import java.sql.*;
import java.util.List;

public class GatesDao extends AbstractDictionaryDao<Gate> {

    private static final String TABLE_NAME = "gates";
    private static final String ID_NAME = "gate_id";
    private final static List<String> UNIQUE_FIELDS = List.of(
            "gate_number"
    );

    public GatesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
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
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (gate_number) VALUES
                (?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Gate gate) throws SQLException {
        ps.setString(1, gate.getNumber());
    }

    @Override
    protected String buildUpdateSql() {
        return """
                UPDATE %s
                SET gate_number = ?
                WHERE %s = ?
                """.formatted(TABLE_NAME, ID_NAME);
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
}
