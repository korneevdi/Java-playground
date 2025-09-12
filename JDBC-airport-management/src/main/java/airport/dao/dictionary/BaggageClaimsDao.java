package airport.dao.dictionary;

import airport.entity.BaggageClaim;

import java.sql.*;

public class BaggageClaimsDao extends AbstractDictionaryDao<BaggageClaim> {

    private static final String TABLE_NAME = "baggage_claims";
    private static final String ID_COLUMN = "claim_id";
    private static final String NAME_COLUMN = "claim_name";

    public BaggageClaimsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_COLUMN, NAME_COLUMN);
    }

    @Override
    protected BaggageClaim mapRow(ResultSet resultSet) throws SQLException {
        return new BaggageClaim(
                resultSet.getInt(ID_COLUMN),
                resultSet.getString(NAME_COLUMN)
        );
    }
}
