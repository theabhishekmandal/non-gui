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
        // Keeps track of whether each vertex has been visited
        boolean[] visited = new boolean[this.vertices];

        // Stack is used to simulate recursion (LIFO structure)
        Deque<Integer> stack = new ArrayDeque<>();

        // Used to store traversal order in a readable format
        StringJoiner path = new StringJoiner("--->");

        // Find the first vertex with at least one outgoing edge (start point)
        int startIndex = getStartIndex();

        // If graph has no edges or vertices, stop early
        if (startIndex == -1) {
            System.out.println("DFS for adjacency Matrix ----> " + path);
            return;
        }

        // Step 1: Push the start vertex to the stack and mark it visited
        stack.push(startIndex);
        visited[startIndex] = true;
        path.add(String.valueOf(startIndex));

        // Step 2: Continue until all reachable vertices are explored
        while (!stack.isEmpty()) {

            // Use peek() instead of pop() → we still need this vertex
            // to check other unvisited neighbors before removing it
            int vertex = stack.peek();
            int index = -1;

            // Step 3: Find the first unvisited neighbor of the current vertex
            for (int j = 0; j < this.vertices; j++) {
                // Check for an edge and if neighbor is not yet visited
                if (adjMatrix[vertex][j] != 0 && !visited[j]) {
                    index = j; // Found an unvisited neighbor
                    break;
                }
            }

            // Step 4: If no unvisited neighbor found → backtrack (pop from stack)
            if (index == -1) {
                stack.pop();
            }
            // Step 5: Otherwise, visit the neighbor and go deeper
            else {
                path.add(String.valueOf(index));
                stack.push(index);
                visited[index] = true;
            }
        }

        System.out.println("DFS for adjacency Matrix ----> " + path);
    }

    /**
     * Utility method to find the first vertex that has an outgoing edge.
     * Used as the starting point for traversal.
     */
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
        // Keeps track of whether each vertex has been visited
        boolean[] visited = new boolean[this.vertices];

        // Queue is used for level-order traversal (FIFO structure)
        Deque<Integer> queue = new ArrayDeque<>();

        // Used to store traversal order
        StringJoiner path = new StringJoiner("--->");

        // Find the first vertex with outgoing edges to start BFS
        int startIndex = getStartIndex();

        // If graph is empty, stop early
        if (startIndex == -1) {
            System.out.println("BFS for adjacency Matrix ----> " + path);
            return;
        }

        // Step 1: Enqueue start vertex and mark it visited
        queue.offer(startIndex);
        visited[startIndex] = true;
        path.add(String.valueOf(startIndex));

        // Step 2: Continue until all reachable vertices are explored
        while (!queue.isEmpty()) {
            // Remove vertex from front of queue
            int vertex = queue.poll();

            // Step 3: Visit all unvisited neighbors of this vertex
            for (int i = 0; i < vertices; i++) {
                if (adjMatrix[vertex][i] != 0 && !visited[i]) {
                    path.add(String.valueOf(i));
                    queue.offer(i);
                    visited[i] = true; // Mark as visited when enqueued
                }
            }
        }

        System.out.println("BFS for adjacency Matrix ----> " + path);
    }
}
