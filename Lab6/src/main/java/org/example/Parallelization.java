package org.example;

import org.example.Domain.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Parallelization {
    public static List<Integer> findHamiltonianCycleParallel(Graph graph) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try {
            List<Future<List<Integer>>> futures = new ArrayList<>();
            for (int i = 0; i < graph.vertices; i++) {
                Callable<List<Integer>> task = new HamiltonianCycleTask(graph, i);
                futures.add(executorService.submit(task));
            }

            for (Future<List<Integer>> future : futures) {
                List<Integer> result = future.get();
                if (result != null) {
                    return result;  // Hamiltonian cycle found
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        return null;  // No Hamiltonian cycle found
    }
}
