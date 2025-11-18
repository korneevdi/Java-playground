package annotation.xmlparser;

public class AnnotationTest {

    public static void main(String[] args) {
        AnnotationAnalyzer analyzer = new AnnotationAnalyzer();
        analyzer.analyze(Book.class);
    }
}
