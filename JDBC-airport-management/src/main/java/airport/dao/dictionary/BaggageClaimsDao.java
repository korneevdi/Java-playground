package airport.dao.dictionary;

import airport.dao.AbstractDao;
import airport.entity.dictionary.BaggageClaim;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class BaggageClaimsDao extends AbstractDao<BaggageClaim> {

    private static final String TABLE_NAME = "baggage_claims";
    private static final String ID_NAME = "claim_id";
    private final static List<String> ALL_FIELDS = List.of(
            "claim_number"
    );
    private final static List<String> UNIQUE_FIELDS = ALL_FIELDS;

    public BaggageClaimsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, ALL_FIELDS, UNIQUE_FIELDS);
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
    protected void setInsertStatement(PreparedStatement ps, BaggageClaim baggageClaim) throws SQLException {
        ps.setString(1, baggageClaim.getNumber());
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, BaggageClaim baggageClaim) throws SQLException {
        ps.setString(1, baggageClaim.getNumber());
        ps.setInt(2, baggageClaim.getId());
    }

    @Override
    protected BaggageClaim mapRow(ResultSet resultSet) throws SQLException {
        return new BaggageClaim(
                resultSet.getInt("claim_id"),
                resultSet.getString("claim_number")
        );
    }

    @Override
    public Map<String, String> getColumnToFieldMap() {
        return Map.ofEntries(
                Map.entry("claim_id", "id"),
                Map.entry("claim_number", "number")
        );
    }
}
