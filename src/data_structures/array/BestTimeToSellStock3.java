package data_structures.array;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 *
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 *
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * Example 4:
 *
 * Input: prices = [1]
 * Output: 0
 *
 * Approach:
 *  -   see the approach below, it is working for 2 transactions, but will also work for k transactions.
 */
public class BestTimeToSellStock3 {
    public static void main(String[] args) {
        System.out.println(getBestTimeToSellStockNew(new int[]{3,3,5,0,0,3,1,4}));
        System.out.println(getBestTimeToSellStockNew(new int[]{1,2,3,4,5}));
        System.out.println(getBestTimeToSellStockNew(new int[]{7,6,4,3,1}));
        System.out.println(getBestTimeToSellStockNew(new int[]{1}));
    }

    /*
        The basic idea here is if you are allowed for k transactions.
        then your
        current profit = previous profit(at previous transaction) + -buying price at previous day + selling price today

        profit[i][j - 1] means keeping the profit of previous day
        profit[i - 1][j] means profit of the transaction before the current transaction on same day as multiple transactions are allowed

        Current profit is given by, either buy (keeping the profit of previous day of same transaction) or (buying previous mth day + selling today + profit of previous transaction of mth day)
        profit[i][j] = Math.max(profit[i][j - 1], profit[i - 1][m] + price[j] - price[m]) for m = 0...j-1

        for transaction i = 0, all profit[0][j] = 0 for j in days, because the profit will be zero if there are no
        transactions.

        for day j = 0, all profit[i][0] = 0 for i in transactions, because for any transaction, if you buy
        and sell on the same day then the profit will always be zero.


        profit[i][j] = Math.max(profit[i][j - 1], profit[i - 1][m] + price[j] - price[m]) for m = 0...j-1
        We can avoid calculating profit[i - 1][m] - price[m] for j = 0...j-1 in above by taking the max only as shown by below code
        this above statement means, get me the max(diff between the profit of previous transaction and the stock buying price) => maxDiff
        and then we can get
        profit[i][j] = Math.max(profit[i][j - 1], prices[j] + maxDiff)
        currentProfit = max(keep previous profit, selling price today + (previous profit + -buying price))
     */

    private static int getBestTimeToSellStockNew(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        int maxTransaction = 2;

        maxTransaction += 1;
        int[][] profit = new int[maxTransaction][prices.length];

        for(int i = 1; i < maxTransaction; i++) {

//            int maxDiff = -prices[0] + profit[i - 1][0]; profit[i - 1][0] is 0
            int maxDiff = -prices[0];

            for(int j = 1; j < prices.length; j++) {

                // It is saying best time to sell
                int temp = Math.max(profit[i][j - 1], prices[j] + maxDiff);
                profit[i][j] = temp;

                // It is saying best time to buy
                maxDiff = Math.max(maxDiff, -prices[j] + profit[i - 1][j]);
            }
        }
        return profit[maxTransaction - 1][prices.length - 1];
    }
}
