package annotation.validation;

import java.lang.reflect.Field;

public class AnnotationAnalyzer {

    public static void analyze(Object object) throws IllegalAccessException {
        // Get class of the object via reflection
        Class<?> clazz = object.getClass();

        // Get fields of the class
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ValidateLength.class)) {
                field.setAccessible(true);
                Object value = field.get(object);
                if (value instanceof String string) {
                    int length = string.length();
                    ValidateLength annotation = field.getAnnotation(ValidateLength.class);
                    if (length < annotation.min() || length > annotation.max()) {
                        throw new IllegalArgumentException(
                                "Field " + field + " has invalid length: "
                                        + "length must be between " + annotation.min() + " and " + annotation.max()
                                        + " (actual length: " + length + ")");
                    }
                }
            }
        }
    }
}
