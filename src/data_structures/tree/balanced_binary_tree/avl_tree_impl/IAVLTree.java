package data_structures.tree.balanced_binary_tree.avl_tree_impl;

public interface IAVLTree<T extends Comparable<? super T>> {

    void insert(T data);

    void insertNew(T data);

    AVLTree.Node<T> delete(T data);              // Corresponds to `delete(T data)` – returns deleted node data

    AVLTree.Node<T>  deleteNode(T data);          // Corresponds to `deleteNode(T data)` – same behavior, different path

    void reset();

    int getSize();

    AVLTree.Node<T> getRoot();

    String inOrder();

    String levelOrder();

    String levelOrderPretty();
}