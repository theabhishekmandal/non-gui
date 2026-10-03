package data_structures.graph.problems;

import java.util.HashMap;
import java.util.Map;

/*
https://neetcode.io/problems/valid-tree/question

Graph Valid Tree
Medium

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to check whether these edges make up a valid tree.

Example 1:
    Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
    Output: true

Example 2:
    Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
    Output: false

Note:
    You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
    [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Constraints:
    - 1 <= n <= 2000
    - 0 <= edges.length <= 5000
    - edges[i].length == 2
    - 0 <= a_i, b_i < n
    - a_i != b_i
    - There are no self-loops or repeated edges.

Recommended Time & Space Complexity:
    O(V + E) time and O(V + E) space, where V is the number of vertices and E is the number of edges.

Topics:
    Depth-First Search, Breadth-First Search, Union Find, Graph
 */

/*
    Intuition in this question is for a given Graph which should be tree, we have to check, connected components
    For every non-visited edge if they don't have same parent then we do the union, which makes one common ancestor.
    if for a non-visited edge while finding the ancestor there is a common ancestor then a cycle is detected.
    For every non-visited edge ancestor should be different or it must be disjoint.
 */

public class _3MediumGraphValidTree {
    public static void main(String[] args) {
        int[][][] matrix = new int[][][] {
                {
                        {0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}
                }   ,
                {
                        {0, 1}, {0, 2}, {0, 3}, {1, 4}
                },
                {
                        {0,1},{2,3},{1,2}
                }
        };

        for (int[][] ints : matrix) {
            System.out.println(validTree(ints.length, ints));
        }
    }

    private static Map<Integer, Integer> map;
    public static boolean validTree(int n, int[][] edges) {

        if (edges.length != n - 1) {
            return false;
        }

        //. we will check using connected component.
        map = new HashMap<>();
        for (int[] edge : edges) {
            if (find(edge[0]) != find(edge[1])) {
                union(edge[0], edge[1]);
            } else {
                return false;
            }
        }
        return true;
    }

    private static int find(int first) {
        Integer parent = map.get(first);
        if (parent == null) {
            map.put(first, first);
            parent = first;
        }
        if (first != parent) {
            parent = find(parent);
            map.put(first, parent);
        }
        return parent;
    }

    private static void union(int first, int second) {
        int parentFirst = find(first);
        int parentSecond = find(second);

        if (parentFirst == parentSecond) {
            return;
        }
        if (parentFirst < parentSecond) {
            map.put(parentSecond, parentFirst);
        } else {
            map.put(parentFirst, parentSecond);
        }
    }
}
