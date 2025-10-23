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
        boolean[] visited = new boolean[this.vertices];
        Deque<Integer> stack = new ArrayDeque<>();
        StringJoiner path = new StringJoiner("--->");

        int startIndex = getStartIndex();

        if (startIndex == -1) {
            System.out.println("DFS for adjacency Set ----> " + path);
            return;
        }

        stack.push(startIndex);
        visited[startIndex] = true;
        path.add(startIndex + "");

        while (!stack.isEmpty()) {
            int vertex = stack.peek();
            int index = -1;
            for (int j : adjSet.get(vertex)) {
                if (!visited[j]) {
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

        System.out.println("DFS for adjacency Set " + path);
    }

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
        boolean[] visited = new boolean[this.vertices];
        Deque<Integer> queue = new ArrayDeque<>();
        StringJoiner path = new StringJoiner("--->");

        int startIndex = getStartIndex();

        if (startIndex == -1) {
            System.out.println("BFS for adjacency Set ----> " + path);
            return;
        }

        queue.offer(startIndex);
        visited[startIndex] = true;
        path.add(startIndex + "");

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int j : adjSet.get(vertex)) {
                if (!visited[j]) {
                    path.add(j + "");
                    queue.offer(j);
                    visited[j] = true;
                }
            }
        }

        System.out.println("BFS for adjacency Set " + path);
    }
}
