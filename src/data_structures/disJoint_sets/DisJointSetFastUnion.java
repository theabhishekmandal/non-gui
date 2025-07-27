package data_structures.disJoint_sets;

/**
 * DisJointSetFastUnion:
 *
 * ✅ Advantages:
 * 1. Simpler & Faster Union:
 *    - Union operation is done in O(1) time, as it directly changes the parent.
 *
 * 2. Easy to Implement:
 *    - The logic is minimal and straightforward.
 *
 * 3. Reasonably Efficient:
 *    - Better than naive approaches like Quick-Find for union-heavy use cases.
 *
 * ❌ Disadvantages:
 * 1. Unbalanced Trees:
 *    - No balancing (like by rank or size), which can lead to tall trees.
 *    - As a result, find() can degrade to O(N) in the worst case.
 *
 * 2. Slower Find:
 *    - If tree becomes deep, find operations become inefficient.
 *
 * 3. No Path Compression:
 *    - Parent chain can become long over time, further slowing down find().
 *
 * ⚠️ Note:
 * This implementation is the base of Quick Union, but does not include balancing techniques like Union by Rank or Path Compression.
 * Next check DisjointSetFastUnionByRank.java
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
