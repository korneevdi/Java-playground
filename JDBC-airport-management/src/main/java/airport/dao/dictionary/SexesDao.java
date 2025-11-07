package airport.dao.dictionary;

import airport.dao.AbstractDao;
import airport.entity.dictionary.Sex;

import java.sql.*;
import java.util.List;

public class SexesDao extends AbstractDao<Sex> {

    private static final String TABLE_NAME = "sexes";
    private static final String ID_NAME = "sex_id";
    private final static List<String> ALL_FIELDS = List.of(
            "sex_name"
    );
    private final static List<String> UNIQUE_FIELDS = ALL_FIELDS;

    public SexesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, ALL_FIELDS, UNIQUE_FIELDS);
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
    protected void setExistsStatement(PreparedStatement ps, Sex sex) throws SQLException {
        ps.setString(1, sex.getName());
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Sex sex) throws SQLException {
        ps.setString(1, sex.getName());
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Sex sex) throws SQLException {
        ps.setString(1, sex.getName());
        ps.setInt(2, sex.getId());
    }

    @Override
    protected Sex mapRow(ResultSet resultSet) throws SQLException {
        return new Sex(
                resultSet.getInt("sex_id"),
                resultSet.getString("sex_name")
        );
    }
}
