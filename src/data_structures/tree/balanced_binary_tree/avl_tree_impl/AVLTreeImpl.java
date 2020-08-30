package data_structures.tree.balanced_binary_tree.avl_tree_impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AVLTreeImpl {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> integerList = random.ints(0, 20).limit(20).boxed().collect(Collectors.toList());
        System.out.println(integerList);

        var avl = new AVLTree<Integer>();
        var avl2 = new AVLTree<Integer>();
        for(int i : integerList) {
            avl.insert(i);
            avl2.insertNew(i);
        }

        System.out.println(avl.inOrder());
        System.out.println(avl2.inOrder());

        System.out.println();
        System.out.println(avl.levelOrder());
        System.out.println(avl2.levelOrder());
    }
}
