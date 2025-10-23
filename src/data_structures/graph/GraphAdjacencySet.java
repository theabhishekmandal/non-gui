package data_structures.graph;

import java.util.*;

public class GraphAdjacencySet implements IGraph {
    private final Map<Integer, Set<Integer>> adjSet;
    private final int vertices;
    private final boolean isDirected;

    // Constructor
    public GraphAdjacencySet(int vertices, boolean isDirected) {
        if (vertices <= 0) {
            throw new IllegalArgumentException("Number of vertices must be positive.");
        }

        this.vertices = vertices;
        this.isDirected = isDirected;
        this.adjSet = new HashMap<>();

        // Initialize each vertex with an empty set
        for (int i = 0; i < vertices; i++) {
            adjSet.put(i, new HashSet<>());
        }
    }

    // Add edge
    public void addEdge(int src, int dest) {
        validateVertex(src);
        validateVertex(dest);

        if (src == dest) {
            System.out.println("Self-loops are not allowed: (" + src + ", " + dest + ")");
            return;
        }

        adjSet.get(src).add(dest);

        // For undirected graphs, add the reverse edge too
        if (!isDirected) {
            adjSet.get(dest).add(src);
        }
    }

    // Remove edge
    public void removeEdge(int src, int dest) {
        validateVertex(src);
        validateVertex(dest);

        adjSet.get(src).remove(dest);
        if (!isDirected) {
            adjSet.get(dest).remove(src);
        }
    }

    // Check if an edge exists
    public boolean hasEdge(int src, int dest) {
        validateVertex(src);
        validateVertex(dest);

        return adjSet.get(src).contains(dest);
    }

    // Print adjacency sets
    public void printGraph() {
        System.out.println("\nAdjacency Set Representation:");
        for (int vertex = 0; vertex < vertices; vertex++) {
            System.out.print(vertex + " -> ");
            System.out.println(adjSet.get(vertex));
        }
    }

    // Helper method to validate vertex
    private void validateVertex(int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException("Vertex " + v + " is out of range (0 - " + (vertices - 1) + ")");
        }
    }

    @Override
    public void dfs() {
        // Keeps track of whether a vertex has been visited
        boolean[] visited = new boolean[this.vertices];

        // Stack is used to simulate recursion in DFS (LIFO order)
        Deque<Integer> stack = new ArrayDeque<>();

        // Used to store traversal order
        StringJoiner path = new StringJoiner("--->");

        // Get the first vertex that has at least one outgoing edge
        int startIndex = getStartIndex();

        // If the graph is empty or has no edges, stop
        if (startIndex == -1) {
            System.out.println("DFS for adjacency Set ----> " + path);
            return;
        }

        // Step 1: Push the starting vertex and mark as visited
        stack.push(startIndex);
        visited[startIndex] = true;
        path.add(String.valueOf(startIndex));

        // Step 2: Continue exploring until the stack is empty
        while (!stack.isEmpty()) {
            // Look at the top vertex (don’t remove yet)
            int vertex = stack.peek();
            int index = -1;

            // Step 3: Find an unvisited neighbor
            for (int neighbor : adjSet.get(vertex)) {
                if (!visited[neighbor]) {
                    index = neighbor; // Found one neighbor to go deeper
                    break;
                }
            }

            // Step 4: If no unvisited neighbor → backtrack (pop stack)
            if (index == -1) {
                stack.pop();
            }
            // Step 5: Otherwise, visit the neighbor and continue deeper
            else {
                path.add(String.valueOf(index));
                stack.push(index);
                visited[index] = true;
            }
        }

        System.out.println("DFS for adjacency Set ----> " + path);
    }

    /**
     * Utility method to find the first vertex that has outgoing edges.
     * Used to determine where traversal starts.
     */
    private int getStartIndex() {
        int startIndex = -1;
        for (int vertex : this.adjSet.keySet()) {
            if (!adjSet.get(vertex).isEmpty()) {
                startIndex = vertex;
                break;
            }
        }
        return startIndex;
    }

    @Override
    public void bfs() {
        // Keeps track of whether each vertex has been visited
        boolean[] visited = new boolean[this.vertices];

        // Queue is used in BFS for level-order traversal (FIFO order)
        Deque<Integer> queue = new ArrayDeque<>();

        // Used to store traversal order
        StringJoiner path = new StringJoiner("--->");

        // Get the first vertex with outgoing edges to start BFS
        int startIndex = getStartIndex();

        // If graph is empty or has no edges
        if (startIndex == -1) {
            System.out.println("BFS for adjacency Set ----> " + path);
            return;
        }

        // Step 1: Enqueue starting vertex and mark as visited
        queue.offer(startIndex);
        visited[startIndex] = true;
        path.add(String.valueOf(startIndex));

        // Step 2: Continue exploring until queue is empty
        while (!queue.isEmpty()) {
            // Dequeue the front vertex
            int vertex = queue.poll();

            // Step 3: Visit all unvisited neighbors
            for (int neighbor : adjSet.get(vertex)) {
                if (!visited[neighbor]) {
                    path.add(String.valueOf(neighbor));
                    queue.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        System.out.println("BFS for adjacency Set ----> " + path);
    }
}
