package data_structures.dynamic_programming;

/**
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 *
 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of
 * the floor, and you can either start from the step with index 0, or the step with index 1.
 *
 * Here, the top of the floor means outside of the array. for eg: if a[1, 2, 3] then top of the floor is index 3
 *
 * Approach
 *  -   It is based on dp problem, i.e to reach a given step, it depends on the previous two step.
 *  -   To reach a step you can take the minimum cost of the i - 1 index or i - 2 index.
 */
public class StaircaseProblem {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(arr));
        System.out.println(minCostClimbingStairs2(arr));
    }

    private static int minCostClimbingStairs(int[] cost) {
        if(cost.length == 0) return 0;
        if(cost.length == 1) return cost[0];
        int[] dp = new int[cost.length + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for(int i = 2; i < dp.length; i++) {
            int value = (i == cost.length)? 0 : cost[i];
            dp[i] = value + Math.min(dp[i - 1], dp[i - 2]);
        }
        return dp[dp.length - 1];
    }

    private static int minCostClimbingStairs2(int[] cost) {
        if(cost.length == 0) return 0;
        if(cost.length == 1) return cost[0];
        int prev = 0;
        int prev2 = 0;
        for(int i = 2; i <= cost.length; i++) {
            int sum = Math.min(prev + cost[i - 1], prev2 + cost[i - 2]);
            prev2 = prev;
            prev = sum;
        }
        return prev;
    }
}
