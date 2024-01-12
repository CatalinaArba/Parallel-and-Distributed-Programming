package org.example.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
    public int vertices;
    public List<List<Integer>> adjList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    void addEdge(int source, int destination) {
        adjList.get(source).add(destination);
    }

    public static Graph generateRandomGraph(int vertices) {
        Graph graph = new Graph(vertices);
        Random random = new Random();

        for (int i = 0; i < vertices; i++) {
            int neighbor = random.nextInt(vertices);
            graph.addEdge(i, neighbor);
        }
        return graph;
    }

    public static Graph generateHamiltonianGraph(int vertices) {
        Graph graph = new Graph(vertices);
        List<Integer> unvisited = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            unvisited.add(i);
        }

        int current = 0;
        unvisited.remove(Integer.valueOf(current));

        while (!unvisited.isEmpty()) {
            int next = unvisited.remove(0);
            graph.addEdge(current, next);
            current = next;
        }

        graph.addEdge(current, 0); // Closing the Hamiltonian cycle
        return graph;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph with ").append(vertices).append(" vertices:\n");

        for (int i = 0; i < vertices; i++) {
            sb.append(i).append(" -> ").append(adjList.get(i)).append("\n");
        }

        return sb.toString();
    }

    public Graph get(){
        return this;
    }

}
