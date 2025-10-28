package airport.dao.dictionary;

import airport.entity.dictionary.BaggageClaim;

import java.sql.*;
import java.util.List;

public class BaggageClaimsDao extends AbstractDictionaryDao<BaggageClaim> {

    private static final String TABLE_NAME = "baggage_claims";
    private static final String ID_NAME = "claim_id";
    private final static List<String> UNIQUE_FIELDS = List.of(
            "claim_number"
    );

    public BaggageClaimsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT bc.claim_id, bc.claim_number
                FROM baggage_claims bc
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT bc.claim_id, bc.claim_number
                FROM baggage_claims bc
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT bc.claim_id, bc.claim_number
                FROM baggage_claims bc
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, BaggageClaim baggageClaim) throws SQLException {
        ps.setString(1, baggageClaim.getNumber());
    }

    @Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (claim_number) VALUES
                (?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, BaggageClaim baggageClaim) throws SQLException {
        ps.setString(1, baggageClaim.getNumber());
    }

    @Override
    protected BaggageClaim mapRow(ResultSet resultSet) throws SQLException {
        return new BaggageClaim(
                resultSet.getInt("claim_id"),
                resultSet.getString("claim_number")
        );
    }
}
