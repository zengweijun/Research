package com.nius.graph;

public class Main {
    public static void main(String[] args) {
         testListGraph01();
//        testListGraph02();
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
        // 遍历
        graph.bfs("V1");
    }
}
