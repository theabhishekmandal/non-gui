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

    // Add vertex
    public void addEdge(int src, int dest) {
        validateVertex(src);
        validateVertex(dest);

        if (src == dest) {
            System.out.println("Self-loops are not allowed: (" + src + ", " + dest + ")");
            return;
        }

        if (isDirected && hasEdge(dest, src)) {
            System.out.println("Opposite directed vertex already exists: (" + dest + ", " + src + ");" +
                    " cannot add (" + src + ", " + dest + ")");
            return;
        }

        adjSet.get(src).add(dest);

        // For undirected graphs, add the reverse vertex too
        if (!isDirected) {
            adjSet.get(dest).add(src);
        }
    }

    // Remove vertex
    public void removeEdge(int src, int dest) {
        validateVertex(src);
        validateVertex(dest);

        adjSet.get(src).remove(dest);
        if (!isDirected) {
            adjSet.get(dest).remove(src);
        }
    }

    // Check if an vertex exists
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
        boolean[] visited = new boolean[vertices];
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
                int next = -1;
                for (int v = 0; v < vertices; v++) {
                    if (adjSet.get(vertex).contains(v) && !visited[v]) {
                        next = v;
                        break;
                    }
                }
                if (next == -1) {
                    stack.pop();
                } else {
                    path.add(String.valueOf(next));
                    stack.push(next);
                    visited[next] = true;
                }
            }
        }

        System.out.println("DFS for adjacency Set ----> " + path);
    }

    @Override
    public void bfs() {
        boolean[] visited = new boolean[vertices];
        Deque<Integer> queue = new ArrayDeque<>();
        StringJoiner path = new StringJoiner("--->");

        for (int s = 0; s < vertices; s++) {
            if (visited[s]) {
                continue;
            }
            queue.offer(s);
            visited[s] = true;
            path.add(String.valueOf(s));

            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                for (int v = 0; v < vertices; v++) {
                    if (adjSet.get(vertex).contains(v) && !visited[v]) {
                        path.add(String.valueOf(v));
                        queue.offer(v);
                        visited[v] = true;
                    }
                }
            }
        }

        System.out.println("BFS for adjacency Set ----> " + path);
    }

    @Override
    public List<Integer> getBfsTopologicalOrder() {
        /*
            topological sort does not work if graph is not directed.
            Perform cycle detection in that case return empty list.
            It should also work for disconnected components.

            For Bfs we have to compute indegree of each node i.e how many incoming edges are there in the node.
            if indegree of a node is 0 in that case we can have BFS based Topological order.
            subtract indegree of each node which is connected to the current node.

            If indegree is zero add it to queue and process it.

            Here directed cycle is detected with two cases
            -   There can be cycle in every node, such that indegree of any node will not be zero. In that case
                no iteration will be performed and we will return empty list.

            -   There can be cycle in one of the node, in that case total vertices should be equal to list length.

            -   It automatically works for disconnected graph because we are calculating indegree for every node.

         */
        if (!isDirected) {
            return List.of();
        }
        int[] indegree = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j : adjSet.get(i)) {
                indegree[j]++;
            }
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < vertices; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> answer = new ArrayList<>();
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            answer.add(vertex);
            for (int j : adjSet.get(vertex)) {
                indegree[j]--;
                if (indegree[j] == 0) {
                    queue.offer(j);
                }
            }
        }

        // if there can be cycle or inconsistent state then return empty.
        if (answer.size() != vertices) {
            return List.of();
        }
        return answer;
    }

    @Override
    public List<Integer> getDfsTopologicalOrder() {
        /*
            topological sort does not work if the graph is not directed.
            Perform cycle detection in that case return empty list.
            It should also work for disconnected graph.

            In Dfs based approach we will use stack.
            Now a nodes is not traversed multiple times we use visited array. For this we will use new form of visited
            array which is used. Purpose of this visited array.
                -   A node is not traversed multiple times
                -   For cycle detection. When putting a node in stack then it is having second state.

            To handle disconnected graphs we have to check for every node.
         */

        if (!isDirected) {
            return List.of();
        }
        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> answer = new ArrayList<>();
        int[] state = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            if (state[i] == 2) {
                continue;
            }
            stack.push(i);
            state[i] = 1;
            while (!stack.isEmpty()) {
                int vertex = stack.peek();
                int index = -1;
                for (int j : adjSet.get(vertex)) {
                    if (state[j] == 1) {
                        return List.of();
                    }
                    if (state[j] == 0) {
                        index = j;
                        break;
                    }
                }

                if (index == -1) {
                    stack.pop();
                    answer.add(vertex);
                    state[vertex] = 2;
                } else {
                    stack.push(index);
                    state[index] = 1;
                }
            }
        }
        Collections.reverse(answer);
        return answer;
    }
}
