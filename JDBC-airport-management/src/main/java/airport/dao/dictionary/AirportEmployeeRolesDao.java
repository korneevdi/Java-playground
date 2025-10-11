package airport.dao.dictionary;

import airport.entity.dictionary.AirportEmployeeRole;

import java.sql.*;

public class AirportEmployeeRolesDao extends AbstractDictionaryDao<AirportEmployeeRole> {

    private static final String TABLE_NAME = "airport_employee_roles";
    private static final String ID_NAME = "role_id";

    public AirportEmployeeRolesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
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
    protected AirportEmployeeRole mapRow(ResultSet resultSet) throws SQLException {
        return new AirportEmployeeRole(
                resultSet.getInt("role_id"),
                resultSet.getString("role_name")
        );
    }
}
