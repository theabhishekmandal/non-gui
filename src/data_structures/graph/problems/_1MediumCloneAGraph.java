package data_structures.graph.problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1MediumCloneAGraph {
    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node.neighbors.add(node2);
        node.neighbors.add(node4);

        node2.neighbors.add(node);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node);
        node4.neighbors.add(node3);

        System.out.println(cloneGraph(node));
        System.out.println(cloneGraph2(node));

    }
    public static Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> origToCopy = new HashMap<>();
        Deque<Node> queue = new ArrayDeque<>();

        origToCopy.put(node, new Node(node.val));
        queue.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (Node neighbor : current.neighbors) {
                // if copy is present then you don't need to add it to queue since it is already visited.
                if (!origToCopy.containsKey(neighbor)) {
                    origToCopy.put(neighbor, new Node(neighbor.val));
                    queue.add(neighbor);
                }
                origToCopy.get(current).neighbors.add(origToCopy.get(neighbor));
            }
        }
        return origToCopy.get(node);
    }

    static Map<Node, Node> map = new HashMap<>();

    public static Node cloneGraph2(Node node) {

        if (node == null)
            return null;

        if (map.containsKey(node))
            return map.get(node);

        Node copy = new Node(node.val);
        map.put(node, copy);

        for (Node nei : node.neighbors)
            copy.neighbors.add(cloneGraph2(nei));

        return copy;
    }
}


class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

    public String toString() {
        return "" + val;
    }
}
