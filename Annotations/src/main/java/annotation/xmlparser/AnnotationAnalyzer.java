package annotation.xmlparser;

import java.lang.reflect.Method;

public class AnnotationAnalyzer {

    public void analyze(Class<?> clazz) {
        Method[] methods = clazz.getMethods();

        for(Method m : methods) {
            if(m.isAnnotationPresent(XmlElement.class)) {
                m.setAccessible(true);
                XmlElement annotation = m.getAnnotation(XmlElement.class);
                System.out.println(
                        "Method " + m + " has annotation " + annotation + " with name " + annotation.name()
                );
            }
        }

        for(Method m : methods) {
            if(m.isAnnotationPresent(XmlAttribute.class)) {
                m.setAccessible(true);
                XmlAttribute annotation = m.getAnnotation(XmlAttribute.class);
                System.out.println(
                        "Method " + m + " has annotation " + annotation + " with name " + annotation.name()
                );
            }
        }
    }
}
