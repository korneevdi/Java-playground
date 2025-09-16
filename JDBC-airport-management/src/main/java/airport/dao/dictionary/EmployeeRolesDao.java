package airport.dao.dictionary;

import airport.entity.dictionary.CrewRole;
import airport.entity.dictionary.EmployeeRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRolesDao extends AbstractDictionaryDao<EmployeeRole> {

    private static final String TABLE_NAME = "airport_employee_roles";
    private static final String ID_COLUMN = "role_id";
    private static final String NAME_COLUMN = "role_name";

    public EmployeeRolesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_COLUMN, NAME_COLUMN);
    }

    @Override
    protected EmployeeRole mapRow(ResultSet resultSet) throws SQLException {
        return new EmployeeRole(
                resultSet.getInt(ID_COLUMN),
                resultSet.getString(NAME_COLUMN)
        );
    }
}
