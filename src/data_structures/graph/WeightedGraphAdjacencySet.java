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
        int[] path = new int[this.vertices];
        /*
            now currently reaching to every node is not possible except src node, so
            we would initialize distance array with -1

            now a current node will have multiple weighted edges to multiple vertices.
            We would require a min priority queue which will hold the weightedEdge and vertex.
            Shortest edge will be given priority.

            src = {0, src} is added to queue where 0 signifies the distance to reach a current node from previous node.
            now from each current vertex to next vertex, we will visit the node
            and check if distance[currentNode] + weightedEdge < distance[nextNode] where distance[nextNode]
            can have two possible values.
                -   -1 - node was never visited so it was unreachable
                -   positive number - it was visited from a different path which had different distance


         */


        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Arrays.fill(distance, Integer.MAX_VALUE);

        // starting with src
        distance[src] = 0;
        priorityQueue.add(new int[]{0, src});

        while (!priorityQueue.isEmpty()) {
            int[] pair = priorityQueue.poll();
            int distanceToReachCurrentVertex = pair[0];
            int currentVertex = pair[1];

            for (WeightedEdge edgeWithNextVertex : adjSet.get(currentVertex)) {

                int distanceToNextVertex = distanceToReachCurrentVertex + edgeWithNextVertex.weight;

                if (distanceToNextVertex < distance[edgeWithNextVertex.vertex]) {
                    distance[edgeWithNextVertex.vertex] = distanceToNextVertex;
                    priorityQueue.add(new int[] {distanceToNextVertex, edgeWithNextVertex.vertex});
                    path[edgeWithNextVertex.vertex] = currentVertex;
                }
            }
        }

        return path;
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
