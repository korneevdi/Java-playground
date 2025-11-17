package annotation.validation;

public class AnnotationTest {

    public static void main(String[] args) throws IllegalAccessException {
        User user = new User("Ivan", 38, "login", "password");
        AnnotationAnalyzer.analyze(user);
    }
}
