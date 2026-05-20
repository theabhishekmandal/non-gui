package data_structures.graph;

import java.util.Arrays;

public class WeightedGraphImpl {
    public static void main(String[] args) {

        /*
            0--5->1
            0--2-->1
         */
        IWeightedGraph weightedGraph = new WeightedGraphAdjacencySet(3, true);
        weightedGraph.addEdge(0, 1, 2);
        weightedGraph.addEdge(2, 1, -6);
        weightedGraph.addEdge(0, 2, 5);

        System.out.println(Arrays.toString(weightedGraph.dijkstraShortestPath(0, 1)));
        System.out.println(Arrays.toString(weightedGraph.classicDijkstraShortestPath(0, 1)));
        System.out.println(Arrays.toString(weightedGraph.bellManFord(0, 1)));

        /*
            0--1--1
            1-- -2 -->2
            2-- -2 -->1
         */
        weightedGraph = new WeightedGraphAdjacencySet(3, true);
        weightedGraph.addEdge(0, 1, 1);
        weightedGraph.addEdge(1, 2, -2);
        weightedGraph.addEdge(2, 1, -2);
        System.out.println(Arrays.toString(weightedGraph.bellManFord(0, 1)));


        weightedGraph = new WeightedGraphAdjacencySet(3, false);
        weightedGraph.addEdge(0, 1, 1);
        weightedGraph.addEdge(1, 2, -2);
        weightedGraph.addEdge(2, 1, -2);
        System.out.println(Arrays.deepToString(weightedGraph.getMSTusingPrim()));
        System.out.println(Arrays.deepToString(weightedGraph.getMSTusingPrimDenseGraph()));
        System.out.println(Arrays.deepToString(weightedGraph.getMSTusingKruskal()));
    }
}
