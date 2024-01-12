package org.example;

import org.example.Domain.Graph;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numVertices = 6; // Change this to the desired number of vertices
        Graph graph=Graph.generateRandomGraph(numVertices);
        //Graph graph = Graph.generateHamiltonianGraph(numVertices);
        System.out.println(graph);
        List<Integer> hamiltonianCycle = Parallelization.findHamiltonianCycleParallel(graph);

        if (hamiltonianCycle != null) {
            System.out.println("Hamiltonian Cycle: " + hamiltonianCycle);
        } else {
            System.out.println("No Hamiltonian Cycle found.");
        }
        return;
    }
}