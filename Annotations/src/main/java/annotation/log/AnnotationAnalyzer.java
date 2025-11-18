package annotation.log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationAnalyzer {

    public static void analyze(Class<?> clazz)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object instance = clazz.getDeclaredConstructor().newInstance();

        for(Method m : clazz.getDeclaredMethods()) {
            if(m.isAnnotationPresent(LogExecution.class)) {
                m.setAccessible(true);

                long start = System.currentTimeMillis();
                m.invoke(instance);
                long finish = System.currentTimeMillis();

                System.out.println("Method " + m + " completed successfully\nDuration: " + (finish - start) + " ms");
            }
        }
    }
}
