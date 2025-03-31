// Annotation to show the logging level of a method

package example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// Each annotation should have two annotations: @Target and @Retention
@Target(ElementType.METHOD)             // Specifies which elements the @Log annotation can be applied to
@Retention(RetentionPolicy.RUNTIME)     // Specifies how long the @Log annotation is available
public @interface Log {

    String level() default "INFO";
}
