package data_structures.disJoint_sets;

/**
 * Implements the Quick Find approach for Disjoint Set (Union-Find).
 *
 * <p><strong>Advantages:</strong></p>
 * <ul>
 *   <li><strong>Very Fast Find:</strong> The {@code find} operation is O(1), as it directly accesses the set ID.</li>
 *   <li><strong>Simple Implementation:</strong> Easy to understand and implement.</li>
 * </ul>
 *
 * <p><strong>Disadvantages:</strong></p>
 * <ul>
 *   <li><strong>Very Slow Union:</strong> The {@code union} operation takes O(N) time, since it may need to update all elements in a set.</li>
 *   <li><strong>Inefficient for Large Datasets:</strong> The union operation becomes a bottleneck in large-scale scenarios.</li>
 *   <li><strong>Not Suitable for Dynamic Connectivity Problems:</strong> Not scalable when many union operations are required.</li>
 * </ul>
 *
 * <p><strong>Note:</strong> This is a classic implementation of the Quick Find algorithm.
 * Use it only when the number of union operations is very small.</p>
 *
 * @see DisJointSetFastUnion
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
