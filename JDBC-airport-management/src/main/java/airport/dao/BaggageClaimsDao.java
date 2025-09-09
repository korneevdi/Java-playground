package airport.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaggageClaimsDao {

    private Connection connection;

    public BaggageClaimsDao(Connection connection) {
        this.connection = connection;
    }

    // Show the list of baggage claims
    public List<String> findAll() {
        String sql =
                """
                SELECT claim_name FROM baggage_claims
                """;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> allClaims = new ArrayList<>();
            while (resultSet.next()) {
                String claim = resultSet.getString("claim_name");
                allClaims.add(claim);
            }
            return allClaims;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find baggage claim by id
    public String findById(int id) {
        String sql =
                """
                SELECT claim_name FROM baggage_claims
                WHERE claim_id = %s
                """.formatted(id);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("claim_name");
            } else {
                return "";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find id by claim_name
    public int findId(String claim) {
        String sql =
                """
                SELECT claim_id FROM baggage_claims
                WHERE claim_name = '%s'
                """.formatted(claim);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("claim_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add new baggage claim
    public void insert(String newClaim) {
        String sql =
                """
                INSERT INTO baggage_claims (claim_name) VALUES
                (?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newClaim);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update baggage claim
    public void update(int id, String newClaim) {
        String sql =
                """
                UPDATE baggage_claims
                SET claim_name = ?
                WHERE claim_id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newClaim);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete baggage claim
    public boolean delete(String claim) {
        String sql =
                """
                DELETE FROM baggage_claims
                WHERE claim_name = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, claim);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
