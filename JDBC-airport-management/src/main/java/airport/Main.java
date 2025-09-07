package airport;

import airport.database.DbInitializer;

public class Main {
    public static void main(String[] args) {

        // Create and initialize database
        DbInitializer.createDatabase();
        DbInitializer.initializeDatabase();
    }
}
