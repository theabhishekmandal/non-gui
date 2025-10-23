package data_structures.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringJoiner;

public class GraphAdjacencyMatrix implements IGraph {
    // this can be used for weighted graphs.
    // for now we are using 1 to denote the edge
    private final int[][] adjMatrix;
    private final int vertices;
    private final boolean isDirected;

    // Constructor
    public GraphAdjacencyMatrix(int vertices, boolean isDirected) {
        if (vertices <= 0) {
            throw new IllegalArgumentException("Number of vertices must be positive.");
        }
        this.vertices = vertices;
        this.isDirected = isDirected;
        this.adjMatrix = new int[vertices][vertices];
    }

    // Add an edge between source and destination
    public void addEdge(int src, int dest) {
        validateVertex(src);
        validateVertex(dest);

        if (src == dest) {
            System.out.println("Self-loops are not allowed: (" + src + ", " + dest + ")");
            return;
        }

        adjMatrix[src][dest] = 1;

        // For undirected graphs, add the reverse edge too
        if (!isDirected) {
            adjMatrix[dest][src] = 1;
        }
    }

    // Remove an edge (optional)
    public void removeEdge(int src, int dest) {
        validateVertex(src);
        validateVertex(dest);

        adjMatrix[src][dest] = 0;
        if (!isDirected) {
            adjMatrix[dest][src] = 0;
        }
    }

    // Check if an edge exists between src and dest
    public boolean hasEdge(int src, int dest) {
        validateVertex(src);
        validateVertex(dest);

        return adjMatrix[src][dest] == 1;
    }

    // Print the adjacency matrix
    public void printGraph() {
        System.out.println("\nAdjacency Matrix:");
        System.out.print("   ");
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Helper: Validate that vertex index is within range
    private void validateVertex(int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException("Vertex " + v + " is out of range (0 - " + (vertices - 1) + ")");
        }
    }

    @Override
    public void dfs() {
        boolean[] visited = new boolean[this.vertices];
        Deque<Integer> stack = new ArrayDeque<>();
        StringJoiner path = new StringJoiner("--->");

        // find first starting index from which we have to start doing dfs
        int startIndex = getStartIndex();

        if (startIndex == -1) {
            System.out.println("DFS for adjacency Matrix ----> " + path);
            return;
        }


        stack.push(startIndex);
        visited[startIndex] = true;
        path.add(startIndex + "");

        while (!stack.isEmpty()) {
            // can't replace peek with pop otherwise, we will not be able to access other neighbours.
            int vertex = stack.peek();
            int index = -1;
            for (int j = 0; j < this.vertices; j++) {
                if (adjMatrix[vertex][j] != 0 && !visited[j]) {
                    index = j;
                    break;
                }
            }
            if (index == -1) {
                stack.pop();
            } else {
                path.add(index + "");
                stack.push(index);
                visited[index] = true;
            }
        }

        System.out.println("DFS for adjacency Matrix " + path);
    }

    private int getStartIndex() {
        int startIndex = -1;
        for (int i = 0; i < this.vertices; i++) {
            for (int j = 0; j < this.vertices; j++) {
                if (adjMatrix[i][j] != 0) {
                    startIndex = i;
                    break;
                }
            }
            if (startIndex != -1) {
                break;
            }
        }
        return startIndex;
    }

    @Override
    public void bfs() {
        boolean[] visited = new boolean[this.vertices];
        Deque<Integer> queue = new ArrayDeque<>();
        StringJoiner path = new StringJoiner("--->");

        int startIndex = getStartIndex();

        if (startIndex == -1) {
            System.out.println("BFS for adjacency Matrix ----> " + path);
            return;
        }

        queue.offer(startIndex);
        visited[startIndex] = true;
        path.add(startIndex + "");

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int i = 0; i < vertices; i++) {
                if (adjMatrix[vertex][i] != 0 && !visited[i]) {
                    path.add(i + "");
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }

        System.out.println("BFS for adjacency Matrix " + path);
    }
}
