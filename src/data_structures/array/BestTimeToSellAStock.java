package data_structures.array;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * Approach:
 *  -   Here we are mainly focused on the high values and followed by low values. Because we want to buy a
 *      stock at a cheap price and sell them at higher price
 *  -   So at a given point either there will be a smaller value or a higher value, so when it is smaller value
 *      then save it as new smaller value and when it is higher value then find the profit from it.
 *  -   Here you cannot do like, find the min value and find the max value and then take the difference. For eg: [7, 5, 3, 6, 4, 1].
 *      max=7 and min=1 and diff is 7. But this is not true because you cannot sell before you buy stock
 */
public class BestTimeToSellAStock {
    public static void main(String[] args) {
        System.out.println(getBestTimeToSellStock(new int[]{7, 1, 5, 3, 6, 4}));
    }

    private static int getBestTimeToSellStock(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int j : arr) {
            min = Math.min(j, min);
            max = Math.max(max, j - min);
        }
        return max;
    }
}
