package org.example;

import org.example.Domain.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class HamiltonianCycleTask implements Callable<List<Integer>> {
    private final Graph graph;
    private final int startingVertex;

    public HamiltonianCycleTask(Graph graph, int startingVertex) {
        this.graph = graph;
        this.startingVertex = startingVertex;
    }

    @Override
    public List<Integer> call() {
        List<Integer> path = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        findHamiltonianCycle(startingVertex, path, visited);
        return path.isEmpty() ? null : path;
    }

    private boolean findHamiltonianCycle(int current, List<Integer> path, Set<Integer> visited) {
        path.add(current);
        visited.add(current);

        if (path.size() == graph.vertices) {
            // Hamiltonian cycle found
            return true;
        }

        for (int neighbor : graph.adjList.get(current)) {
            if (!visited.contains(neighbor)) {
                if (findHamiltonianCycle(neighbor, path, visited)) {
                    return true;  // Found a Hamiltonian cycle
                }
            }
        }

        // Backtrack
        path.remove(path.size() - 1);
        visited.remove(current);
        return false;
    }
}
