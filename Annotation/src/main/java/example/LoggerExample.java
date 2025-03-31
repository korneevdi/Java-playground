// A class containing methods some of which are marked by the @Log annotation

package example;

public class LoggerExample {

    // Marked by annotation @Log with default logging level ("INFO")
    @Log
    public void doWork() {
        System.out.println("Working...");
    }

    // Marked by annotation @Log with specified logging level ("WARN")
    @Log(level = "WARN")
    public void anotherMethod() {
        System.out.println("Also working...");
    }

    // Not marked by annotation @Log
    public void oneMoreMethod() {
        System.out.println("Still working...");
    }
}
