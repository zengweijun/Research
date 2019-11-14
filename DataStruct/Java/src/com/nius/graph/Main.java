package com.nius.graph;

public class Main {
    public static void main(String[] args) {
        ListGraph<String, Integer> graph = new ListGraph();
        graph.addEdge("V0", "V4", 6);
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V3", "V4", 1);

        graph.addVertex("V10");
        graph.addVertex("V12");
        graph.addEdge("V10", "V12", 20);
        graph.print();
    }
}
