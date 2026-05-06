package data_structures.graph;

public interface IWeightedGraph {
    void addEdge(int src, int dest, int weight);
    int[] dijkstraShortestPath(int src, int dest    );
}
