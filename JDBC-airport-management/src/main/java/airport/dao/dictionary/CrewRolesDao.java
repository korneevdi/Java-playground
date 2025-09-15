package airport.dao.dictionary;

import airport.entity.dictionary.CrewRole;

import java.sql.*;

public class CrewRolesDao extends AbstractDictionaryDao<CrewRole> {

    private static final String TABLE_NAME = "crew_roles";
    private static final String ID_COLUMN = "role_id";
    private static final String NAME_COLUMN = "role_name";

    public CrewRolesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_COLUMN, NAME_COLUMN);
    }

    @Override
    protected CrewRole mapRow(ResultSet resultSet) throws SQLException {
        return new CrewRole(
                resultSet.getInt(ID_COLUMN),
                resultSet.getString(NAME_COLUMN)
        );
    }
}
