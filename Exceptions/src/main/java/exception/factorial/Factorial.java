package exception.factorial;

public class Factorial {

    public static int getFactorial(int number) throws FactorialException {
        if(number == 0) {
            return 1;
        }

        if(number > 0) {
            int result = 1;
            for(int i = 1; i <= number; i++) {
                result *= i;
            }
            return result;
        } else {
            throw new FactorialException("Number should be non-negative. You entered: " + number, number);
        }
    }

    public static void calculate(int a, int b) {
        try{
            System.out.println((float) getFactorial(a) / getFactorial(b));
        } catch (FactorialException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid input: " + e.getNumber());
        }
    }
}
