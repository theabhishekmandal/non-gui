package data_structures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import data_structures.disJoint_sets.DisJointSetFastUnionByRankWithPathCompression_4;
import data_structures.disJoint_sets.IDisJointSet;

public class WeightedGraphAdjacencySet implements IWeightedGraph {
    private final Map<Integer, Set<WeightedEdge>> adjSet;
    private final int vertices;
    private final boolean isDirected;

    record WeightedEdge(int sourceVertex, int destinationVertex, int weight){}

    public WeightedGraphAdjacencySet(int vertices, boolean isDirected) {
        if (vertices <= 0) {
            throw new IllegalArgumentException("Number of vertices must be positive.");
        }

        this.vertices = vertices;
        this.isDirected = isDirected;
        this.adjSet = new HashMap<>();

        // Initialize each destinationVertex with an empty set
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
            System.out.println("Opposite directed destinationVertex already exists: (" + dest + ", " + src + ");" +
                    " cannot add (" + src + ", " + dest + ")");
            return;
        }
         */

        adjSet.computeIfAbsent(src, x -> new HashSet<>()).add(new WeightedEdge(src, dest, weight));

        // For undirected graphs, add the reverse destinationVertex too
        if (!isDirected) {
            adjSet.computeIfAbsent(dest, x -> new HashSet<>()).add(new WeightedEdge(dest, src, weight));
        }
    }

    @Override
    public int[] dijkstraShortestPath(int src, int dest) {
        /*
            Adaptive Dijkstra / repeated relaxation:
            This method does not permanently finalize a destinationVertex. If a later edge finds a shorter
            distance to a destinationVertex that was already pulled from the priority queue, that destinationVertex is
            pushed again with the improved distance.

            That is why it can handle cases where a negative edge improves an earlier path, for
            example 0 -> 1 with cost 2 and 0 -> 2 -> 1 with cost -1. Classic Dijkstra would mark
            destinationVertex 1 as done too early and would not allow the later improvement.

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
                int next = edgeWithNextVertex.destinationVertex;
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
            This method works only when all edge weights are non-negative. Once the closest destinationVertex
            is removed from the priority queue, it is marked visited/finalized and never improved
            again.

            It does not work correctly with negative edges because a shorter path to an already
            finalized destinationVertex may be discovered later, after this method has stopped accepting
            improvements for that destinationVertex.
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
                int next = edgeWithNextVertex.destinationVertex;
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
            If you suppose every destinationVertex of node is connected to every destinationVertex, then we have to relax only v-1 edges only.
            So for every destinationVertex we relax edges which will lead to optimal path.

         */
        for (int i = 0; i < vertices - 1; i++) {

            for (Map.Entry<Integer, Set<WeightedEdge>> entrySet : adjSet.entrySet()) {
                for (WeightedEdge edge : entrySet.getValue()) {

                    Integer u = entrySet.getKey();
                    int v = edge.destinationVertex;

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
                int v = edge.destinationVertex;

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

    @Override
    public Object[] getMSTusingPrim() {
        // prim algorithm is for unidirected graphs.
        if (this.isDirected) {
            return new Object[0];
        }
        boolean[] visited = new boolean[this.vertices];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        priorityQueue.offer(new int[]{0, 0, -1});
        int mstWeight = 0;
        List<String> edges = new ArrayList<>();

        while (!priorityQueue.isEmpty()) {
            int[] pair = priorityQueue.poll();
            int edgeWeight = pair[0];
            int v = pair[1];
            int parent = pair[2];

            if (visited[v]) {
                continue;
            }

            mstWeight += edgeWeight;
            visited[v] = true;
            if (parent != -1) {
                edges.add(parent + "-->" + v);
            }

            for (WeightedEdge edge : adjSet.get(v)) {
                if (!visited[edge.destinationVertex]) {
                    priorityQueue.offer(new int[]{edge.weight, edge.destinationVertex, v});
                    // we are not updating parent here, because we are not comparing the minimum edge
                    // so a heavy edge may update light edge parent.

                }
            }
        }


        return new Object[]{mstWeight, edges};
    }

    @Override
    public Object[] getMSTusingPrimDenseGraph() {
        if (this.isDirected) {
            return new Object[0];
        }
        int[] parent = new int[this.vertices];
        boolean[] visited = new boolean[this.vertices];
        int[] cost = new int[this.vertices];
        List<String> edges = new ArrayList<>();
        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        cost[0] = 0;
        int mst = 0;

        for (int i = 0; i < this.vertices; i++) {

            int u = -1;
            for (int v = 0; v < this.vertices; v++) {
                if (!visited[v] && (u == -1 || cost[v] < cost[u])) {
                    u = v;
                }
            }

            // there is no check for if u == -1 after above loop, because after every iteration
            //  there exists a destinationVertex which is not visited because we are doing v-1 iterations.

            visited[u] = true;
            if (parent[u] != -1) {
                edges.add(parent[u] + "-->" + u);
            }
            mst += cost[u];

            for (WeightedEdge edge : adjSet.get(u)) {
                if (!visited[edge.destinationVertex] && edge.weight < cost[edge.destinationVertex]) {
                    parent[edge.destinationVertex] = u;
                    cost[edge.destinationVertex] = edge.weight;
                    // not marking visited here, because we have to pick an edge which has lower cost
                }
            }
        }
        return new Object[] {mst, edges};
    }

    // This cannot be used for dense graphs because edges will be equal to V2 so more running time.
    @Override
    public Object[] getMSTusingKruskal() {
        if (isDirected) {
            return new Object[0];
        }
        List<WeightedEdge> sortedList = adjSet.values().stream().flatMap(Collection::stream)
                .sorted(Comparator.comparing(x -> x.weight)).toList();

        IDisJointSet disJointSet = new DisJointSetFastUnionByRankWithPathCompression_4();
        disJointSet.makeSet(this.vertices);

        int mst = 0;
        List<String> edges = new ArrayList<>();

        for (WeightedEdge edge : sortedList) {

            // why do we don't need any boolean flag to track ?
            // if there is no cycle then we add to list.
            if (disJointSet.find(edge.sourceVertex) != disJointSet.find(edge.destinationVertex)) {

                disJointSet.union(edge.sourceVertex, edge.destinationVertex);
                mst += edge.weight;
                edges.add(edge.sourceVertex + "-->" + edge.destinationVertex);
            }
        }
        return new Object[]{mst, edges};
    }

    private boolean hasEdge(int dest, int src, int weight) {
        return adjSet.getOrDefault(dest, Collections.emptySet()).stream().anyMatch(x -> x.destinationVertex == src && x.weight == weight);
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException("Vertex " + v + " is out of range (0 - " + (vertices - 1) + ")");
        }
    }
}
