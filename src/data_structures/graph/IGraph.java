package data_structures.graph;

import java.util.List;

public interface IGraph {
    void addEdge(int src, int dest);
    void removeEdge(int src, int dest);
    boolean hasEdge(int src, int dest);
    void printGraph();
    void dfs();
    void bfs();
    List<Integer> getBfsTopologicalOrder();
    List<Integer> getDfsTopologicalOrder();
}
