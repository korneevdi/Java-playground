package airport.dao.dictionary;

import airport.entity.dictionary.Type;

import java.sql.*;
import java.util.List;

public class TypesDao extends AbstractDictionaryDao<Type> {

    private static final String TABLE_NAME = "types";
    private static final String ID_NAME = "type_id";
    private final static List<String> UNIQUE_FIELDS = List.of(
            "type_name"
    );

    public TypesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
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
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (type_name) VALUES
                (?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Type type) throws SQLException {
        ps.setString(1, type.getName());
    }

    @Override
    protected Type mapRow(ResultSet resultSet) throws SQLException {
        return new Type(
                resultSet.getInt("type_id"),
                resultSet.getString("type_name")
        );
    }
}
