package data_structures.graph;

public interface IWeightedGraph {
    void addEdge(int src, int dest, int weight);
    int[] dijkstraShortestPath(int src, int dest);
    int[] classicDijkstraShortestPath(int src, int dest);
    int[] bellManFord(int src, int dest);
    Object[] getMSTusingPrim();
    Object[] getMSTusingPrimDenseGraph();
    Object[] getMSTusingKruskal();
}
