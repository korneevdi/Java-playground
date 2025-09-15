package airport.dao.dictionary;

import airport.entity.dictionary.PersonSex;

import java.sql.*;

public class PersonSexDao extends AbstractDictionaryDao<PersonSex> {

    private static final String TABLE_NAME = "person_sex";
    private static final String ID_COLUMN = "sex_id";
    private static final String NAME_COLUMN = "sex_name";

    public PersonSexDao(Connection connection) {
        super(connection, TABLE_NAME, ID_COLUMN, NAME_COLUMN);
    }

    @Override
    protected PersonSex mapRow(ResultSet resultSet) throws SQLException {
        return new PersonSex(
                resultSet.getInt(ID_COLUMN),
                resultSet.getString(NAME_COLUMN)
        );
    }
}
