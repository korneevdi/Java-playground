package airport.dao.dictionary;

import airport.entity.dictionary.Gate;

import java.sql.*;

public class GatesDao extends AbstractDictionaryDao<Gate> {

    private static final String TABLE_NAME = "gates";
    private static final String ID_NAME = "gate_id";

    public GatesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
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
    protected Gate mapRow(ResultSet resultSet) throws SQLException {
        return new Gate(
                resultSet.getInt("gate_id"),
                resultSet.getString("gate_number")
        );
    }
}
