package example;

import java.lang.reflect.Method;

public class AnnotationProcessor {

    public static void processorLogging(Class<?> someClass) {
        for(Method method : someClass.getDeclaredMethods()) {
            Log log = method.getAnnotation(Log.class);
            if(log != null) {
                System.out.println(
                        "Method: " + method.getName() +
                        ", level: " + log.level());
            }
        }
    }
}
