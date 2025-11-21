package exception.factorial;

public class Main {
    public static void main(String[] args) throws FactorialException {
        int number = 7;
        System.out.println(Factorial.getFactorial(number));

        int a = 5;
        int b = -2;
        Factorial.calculate(a, b);
    }
}
