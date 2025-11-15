package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ReflectionTest {
    public static void main(String[] args)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        User user = new User("Ivan", 29); // create an object of any class
        Class<User> userClass = User.class; // get access to the User class via reflection

        // We can find any info about the class
        System.out.println(Arrays.toString(userClass.getDeclaredFields()));

        // We can find any info about the class fields
        Field field = userClass.getDeclaredField("password");
        field.setAccessible(true); // if the field is private
        System.out.println(field.get(user));

        // We can find any info about the class methods
        Method method = userClass.getDeclaredMethod("purchase");
        System.out.println(Arrays.toString(method.getDeclaredAnnotations()));

        int modifier = method.getModifiers();
        boolean isFinal = Modifier.isVolatile(modifier);
        System.out.println(isFinal);

        method.setAccessible(true);
        method.invoke(user);
    }
}
