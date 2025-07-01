package data_structures.tree.balanced_binary_tree.avl_tree_impl;

public class AVLTreeTest {
    public static void main(String[] args) {
        var avlTree = new AVLTree<Integer>();

        // RR rotation insert
        avlTree.insertNew(16);
        avlTree.insertNew(10);
        avlTree.insertNew(20);
        avlTree.insertNew(9);
        avlTree.insertNew(30);
        avlTree.insertNew(18);
        avlTree.insertNew(23);
        avlTree.insertNew(50);

        // Below insertion will show two things.
        // Re-Balancing
        // Re-Balancing non root node.
        avlTree.insertNew(34);
    }
}
