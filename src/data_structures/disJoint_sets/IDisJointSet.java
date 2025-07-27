package data_structures.disJoint_sets;

/*
    Below are the operations of Disjoint Set.
    -   makeSet - creates a set of n elements.
    -   find - Returns the set name of the given element.
    -   union - creates a new set containing the elements x and y in their union and deletes the sets containing the element X and Y.
 */
public interface IDisJointSet {
    void makeSet(int size);
    boolean isConnected(int firstElement, int secondElement);
    int find(int element);
    void union(int firstElement, int secondElement);
}
