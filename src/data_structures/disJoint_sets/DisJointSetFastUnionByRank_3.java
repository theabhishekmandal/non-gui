package data_structures.disJoint_sets;

/**
 * An implementation of the Disjoint Set (Union-Find) data structure using
 * <strong>Union by Rank</strong> optimization.
 *
 * <p>This approach keeps the tree structures shallow by always attaching the
 * smaller tree (in terms of rank) under the root of the larger one. This significantly
 * reduces the height of the resulting tree and improves the efficiency of the
 * {@code find()} and {@code union()} operations.</p>
 *
 * <p>Although this implementation does not include path compression, it still performs
 * reasonably well with worst-case time complexity of O(log N) per operation.
 * When combined with path compression, the performance approaches O(α(N)) per operation,
 * where α(N) is the inverse Ackermann function.</p>
 *
 * <h2>Advantages ✅</h2>
 * <ul>
 *     <li><strong>Efficient:</strong> Keeps trees balanced, ensuring logarithmic time operations.</li>
 *     <li><strong>Deterministic structure:</strong> Union decisions are made based on rank, not arbitrary.</li>
 *     <li><strong>Low memory overhead:</strong> Requires only one additional integer array for ranks.</li>
 *     <li><strong>Extensible:</strong> A good foundation to combine with path compression for even better performance.</li>
 * </ul>
 *
 * <h2>Disadvantages ❌</h2>
 * <ul>
 *     <li><strong>No path compression:</strong> Still allows tree depth to grow (up to log N).</li>
 *     <li><strong>Rank is approximate:</strong> Only estimates height; not the exact number of levels.</li>
 *     <li><strong>More complex than naive versions:</strong> Slight increase in logic for union operations.</li>
 * </ul>
 *
 * @see DisJointSetFastUnionByRankWithPathCompression_4
 * @see DisJoinSetFastFind_1
 * @see DisJointSetFastUnion_2
 */
public class DisJointSetFastUnionByRank_3 implements IDisJointSet {

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
        // Traverse up the tree to find the root parent
        while (parent[element] != element) {
            element = parent[element];
        }
        return element;
    }

    @Override
    public void union(int firstElement, int secondElement) {
        int rootFirst = find(firstElement);
        int rootSecond = find(secondElement);

        // Already connected; no need to union
        if (rootFirst == rootSecond) {
            return;
        }

        // Attach smaller ranked tree under root of higher ranked tree
        if (rank[rootFirst] < rank[rootSecond]) {
            parent[rootFirst] = rootSecond;
        } else if (rank[rootFirst] > rank[rootSecond]) {
            parent[rootSecond] = rootFirst;
        } else {
            // Same rank, attach second under first and increase rank
            parent[rootSecond] = rootFirst;
            rank[rootFirst]++;
        }
    }
}
