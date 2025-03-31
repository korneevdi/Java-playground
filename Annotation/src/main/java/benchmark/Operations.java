package benchmark;

import java.util.List;

public class Operations {

    @Benchmark
    public void fastMethod(List<Integer> list) {
        int sum = 0;
        for(int i : list) {
            sum += i;
        }
        System.out.println("Sum: " + sum);
    }

    @Benchmark
    public void slowMethod(List<Integer> list) throws InterruptedException {
        int sum = 0;
        try{
            for(int i : list) {
                Thread.sleep(3);
                sum += i;
            }
        } catch (InterruptedException e) {
            throw new InterruptedException(e.getMessage());
        }
        System.out.println("Sum: " + sum);
    }
}
