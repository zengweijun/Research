package com.nius.graph;

import java.util.List;
import java.util.Set;

public class Main {
    static Graph.WeightManager<Double> weightManager = new Graph.WeightManager<Double>() {
        public int compare(Double w1, Double w2) {
            return w1.compareTo(w2);
        }
        public Double add(Double w1, Double w2) {
            return w1 + w2;
        }
        public Double zero() {
            return 0.0;
        }
    };

    public static void main(String[] args) {
//         testListGraph01();
//        testListGraph02();
//        testBfs();
//        testDfs();
//        testTopo();
        testMst();
    }

    static void testMst() {
        Graph<Object, Double> graph = undirectedGraph(Data.MST_01);
        Set<Graph.EdgeInfo<Object, Double>> infos = graph.mst();
        for (Graph.EdgeInfo<Object, Double> info : infos) {
            System.out.println(info);
        }
    }

    static void testTopo() {
        Graph<Object, Double> graph = directedGraph(Data.TOPO);
        List<Object> list = graph.topologicalSort();
        System.out.println(list);
    }

    public static void testDfs() {
//        Graph<Object, Double> graph = undirectedGraph(Data.DFS_01);
//        graph.dfs(1);

//        Graph<Object, Double> graph = directedGraph(Data.DFS_02);
//        graph.dfs("a");

        Graph<Object, Double> graph = undirectedGraph(Data.DFS_02);
        graph.dfs("e", (Object o)->{
            System.out.println(o);
            return o.equals("c");
        });
    }

    public static void testBfs() {
//        Graph<Object, Double> graph = undirectedGraph(Data.BFS_01);
//        Graph<Object, Double> graph = directedGraph(Data.BFS_02);
//        Graph<Object, Double> graph = undirectedGraph(Data.BFS_03);
        Graph<Object, Double> graph = directedGraph(Data.BFS_04);
        graph.bfs(5, (Object o)->{
            System.out.println(o);
            return o.equals(7);
        });
    }

    public static void testListGraph02() {
        // 无向图(将有向图改为双向，即可表示无向图)
        ListGraph<String, Integer> graph = new ListGraph();
        graph.addEdge("V0", "V1", 0);
        graph.addEdge("V1", "V0", 0);

        graph.addEdge("V0", "V2", 0);
        graph.addEdge("V2", "V0", 0);

        graph.addEdge("V0", "V3", 0);
        graph.addEdge("V3", "V0", 0);

        graph.addEdge("V1", "V2", 0);
        graph.addEdge("V2", "V1", 0);

        graph.addEdge("V2", "V3", 0);
        graph.addEdge("V3", "V2", 0);

        graph.print();
    }

    public static void testListGraph01() {
        // 有向图
        ListGraph<String, Integer> graph = new ListGraph();
        graph.addEdge("V0", "V4", 6);
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V3", "V4", 1);
        graph.print();
        // 删顶点
//        graph.removeEdge("V0", "V4"); // yes
//        graph.removeEdge("V1", "V4"); // no
        // 删边
//        graph.removeVertex("V1");
//        graph.print();
    }

    /** 有向图 */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /** 无向图 */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }
}
