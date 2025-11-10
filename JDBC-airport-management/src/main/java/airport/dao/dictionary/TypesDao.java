package airport.dao.dictionary;

import airport.dao.AbstractDao;
import airport.entity.dictionary.Type;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class TypesDao extends AbstractDao<Type> {

    private static final String TABLE_NAME = "types";
    private static final String ID_NAME = "type_id";
    private final static List<String> ALL_FIELDS = List.of(
            "type_name"
    );
    private final static List<String> UNIQUE_FIELDS = ALL_FIELDS;

    public TypesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, ALL_FIELDS, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT t.type_id, t.type_name
                FROM types t
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT t.type_id, t.type_name
                FROM types t
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT t.type_id, t.type_name
                FROM types t
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, Type type) throws SQLException {
        ps.setString(1, type.getName());
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Type type) throws SQLException {
        ps.setString(1, type.getName());
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Type type) throws SQLException {
        ps.setString(1, type.getName());
        ps.setInt(2, type.getId());
    }

    @Override
    protected Type mapRow(ResultSet resultSet) throws SQLException {
        return new Type(
                resultSet.getInt("type_id"),
                resultSet.getString("type_name")
        );
    }

    @Override
    public Map<String, String> getColumnToFieldMap() {
        return Map.ofEntries(
                Map.entry("type_id", "id"),
                Map.entry("type_name", "name")
        );
    }
}
