package airport.dao.dictionary;

import airport.entity.dictionary.Type;

import java.sql.*;

public class TypesDao extends AbstractDictionaryDao<Type> {

    private static final String TABLE_NAME = "types";
    private static final String ID_NAME = "type_id";

    public TypesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
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
    protected Type mapRow(ResultSet resultSet) throws SQLException {
        return new Type(
                resultSet.getInt("type_id"),
                resultSet.getString("type_name")
        );
    }
}
