package data_structures.disJoint_sets;

/**
 * DisJointSetFastUnionByRankWithPathCompression
 *
 * This class implements the Disjoint Set (Union-Find) data structure using two key optimizations:
 * 1. Union by Rank: Ensures smaller (shallower) trees are attached under deeper trees to prevent height growth.
 * 2. Path Compression: Flattens the tree during find operations, pointing nodes directly to the root.
 *
 * Together, these optimizations make both `find` and `union` operations extremely efficient, with
 * nearly constant amortized time complexity O(α(N)), where α is the inverse Ackermann function.
 *
 * This implementation is well-suited for large-scale dynamic connectivity problems such as:
 * - Kruskal’s Minimum Spanning Tree algorithm
 * - Network connection queries
 * - Connected components in graphs
 *
 * Pros:
 * - Very fast for large number of operations
 * - Keeps tree height minimal over time
 *
 * Cons:
 * - Slightly more complex logic than simpler approaches (e.g., QuickFind)
 * - Uses additional space for the rank array
 */

public class DisJointSetFastUnionByRankWithPathCompression implements IDisJointSet {

    private int[] parent; // Stores the parent of each element
    private int[] rank;   // Approximate height of the tree rooted at each node

    @Override
    public void makeSet(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;   // Initially, each element is its own parent
            rank[i] = 0;     // Initial rank (tree height) is 0
        }
    }

    @Override
    public boolean isConnected(int firstElement, int secondElement) {
        return find(firstElement) == find(secondElement);
    }

    @Override
    public int find(int element) {
        // Path Compression: Flattens the tree structure by pointing the current node directly to its root
        if (element != parent[element]) {
            parent[element] = find(parent[element]);
        }
        return parent[element];
    }

    @Override
    public void union(int firstElement, int secondElement) {
        int rootFirst = find(firstElement);
        int rootSecond = find(secondElement);

        // Already connected; no need to union
        if (rootFirst == rootSecond) {
            return;
        }

        // Union by Rank: Attach the tree with lower rank to the one with higher rank
        if (rank[rootFirst] < rank[rootSecond]) {
            parent[rootFirst] = rootSecond;
        } else if (rank[rootFirst] > rank[rootSecond]) {
            parent[rootSecond] = rootFirst;
        } else {
            // If ranks are equal, choose one arbitrarily and increase its rank
            parent[rootSecond] = rootFirst;
            rank[rootFirst]++;
        }
    }
}
