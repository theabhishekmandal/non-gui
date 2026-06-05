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

        /*
            Floyd-Warshall (directed or undirected):
            0--1--1--2
            0--------4--------2
         */
        WeightedGraphAdjacencySet undirectedGraph = new WeightedGraphAdjacencySet(3, false);
        undirectedGraph.addEdge(0, 1, 1);
        undirectedGraph.addEdge(1, 2, 2);
        undirectedGraph.addEdge(0, 2, 4);

        Object[] floydResult = undirectedGraph.allPairShortestPath();
        int[][] distanceMatrix = (int[][]) floydResult[0];
        int[][] nextMatrix = (int[][]) floydResult[1];

        System.out.println(Arrays.deepToString(distanceMatrix));
        System.out.println(Arrays.deepToString(nextMatrix));
        System.out.println(Arrays.toString(
                undirectedGraph.reconstructShortestPath(0, 2, distanceMatrix, nextMatrix)));

        /*
            Articulation points (undirected):
                2
               /
            0--1--3
               \
                4

            Vertex 1 is the articulation point.
         */
        WeightedGraphAdjacencySet apGraph = new WeightedGraphAdjacencySet(5, false);
        apGraph.addEdge(0, 1, 1);
        apGraph.addEdge(1, 2, 1);
        apGraph.addEdge(1, 3, 1);
        apGraph.addEdge(1, 4, 1);
        System.out.println(apGraph.getVertexCutArticulationPoint());
    }
}
