package airport.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static final Properties PROPERTIES = new Properties();

    // Prohibit to create objects of this class from outside,
    // since this is a utility class
    private PropertiesReader() {
    }

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream inputStream = PropertiesReader.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getValue(String key) {
        return PROPERTIES.getProperty(key);
    }
}
