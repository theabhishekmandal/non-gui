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

        /*
        Removing this so that we can add cycles.
        if (isDirected && hasEdge(dest, src, weight)) {
            System.out.println("Opposite directed vertex already exists: (" + dest + ", " + src + ");" +
                    " cannot add (" + src + ", " + dest + ")");
            return;
        }
         */

        adjSet.computeIfAbsent(src, x -> new HashSet<>()).add(new WeightedEdge(dest, weight));

        // For undirected graphs, add the reverse vertex too
        if (!isDirected) {
            adjSet.computeIfAbsent(dest, x -> new HashSet<>()).add(new WeightedEdge(src, weight));
        }
    }

    @Override
    public int[] dijkstraShortestPath(int src, int dest) {
        /*
            Adaptive Dijkstra / repeated relaxation:
            This method does not permanently finalize a vertex. If a later edge finds a shorter
            distance to a vertex that was already pulled from the priority queue, that vertex is
            pushed again with the improved distance.

            That is why it can handle cases where a negative edge improves an earlier path, for
            example 0 -> 1 with cost 2 and 0 -> 2 -> 1 with cost -1. Classic Dijkstra would mark
            vertex 1 as done too early and would not allow the later improvement.

            This still should not be used when a negative cycle is reachable, because distances can
            keep improving forever.
         */
        if (!(adjSet.containsKey(src) && adjSet.containsKey(dest))) {
            return new int[]{};
        }
        int[] distance = new int[this.vertices];
        int[] parent = new int[this.vertices];

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

    @Override
    public int[] classicDijkstraShortestPath(int src, int dest) {
        /*
            Classic Dijkstra:
            This method works only when all edge weights are non-negative. Once the closest vertex
            is removed from the priority queue, it is marked visited/finalized and never improved
            again.

            It does not work correctly with negative edges because a shorter path to an already
            finalized vertex may be discovered later, after this method has stopped accepting
            improvements for that vertex.
         */
        if (!(adjSet.containsKey(src) && adjSet.containsKey(dest))) {
            return new int[]{};
        }
        int[] distance = new int[this.vertices];
        int[] parent = new int[this.vertices];
        boolean[] visited = new boolean[this.vertices];

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        distance[src] = 0;
        priorityQueue.add(new int[]{0, src});

        while (!priorityQueue.isEmpty()) {
            int[] pair = priorityQueue.poll();
            int distanceToReachCurrentVertex = pair[0];
            int currentVertex = pair[1];

            if (visited[currentVertex]) {
                continue;
            }

            visited[currentVertex] = true;
            if (currentVertex == dest) {
                break;
            }

            for (WeightedEdge edgeWithNextVertex : adjSet.get(currentVertex)) {
                int next = edgeWithNextVertex.vertex;
                if (visited[next]) {
                    continue;
                }

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

    @Override
    public int[] bellManFord(int src, int dest) {

        if (!(adjSet.containsKey(src) && adjSet.containsKey(dest))) {
            return new int[]{};
        }

        int[] distance = new int[this.vertices];
        int[] parent = new int[this.vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        distance[src] = 0;

        /*
            Traverse every edge for v-1 times. v-1 times because longest path within the graph can be of v-1 edges.
            So v-1 times relaxation will update the optimal path.

         */
        for (int i = 0; i < vertices - 1; i++) {

            for (Map.Entry<Integer, Set<WeightedEdge>> entrySet : adjSet.entrySet()) {
                for (WeightedEdge edge : entrySet.getValue()) {

                    Integer u = entrySet.getKey();
                    int v = edge.vertex;

                    // to know which is the starting point because we are not starting from src node.
                    if (distance[u] != Integer.MAX_VALUE &&
                            distance[u] + edge.weight < distance[v]) {
                        distance[v] = distance[u] + edge.weight;
                        parent[v] = u;
                    }
                }
            }
        }

        /*
            If even after v-1 times, graph can still be relaxed this means a cycle exists.
         */

        for (Map.Entry<Integer, Set<WeightedEdge>> entrySet : adjSet.entrySet()) {
            for (WeightedEdge edge : entrySet.getValue()) {

                Integer u = entrySet.getKey();
                int v = edge.vertex;

                if (distance[u] != Integer.MAX_VALUE &&
                        distance[u] + edge.weight < distance[v]) {
                    System.out.println("Cycle exists");
                        return new int[]{};
                }
            }
        }

        if (distance[dest] == Integer.MAX_VALUE) {
            return new int[]{};
        }

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
            shortestPath[i] = temp[len - i - 1];
        }

        return shortestPath;
    }

    private boolean hasEdge(int dest, int src, int weight) {
        return adjSet.getOrDefault(dest, Collections.emptySet()).stream().anyMatch(x -> x.vertex == src && x.weight == weight);
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException("Vertex " + v + " is out of range (0 - " + (vertices - 1) + ")");
        }
    }
}
