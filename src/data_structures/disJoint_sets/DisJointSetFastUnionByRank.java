package data_structures.disJoint_sets;

/**
 * DisJointSetFastUnionByRank:
 *
 * ✅ Advantages:
 * 1. Improved Efficiency:
 *    - Union by rank keeps the trees shallow, making operations faster.
 *    - Both union and find operations run in O(log N) time in the worst case.
 *
 * 2. Deterministic Balancing:
 *    - Smaller trees are attached under larger trees, avoiding skewed trees.
 *
 * 3. Simplicity:
 *    - Rank is easy to maintain and reduces tree height effectively.
 *
 * 4. Good Base for Further Optimization:
 *    - Can be combined with Path Compression for near-constant time operations.
 *
 * ❌ Disadvantages:
 * 1. No Path Compression:
 *    - Without it, find() can still be O(log N) in the worst case.
 *
 * 2. Rank is Approximate:
 *    - It does not store exact height, only an upper bound.
 *
 * 3. Slightly More Memory:
 *    - Requires an extra array (rank[]) along with parent[].
 *
 * 4. Slightly More Complex:
 *    - Compared to naive implementations, union by rank logic is more involved.
 */
public class DisJointSetFastUnionByRank implements IDisJointSet {

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
