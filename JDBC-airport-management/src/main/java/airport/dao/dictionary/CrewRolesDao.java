package airport.dao.dictionary;

import airport.entity.dictionary.CrewRole;

import java.sql.*;
import java.util.List;

public class CrewRolesDao extends AbstractDictionaryDao<CrewRole> {

    private static final String TABLE_NAME = "crew_roles";
    private static final String ID_NAME = "role_id";
    private final static List<String> UNIQUE_FIELDS = List.of(
            "role_name"
    );

    public CrewRolesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT cr.role_id, cr.role_name
                FROM crew_roles cr
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT cr.role_id, cr.role_name
                FROM crew_roles cr
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT cr.role_id, cr.role_name
                FROM crew_roles cr
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, CrewRole crewRole) throws SQLException {
        ps.setString(1, crewRole.getName());
    }

    @Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (role_name) VALUES
                (?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, CrewRole crewRole) throws SQLException {
        ps.setString(1, crewRole.getName());
    }

    @Override
    protected CrewRole mapRow(ResultSet resultSet) throws SQLException {
        return new CrewRole(
                resultSet.getInt("role_id"),
                resultSet.getString("role_name")
        );
    }
}
