package data_structures.graph;

public interface IGraph {
    void addEdge(int src, int dest);
    void removeEdge(int src, int dest);
    boolean hasEdge(int src, int dest);
    void printGraph();
    void dfs();
    void bfs();
}
