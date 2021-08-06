package data_structures.tree.binary_search_tree.problems;

import utility.Pair;
import data_structures.tree.binary_search_tree.binary_search_tree_impl.BinarySearchTree;

import java.util.*;
import java.util.stream.IntStream;

import static data_structures.tree.binary_search_tree.binary_search_tree_impl.BinarySearchTree.Node;

/**
 * Given two BSTs with unique elements find whether both have the same content
 * ex: [10, 5, 15, 20, 30] and [10, 20, 15 , 5, 30] => true
 *     [10, 5, 15, 20, 30] and  [10, 5, 15, 19, 30] => false
 *
 * Things To Remember
 *  -   Using a single queue by both BSTs
 *
 *  -   The function of queue is that,
 *
 *      -   if the queue is empty or the first element of the queue does not matches with the value of the current
 *          node then add the value in the queue
 *
 *      -   Otherwise pop the first element from the queue
 *
 *  -   Why, are we using queue?
 *      -   we are using queue because, inorder traversal gives sorted order, now if both BSTs have same values
 *          then one bst will add the data to the queue and one bst will remove the data from the queue
 *
 *      -   if suppose BSTs have different elements then if one bst adds the value in the queue, the other bst won't be able
 *          to remove it because it is not present, thereby queue will be non empty for BSTs having different values
 *
 *      -   At last we check if queue is empty or not, empty means bst are equal
 *
 *      -   if queue is not empty then why are we checking the current node's value with the first element?
 *          -   because, the order is not specified that which bst will insert first in the queue, so if bst1 adds
 *              data in the queue [1, 2, 3, 4], and now the currentNode in bst2 is 1, it has to check for the first
 *              element in the queue rather than the last element. After popping, queue will become [2, 3, 4].
 *
 * Approach:
 *  -   Do inorder traversal in both of the BSTs at the same time
 *  -   Now for each inorder successor of both BSTs
 *  -   do the following in the above single queue
 *  -   At last check whether the queue is empty or not, empty means BSTs are equal
 *
 */

public class ContainsSameElements {
    private static final Random random = new Random();

    public static void main(String[] args) {

        var pairList = getInput();

        var list = pairList.get(0).getFirst();
        var bst = pairList.get(0).getSecond();

        var list2 = pairList.get(1).getFirst();
        var bst2 = pairList.get(1).getSecond();

        System.out.println("Inserting order in bst1 " + list);
        System.out.println("Inserting order in bst1 " + list2);
        System.out.println(isContentEqual(bst, bst2));
    }

    private static List<Pair<List<Integer>, BinarySearchTree<Integer>>> getInput() {
        var bst = new BinarySearchTree<Integer>();
        var bst2 = new BinarySearchTree<Integer>();
        var set = new HashSet<Integer>();

        // input1
        IntStream.range(0, 20).forEach(x -> set.add(random.nextInt(100)));
        var list = new ArrayList<>(set);
        list.forEach(bst::insertInBst);

        // input2
        var list2 = new ArrayList<>(list);
        if (random.nextBoolean()) {
            Collections.shuffle(list2);
        } else {
            list2.set(random.nextInt(list2.size()), Integer.MAX_VALUE);
        }
        list2.forEach(bst2::insertInBst);

        return Arrays.asList(Pair.of(list, bst), Pair.of(list2, bst2));
    }

    private static <T extends Comparable<? super T>> boolean isContentEqual(BinarySearchTree<T> one, BinarySearchTree<T> two) {
        if (one == null && two == null) {
            return true;
        }
        if (one == null || two == null || one.getSize() != two.getSize()) {
            return false;
        }

        var curr1 = one.getRoot();
        var curr2 = two.getRoot();

        var stack1 = new LinkedList<Node<T>>();
        var stack2 = new LinkedList<Node<T>>();
        var answerQueue = new LinkedList<T>();
        boolean flag1 = true;
        boolean flag2 = true;
        while (flag1 || flag2) {
            if (flag1) {
                var pair = doInorder(curr1, stack1, answerQueue);
                flag1 = pair.getFirst();
                curr1 = pair.getSecond();
            }

            if (flag2) {
                var pair = doInorder(curr2, stack2, answerQueue);
                flag2 = pair.getFirst();
                curr2 = pair.getSecond();
            }
        }
        return answerQueue.isEmpty();
    }

    private static <T extends Comparable<? super T>> Pair<Boolean, Node<T>> doInorder(Node<T> curr, Deque<Node<T>> stack, Deque<T> queue) {
        if (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            } else {
                curr = stack.pop();
                if (!queue.isEmpty() && curr.getData().equals(queue.getFirst())) {
                    queue.pollFirst();
                } else {
                    queue.addLast(curr.getData());
                }
                curr = curr.getRight();
            }
        } else {
            return Pair.of(false, curr);
        }
        return Pair.of(true, curr);
    }
}
