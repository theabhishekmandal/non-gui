package data_structures.tree.balanced_binary_tree.avl_tree_impl;

import java.util.*;
import java.util.stream.Collectors;

public class AVLTreeImpl {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> integerList;
//        integerList = random.ints(0, 100000).limit(20).boxed().collect(Collectors.toList());
//        integerList = Arrays.asList(10, 20, 30);
        integerList = Arrays.asList(3, 17, 18, 3, 12, 1, 3, 15, 12, 14, 12, 8 , 16, 19, 6, 2, 12, 8, 15, 19);
        StringBuilder br = new StringBuilder();
        System.out.println(integerList);

        IAVLTree<Integer> avl = new AVLTree<>();
        IAVLTree<Integer> avl2 = new AVLTree<Integer>();
//        demo(integerList, avl, avl2, br);
        demo2(integerList, avl, avl2, br);
    }

    private static void demo(List<Integer> integerList, IAVLTree<Integer> avl, IAVLTree<Integer> avl2, StringBuilder br) {
        for(int i : integerList) {
            System.out.println("inserting " + i);
            avl.insert(i);
            String inOrder1 = avl.inOrder();
            String levelOrder1 = avl.levelOrderPretty();

            avl2.insertNew(i);
            String inOrder2 = avl2.inOrder();
            String levelOrder2 = avl2.levelOrderPretty();

            System.out.println(levelOrder2);
            if(!inOrder1.equals(inOrder2)) {
                br.append("\n something is different in inOrder").append(inOrder1).append("\n").append(inOrder2).append("\n");
            }
            if(!levelOrder1.equals(levelOrder2)) {
                br.append(i).append("\n something is different in level order").append(levelOrder1).append("\n").append(levelOrder2);
            }
        }

        System.out.println("inorder string using avl\n" + avl.inOrder());
        System.out.println("inorder traversal using avl2\n" + avl2.inOrder());
        System.out.println();

        System.out.println("level order traversal using avl\n" + avl.levelOrderPretty());
        System.out.println("level order traversal using avl2\n" + avl2.levelOrderPretty());
        System.out.println();

        List<Integer> newList = new ArrayList<>(integerList);
//        newList.sort(Comparator.reverseOrder());
        Collections.shuffle(newList);
        newList = Arrays.asList(18, 1, 3, 14, 4, 9, 2, 10, 17, 7, 15, 11, 6, 1, 2, 16, 16, 6, 10, 11);
        System.out.println("deletion list" + newList);
        for(int i : newList) {
            System.out.println("node to be deleted " + i);
            System.out.println("tree 1 \n" + avl.levelOrderPretty());
            System.out.println("tree 2 \n" + avl2.levelOrderPretty());
            // input is such that their will be no rotation.
            System.out.println("node to be deleted from tree 1 " + i  + " deletedValue = " + avl.delete(i));
            System.out.println("node to be deleted from tree 2 " + i  + " deletedValue = " + avl2.deleteNode(i));

            System.out.println("level order traversal tree 1\n" + avl.levelOrderPretty());
            System.out.println("level order traversal tree 2\n" + avl2.levelOrderPretty());
            System.out.println("inorder traversal tree 1\n" + avl.inOrder());
            System.out.println("inorder traversal tree 2\n" + avl2.inOrder());
            System.out.println();
        }

        System.err.println(br.toString());
    }

    // this method is to be used for checking rotation while deleting
    private static void demo2(List<Integer> integerList, IAVLTree<Integer> avl, IAVLTree<Integer> avl2, StringBuilder br) {
        avl.reset();
        avl2.reset();
        for (int i : integerList) {
            avl.insertNew(i);
            avl2.insert(i);
        }

        // using sorted list to check balancing.
        List<Integer> list = integerList.stream().sorted().toList();

        for (int i : list) {
            System.out.println("tree 1 \n" + avl.levelOrderPretty());
            System.out.println("tree 2 \n" + avl2.levelOrderPretty());
            // input is such that their will be no rotation.
            System.out.println("node to be deleted from tree 1 " + i  + " deletedValue = " + avl.delete(i));
            System.out.println("node to be deleted from tree 2 " + i  + " deletedValue = " + avl2.deleteNode(i));

            System.out.println("level order traversal tree 1\n" + avl.levelOrderPretty());
            System.out.println("level order traversal tree 2\n" + avl2.levelOrderPretty());
            System.out.println("inorder traversal tree 1\n" + avl.inOrder());
            System.out.println("inorder traversal tree 2\n" + avl2.inOrder());
            System.out.println();
        }

    }
}
