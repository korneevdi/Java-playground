package airport.dao.contact;

import airport.entity.contact.EmergencyContact;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

public class EmergencyContactsDao extends AbstractContactDao<EmergencyContact> {

    private final static String TABLE_NAME = "emergency_contacts";
    private final static String ID_NAME = "contact_id";
    private final static List<String> UNIQUE_FIELDS = List.of(
            "contact_name",
            "contact_relation",
            "contact_phone"
    );

    public EmergencyContactsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT ec.contact_id, ec.contact_name, ec.relation, ec.contact_phone
                FROM emergency_contacts ec
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT ec.contact_id, ec.contact_name, ec.relation, ec.contact_phone
                FROM emergency_contacts ec
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT ec.contact_id, ec.contact_name, ec.relation, ec.contact_phone
                FROM emergency_contacts ec
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected String buildExistsSql() {
        String whereClause = UNIQUE_FIELDS.stream()
                .map(f -> f + " = ?")
                .collect(Collectors.joining(" AND "));

        return "SELECT 1 FROM " + TABLE_NAME + " WHERE " + whereClause;
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, EmergencyContact emergencyContact) throws SQLException {
        ps.setString(1, emergencyContact.getContactName());
        ps.setString(2, emergencyContact.getRelation());
        ps.setString(3, emergencyContact.getPhone());
    }

    @Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (contact_name, contact_relation, contact_phone) VALUES
                (?, ?, ?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, EmergencyContact contact) throws SQLException {
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getRelation());
        ps.setString(3, contact.getPhone());
    }

    @Override
    protected String buildUpdateSql() {
        return """
                UPDATE %s
                SET contact_name = ?, contact_relation = ?, contact_phone = ?
                WHERE %s = ?
                """.formatted(TABLE_NAME, ID_NAME);
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, EmergencyContact contact) throws SQLException {
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getRelation());
        ps.setString(3, contact.getPhone());
        ps.setInt(4, contact.getId());
    }

    @Override
    protected EmergencyContact mapRow(ResultSet resultSet) throws SQLException {
        return new EmergencyContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("contact_name"),
                resultSet.getString("relation"),
                resultSet.getString("contact_phone")
        );
    }
}
