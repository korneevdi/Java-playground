package airport.dao.dictionary;

import airport.dao.AbstractDao;
import airport.entity.dictionary.CrewRole;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class CrewRolesDao extends AbstractDao<CrewRole> {

    private static final String TABLE_NAME = "crew_roles";
    private static final String ID_NAME = "role_id";
    private final static List<String> ALL_FIELDS = List.of(
            "role_name"
    );
    private final static List<String> UNIQUE_FIELDS = ALL_FIELDS;

    public CrewRolesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, ALL_FIELDS, UNIQUE_FIELDS);
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
    protected void setInsertStatement(PreparedStatement ps, CrewRole crewRole) throws SQLException {
        ps.setString(1, crewRole.getName());
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, CrewRole crewRole) throws SQLException {
        ps.setString(1, crewRole.getName());
        ps.setInt(2, crewRole.getId());
    }

    @Override
    protected CrewRole mapRow(ResultSet resultSet) throws SQLException {
        return new CrewRole(
                resultSet.getInt("role_id"),
                resultSet.getString("role_name")
        );
    }

    @Override
    public Map<String, String> getColumnToFieldMap() {
        return Map.ofEntries(
                Map.entry("role_id", "id"),
                Map.entry("role_name", "name")
        );
    }
}
