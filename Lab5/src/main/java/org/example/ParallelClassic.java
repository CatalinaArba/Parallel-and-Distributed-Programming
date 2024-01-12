package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelClassic {
    private static final int NR_THREADS = 4;

    public static Polynomial multiply(Polynomial p1, Polynomial p2) throws InterruptedException {

        int sizeOfResultCoefficientList = p1.getDegree() + p2.getDegree() + 1;

        List<Integer> coefficients = new ArrayList<>();
        for (int i = 0; i < sizeOfResultCoefficientList; i++) {
            coefficients.add(0);
        }
        Polynomial result = new Polynomial(coefficients);

        ExecutorService executor = Executors.newFixedThreadPool(NR_THREADS);

        int nrOfTasks = Math.max(1, sizeOfResultCoefficientList / NR_THREADS);

        for (int i = 0; i < sizeOfResultCoefficientList; i += nrOfTasks) {
            int end = Math.min(i + nrOfTasks, sizeOfResultCoefficientList);
            executor.execute(new Task(i, end, p1, p2, result));
        }

        executor.shutdown();
        executor.awaitTermination(50, TimeUnit.SECONDS);

        return result;

    }
}
