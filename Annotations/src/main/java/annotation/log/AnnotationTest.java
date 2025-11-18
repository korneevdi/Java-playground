package annotation.log;

import java.lang.reflect.InvocationTargetException;

public class AnnotationTest {

    public static void main(String[] args)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AnnotationAnalyzer.analyze(CollectionCreator.class);
    }
}
