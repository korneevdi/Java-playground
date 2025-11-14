package benchmark;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class BenchmarkProcessor {
    public static void runBenchmark(Object object, List<Integer> list) throws Exception {
        Class<?> classs = object.getClass();
        for(Method method : classs.getDeclaredMethods()) {
            if(method.isAnnotationPresent(Benchmark.class)) {
                try{
                    long startTime = System.currentTimeMillis();
                    method.invoke(object, list); // Run method with argument
                    long finishTime = System.currentTimeMillis();
                    System.out.println("Method run time: " + (finishTime - startTime) + " ms");
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new IllegalAccessException(e.getMessage());
                }
            }
        }
    }
}
