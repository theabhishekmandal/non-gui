package data_structures.array;

/**
 * Given n non-negative integers representing an elevation map where the width of each
 * bar is 1, compute how much water it is able to trap after raining
 * ex: arr[1, 1, 5, 2, 7, 6, 1, 4, 2, 3]
 *
 *             |
 *             | |
 *         |   | |
 *         |   | |   |
 *         |   | |   |   |
 *         | | | |   | | |
 *     | | | | | | | | | |
 *     1 1 5 2 7 6 1 4 2 3
 *
 *     Given above the max water that can be trapped is 7 = (5, 7) + (6, 4) + (4, 3)
 *
 * Approach:
 *      -   It is similar to problem container with max RainWater
 *      -   In the same way, the amount of water hold by the container is dependent on the minimum height between the
 *          left and right wall
 *      -   so, first for a given wall at a index i, we have to find the highest wall on the left side as well as on the
 *          right side.
 *      -   After finding the leftmost highest and rightmost highest we can find the water stored by wall at i index
 *          which is given by Math.min(leftMostHighest, rightMostHighest) - wall[i]
 *      -   now the above formula works because for a given wall[i], the water that can be hold at wall[i], will
 *          be the minimum height - wall[i]
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        System.out.println(getTrappedRainWater(new int[]{3, 0, 0, 2, 0, 4}));
        System.out.println(getTrappedRainWater(new int[]{7, 4, 0, 9}));
        System.out.println(getTrappedRainWater(new int[]{6, 9, 9}));
        System.out.println(getTrappedRainWater(new int[]{8, 8, 2, 4, 5, 5, 1}));
        System.out.println(getTrappedRainWater(new int[]{1, 1, 5, 2, 7, 6, 1, 4, 2, 3}));
    }

    private static int getTrappedRainWater(int[] arr) {
        if(arr == null || arr.length <= 2) return 0;

        int[] left = new int[arr.length];
        left[0] = arr[0];
        for(int i = 1; i < arr.length; i++) {
           left[i] = Math.max(left[i - 1], arr[i]);
        }

        int[] right = new int[arr.length];
        right[right.length - 1] = arr[arr.length - 1];
        for(int i = right.length - 2; i >= 0; i--) {
            right[i] = Math.max(arr[i], right[i + 1]);
        }

        int trappedWater = 0;
        for(int i = 0; i < arr.length; i++) {
            trappedWater += Math.min(left[i], right[i]) - arr[i];
        }
        return trappedWater;
    }
}
