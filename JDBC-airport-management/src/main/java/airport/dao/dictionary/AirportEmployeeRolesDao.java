package airport.dao.dictionary;

import airport.entity.dictionary.AirportEmployeeRole;

import java.sql.*;
import java.util.List;

public class AirportEmployeeRolesDao extends AbstractDictionaryDao<AirportEmployeeRole> {

    private static final String TABLE_NAME = "airport_employee_roles";
    private static final String ID_NAME = "role_id";
    private final static List<String> UNIQUE_FIELDS = List.of(
            "role_name"
    );

    public AirportEmployeeRolesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT aer.role_id, aer.role_name
                FROM airport_employee_roles aer
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT aer.role_id, aer.role_name
                FROM airport_employee_roles aer
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT aer.role_id, aer.role_name
                FROM airport_employee_roles aer
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, AirportEmployeeRole airportEmployeeRole) throws SQLException {
        ps.setString(1, airportEmployeeRole.getName());
    }

    @Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (role_name) VALUES
                (?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, AirportEmployeeRole airportEmployeeRole) throws SQLException {
        ps.setString(1, airportEmployeeRole.getName());
    }

    @Override
    protected AirportEmployeeRole mapRow(ResultSet resultSet) throws SQLException {
        return new AirportEmployeeRole(
                resultSet.getInt("role_id"),
                resultSet.getString("role_name")
        );
    }
}
