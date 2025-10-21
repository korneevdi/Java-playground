package airport.dao.dictionary;

import airport.entity.dictionary.Sex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SexesDao extends AbstractDictionaryDao<Sex> {

    private static final String TABLE_NAME = "sexes";
    private static final String ID_NAME = "sex_id";
    private final static List<String> UNIQUE_FIELDS = new ArrayList<>() {{
        add("sex_name");
    }};

    public SexesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT s.sex_id, s.sex_name
                FROM sexes s
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT s.sex_id, s.sex_name
                FROM sexes s
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT s.sex_id, s.sex_name
                FROM sexes s
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
    protected void setExistsStatement(PreparedStatement ps, Sex sex) throws SQLException {
        ps.setString(1, sex.getName());
    }

    @Override
    protected Sex mapRow(ResultSet resultSet) throws SQLException {
        return new Sex(
                resultSet.getInt("sex_id"),
                resultSet.getString("sex_name")
        );
    }
}
