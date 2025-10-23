package data_structures.graph;

import java.util.*;

/**
 * Represents a Graph data structure and explains two common internal representations:
 * <p>
 * <b>1. Adjacency Matrix:</b><br>
 * - Implemented as a 2D array (matrix) where cell [i][j] holds a non-zero value (often 1 or edge weight)
 *   if there is an edge from vertex i to vertex j.<br>
 * - For an undirected graph, the matrix is symmetric about the diagonal.<br>
 * <br>
 * <b>Advantages:</b><br>
 * - Ideal for dense graphs (where number of edges ≈ V²).<br>
 * - Very fast edge lookup: O(1) time to check if an edge exists between two vertices.<br>
 * - Easy to represent weighted graphs since weights can be stored directly in the matrix.<br>
 * <br>
 * <b>Disadvantages:</b><br>
 * - Consumes O(V²) space, even if few edges exist.<br>
 * - Slower for iterating over neighbors of a vertex since it requires scanning an entire row (O(V)).<br>
 * <br>
 * <b>When to Use:</b><br>
 * - Use Adjacency Matrix when the graph is dense or when frequent edge existence checks are needed.
 * - Commonly used in algorithms like Floyd–Warshall (All-Pairs Shortest Path).
 * <p>
 *
 * <b>2. Adjacency Set (or Weighted Adjacency Map):</b><br>
 * - Each vertex maps to a set (or map) of its neighbors along with edge weights.<br>
 *   Example: {0 → {(1, 4), (2, 6)}, 1 → {(2, 5)}}.<br>
 * - Often implemented as a List of Sets, or a Map of Maps in Java.
 * <br>
 * <b>Advantages:</b><br>
 * - Space-efficient for sparse graphs: total space is O(V + E).<br>
 * - Faster iteration over neighbors of a vertex: proportional to vertex degree.<br>
 * - Naturally supports weighted edges using Map<Integer, Integer> (neighbor → weight).<br>
 * <br>
 * <b>Disadvantages:</b><br>
 * - Edge existence check is slower than matrix (O(degree(V))).<br>
 * - Slightly more complex to implement than adjacency matrix.<br>
 * <br>
 * <b>When to Use:</b><br>
 * - Use Adjacency Set for sparse graphs or when memory efficiency is crucial.
 * - Preferred for most real-world graphs (social networks, web graphs, road maps).
 * <p>
 *
 * <b>Summary:</b><br>
 * - <b>Adjacency Matrix →</b> Fast lookups, high memory usage, great for dense or small graphs.<br>
 * - <b>Adjacency Set →</b> Memory-efficient, ideal for sparse and large-scale graphs.
 */

public class GraphRepresentation {
    public static void main(String[] args) {
        IGraph graph = new GraphAdjacencyMatrix(5, false);
        IGraph graphAdjacencySet = new GraphAdjacencySet(5, false);

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int firstVertex = random.nextInt(5);
            int secondVertex = random.nextInt(5);
            graph.addEdge(firstVertex, secondVertex);
            graphAdjacencySet.addEdge(firstVertex, secondVertex);
        }

        System.out.println();
        graph.printGraph();
        graph.dfs();
        graph.bfs();

        System.out.println();
        graphAdjacencySet.printGraph();
        graphAdjacencySet.dfs();
        graphAdjacencySet.bfs();


    }
}

