package data_structures.disJoint_sets;

/**
 * DisJoinSetFastFind (Quick Find approach):
 *
 * ✅ Advantages:
 * 1. Very Fast Find:
 *    - The find operation is O(1), as it directly accesses the set ID.
 *
 * 2. Simple Implementation:
 *    - Easy to understand and implement.
 *
 * ❌ Disadvantages:
 * 1. Very Slow Union:
 *    - Union takes O(N) time since it may need to update all elements in a set.
 *
 * 2. Inefficient for Large Datasets:
 *    - The union operation becomes a bottleneck in large-scale scenarios.
 *
 * 3. Not Suitable for Dynamic Connectivity Problems:
 *    - Not scalable when many union operations are required.
 *
 * ⚠️ Note:
 * This is a classic implementation of the Quick Find algorithm.
 * Use it only when the number of union operations is very small.
 * Next Check DisJointSetFastUnion.java
 */

public class DisJoinSetFastFind implements IDisJointSet {
    private int[] setId; // Each index points to its set representative

    @Override
    public void makeSet(int size) {
        setId = new int[size];
        // Initially, each element is in its own set (its index)
        for (int i = 0; i < size; i++) {
            setId[i] = i;
        }
    }

    @Override
    public boolean isConnected(int firstElement, int secondElement) {
        // Check if both elements belong to the same set
        return find(firstElement) == find(secondElement);
    }

    @Override
    public int find(int element) {
        // Directly return the set identifier
        return setId[element];
    }

    @Override
    public void union(int firstElement, int secondElement) {
        int idx = find(firstElement);
        int idy = find(secondElement);

        // Already connected, no need to do anything
        if (idx == idy) {
            return;
        }

        // Merge the sets: update all entries with id 'idx' to 'idy'
        for (int i = 0; i < setId.length; i++) {
            if (setId[i] == idx) {
                setId[i] = idy;
            }
        }
    }
}
