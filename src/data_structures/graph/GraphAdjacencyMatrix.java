package data_structures.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
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

        // don't add edge b->a, if a->b exits if it is directed graph.
        if (isDirected && hasEdge(dest, src)) {
            System.out.println("Opposite directed edge already exists: " +
                    "(" + dest + ", " + src + "); cannot add (" + src + ", " + dest + ")");
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

        return adjMatrix[src][dest] != 0;
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

        // Start a DFS from every unvisited vertex so isolates and other components are included
        for (int s = 0; s < vertices; s++) {
            if (visited[s]) {
                continue;
            }
            stack.push(s);
            visited[s] = true;
            path.add(String.valueOf(s));

            while (!stack.isEmpty()) {
                int vertex = stack.peek();
                int index = -1;
                for (int j = 0; j < vertices; j++) {
                    if (adjMatrix[vertex][j] != 0 && !visited[j]) {
                        index = j;
                        break;
                    }
                }
                if (index == -1) {
                    stack.pop();
                } else {
                    path.add(String.valueOf(index));
                    stack.push(index);
                    visited[index] = true;
                }
            }
        }

        System.out.println("DFS for adjacency Matrix ----> " + path);
    }

    @Override
    public void bfs() {
        boolean[] visited = new boolean[this.vertices];
        Deque<Integer> queue = new ArrayDeque<>();
        StringJoiner path = new StringJoiner("--->");

        // Start BFS from every unvisited vertex so isolates and other components are included
        for (int s = 0; s < vertices; s++) {
            if (visited[s]) {
                continue;
            }
            queue.offer(s);
            visited[s] = true;
            path.add(String.valueOf(s));

            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                for (int i = 0; i < vertices; i++) {
                    if (adjMatrix[vertex][i] != 0 && !visited[i]) {
                        path.add(String.valueOf(i));
                        queue.offer(i);
                        visited[i] = true;
                    }
                }
            }
        }

        System.out.println("BFS for adjacency Matrix ----> " + path);
    }

    @Override
    public List<Integer> getBfsTopologicalOrder() {
        // DFS/BFS traversal: "Just visit everything"
        // Topological sort: "Visit in a way that prerequisites come first"
        if (!isDirected) {
            return List.of();
        }

        int[] indegree = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (adjMatrix[i][j] != 0) {
                    indegree[j]++;
                }
            }
        }

        // if there is a cycle then indegree will not be zero and hence topological order will be empty.
        Deque<Integer> queue = new ArrayDeque<>();
        for (int v = 0; v < vertices; v++) {
            if (indegree[v] == 0) {
                queue.offer(v);
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.add(u);
            for (int v = 0; v < vertices; v++) {
                if (adjMatrix[u][v] != 0) {
                    indegree[v]--;
                    if (indegree[v] == 0) {
                        queue.offer(v);
                    }
                }
            }
        }

        // Fewer than V removals means a directed cycle (or inconsistent state)
        if (order.size() != vertices) {
            return List.of();
        }
        return List.copyOf(order);
    }

    @Override
    public List<Integer> getDfsTopologicalOrder() {
        // DFS/BFS traversal: "Just visit everything"
        // Topological sort: "Visit in a way that prerequisites come first"
        if (!isDirected) {
            return List.of();
        }

        // 0 = unvisited, 1 = on DFS stack (back-edge => cycle), 2 = finished
        int[] state = new int[vertices];
        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> postOrder = new ArrayList<>();

        for (int s = 0; s < vertices; s++) {
            if (state[s] != 0) {
                continue;
            }
            state[s] = 1;
            stack.push(s);
            while (!stack.isEmpty()) {
                int u = stack.peek();
                int next = -1;
                for (int v = 0; v < vertices; v++) {
                    if (adjMatrix[u][v] == 0) {
                        continue;
                    }
                    // if v is already on the dfs stack,
                    if (state[v] == 1) {
                        return List.of();
                    }
                    if (state[v] == 0) {
                        next = v;
                        break;
                    }
                }
                if (next == -1) {
                    stack.pop();
                    state[u] = 2;
                    postOrder.add(u);
                } else {
                    state[next] = 1;
                    stack.push(next);
                }
            }
        }

        Collections.reverse(postOrder);
        return List.copyOf(postOrder);
    }
}
