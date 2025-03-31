package benchmark;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 1000; i++) {
            list.add(i);
        }

        Operations operations = new Operations();

        try{
            BenchmarkProcessor.runBenchmark(operations, list);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
