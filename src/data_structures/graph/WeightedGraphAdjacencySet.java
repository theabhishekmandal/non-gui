package data_structures.graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class WeightedGraphAdjacencySet implements IWeightedGraph {
    private final Map<Integer, Set<WeightedEdge>> adjSet;
    private final int vertices;
    private final boolean isDirected;

    record WeightedEdge(int vertex, int weight){}

    public WeightedGraphAdjacencySet(int vertices, boolean isDirected) {
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

    @Override
    public void addEdge(int src, int dest, int weight) {
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

        adjSet.computeIfAbsent(src, x -> new HashSet<>()).add(new WeightedEdge(dest, weight));

        // For undirected graphs, add the reverse vertex too
        if (!isDirected) {
            adjSet.computeIfAbsent(dest, x -> new HashSet<>()).add(new WeightedEdge(src, weight));
        }

    }

    @Override
    public int[] dijkstraShortestPath(int src, int dest) {
        // if src and dest does not exist then no shortest path.
        if (!(adjSet.containsKey(src) && adjSet.containsKey(dest))) {
            return new int[]{};
        }
        int[] distance = new int[this.vertices];
        int[] parent = new int[this.vertices];
        /*
            Unvisited vertices use distance = Integer.MAX_VALUE. parent[v] = -1 until v is reached;
            parent[src] stays -1.
         */

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        distance[src] = 0;
        priorityQueue.add(new int[]{0, src});

        while (!priorityQueue.isEmpty()) {
            int[] pair = priorityQueue.poll();
            int distanceToReachCurrentVertex = pair[0];
            int currentVertex = pair[1];

            // distanceToReachCurrentVertex is the distance stamped on this queue entry when it was enqueued; it can be stale
            // if a shorter path to currentVertex was found later and distance[currentVertex] was lowered.
            if (distanceToReachCurrentVertex > distance[currentVertex]) {
                continue;
            }

            for (WeightedEdge edgeWithNextVertex : adjSet.get(currentVertex)) {
                int next = edgeWithNextVertex.vertex;
                int distanceToNextVertex = distanceToReachCurrentVertex + edgeWithNextVertex.weight;

                if (distanceToNextVertex < distance[next]) {
                    distance[next] = distanceToNextVertex;
                    parent[next] = currentVertex;
                    priorityQueue.add(new int[]{distanceToNextVertex, next});
                }
            }
        }

        // no path exists
        if (distance[dest] == Integer.MAX_VALUE) {
            return new int[]{};
        }


        // backtracking from dest to src.
        int[] temp = new int[vertices];
        int len = 0;
        for (int v = dest; v != -1; v = parent[v]) {
            temp[len++] = v;
            if (v == src) {
                break;
            }
        }

        int[] shortestPath = new int[len];
        for (int i = 0; i < len; i++) {
            shortestPath[i] = temp[len - 1 - i];
        }
        return shortestPath;
    }

    private boolean hasEdge(int dest, int src) {
        return adjSet.getOrDefault(dest, Collections.emptySet()).stream().anyMatch(x -> x.vertex == src);
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException("Vertex " + v + " is out of range (0 - " + (vertices - 1) + ")");
        }
    }
}
