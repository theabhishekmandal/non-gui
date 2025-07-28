package data_structures.disJoint_sets;

/**
 * DisJointSetFastUnion (Quick Union approach):
 *
 * ✅ Advantages:
 * <ul>
 *   <li><strong>1. Simpler & Faster Union:</strong>
 *       Union operation is done in O(1) time, as it directly changes the parent.</li>
 *   <li><strong>2. Easy to Implement:</strong>
 *       The logic is minimal and straightforward.</li>
 *   <li><strong>3. Reasonably Efficient:</strong>
 *       Better than naive approaches like Quick-Find for union-heavy use cases.</li>
 * </ul>
 *
 * ❌ Disadvantages:
 * <ul>
 *   <li><strong>1. Unbalanced Trees:</strong>
 *       No balancing (like by rank or size), which can lead to tall trees and degrade find to O(N).</li>
 *   <li><strong>2. Slower Find:</strong>
 *       As trees become deeper, the find operation becomes slower.</li>
 *   <li><strong>3. No Path Compression:</strong>
 *       Parent chains can become long over time, further degrading performance.</li>
 * </ul>
 *
 * ⚠️ <strong>Note:</strong>
 * This is the base Quick Union algorithm.
 * For improved performance, consider enhancements like Union by Rank or Path Compression,
 * as implemented in {@link DisJointSetFastUnionByRank} or {@link DisJointSetFastUnionByRankWithPathCompression}.
 */
public class DisJointSetFastUnion implements IDisJointSet {

    private int[] parent; // Array to store the parent of each element

    @Override
    public void makeSet(int size) {
        parent = new int[size];
        // Initially, every element is its own parent
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public boolean isConnected(int firstElement, int secondElement) {
        // Two elements are connected if they have the same root
        return find(firstElement) == find(secondElement);
    }

    @Override
    public int find(int element) {
        // Traverse up the tree until reaching the root
        while (parent[element] != element) {
            element = parent[element];
        }
        return element;
    }

    @Override
    public void union(int firstElement, int secondElement) {
        int root1 = find(firstElement);
        int root2 = find(secondElement);

        if (root1 == root2) {
            return; // Already connected
        }

        // Combine both sets by pointing one root to another
        parent[root2] = root1;
    }
}
