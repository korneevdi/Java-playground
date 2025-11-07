package airport.dao.dictionary;

import airport.entity.dictionary.ControlType;

import java.sql.*;
import java.util.List;

public class ControlTypesDao extends AbstractDictionaryDao<ControlType> {

    private static final String TABLE_NAME = "control_types";
    private static final String ID_NAME = "control_type_id";
    private final static List<String> UNIQUE_FIELDS = List.of(
            "type_name"
    );

    public ControlTypesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT cc.type_id, cc.type_name
                FROM control_types cc
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT cc.type_id, cc.type_name
                FROM control_types cc
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT cc.type_id, cc.type_name
                FROM control_types cc
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, ControlType controlType) throws SQLException {
        ps.setString(1, controlType.getName());
    }

    @Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (type_name) VALUES
                (?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, ControlType controlType) throws SQLException {
        ps.setString(1, controlType.getName());
    }

    @Override
    protected String buildUpdateSql() {
        return """
                UPDATE %s
                SET type_name = ?
                WHERE %s = ?
                """.formatted(TABLE_NAME, ID_NAME);
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, ControlType controlType) throws SQLException {
        ps.setString(1, controlType.getName());
        ps.setInt(2, controlType.getId());
    }

    @Override
    protected ControlType mapRow(ResultSet resultSet) throws SQLException {
        return new ControlType(
                resultSet.getInt("type_id"),
                resultSet.getString("type_name")
        );
    }
}
