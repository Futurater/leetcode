import java.util.*;

class Solution {
    private final TreeMap<Long, Integer> left = new TreeMap<>();
    private final TreeMap<Long, Integer> right = new TreeMap<>();

    private long sumLeft = 0;
    private long sumRight = 0;
    private int sizeLeft = 0;
    private int sizeRight = 0;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        // Add the first window.
        for (int i = 0; i < x; i++) {
            add(nums[i]);
        }

        cost[0] = getCost();

        // Slide the window.
        for (int start = 1; start <= n - x; start++) {
            remove(nums[start - 1]);
            add(nums[start + x - 1]);

            cost[start] = getCost();
        }

        long INF = Long.MAX_VALUE / 4;

        /*
         * dp[t][i] =
         * minimum cost to choose t non-overlapping windows
         * from the first i elements.
         */
        long[][] dp = new long[k + 1][n + 1];

        for (int t = 0; t <= k; t++) {
            Arrays.fill(dp[t], INF);
        }

        // Choosing zero windows costs zero.
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int t = 1; t <= k; t++) {
            for (int i = 1; i <= n; i++) {
                // Do not end a chosen window at index i - 1.
                dp[t][i] = dp[t][i - 1];

                // Use the window [i - x, i - 1].
                if (i >= x && dp[t - 1][i - x] != INF) {
                    int start = i - x;

                    dp[t][i] = Math.min(
                        dp[t][i],
                        dp[t - 1][i - x] + cost[start]
                    );
                }
            }
        }

        return dp[k][n];
    }

    /*
     * Maintains:
     *
     * left  = lower half, including the median
     * right = upper half
     *
     * Every value in left <= every value in right.
     */
    private void add(long value) {
        if (left.isEmpty() || value <= left.lastKey()) {
            insert(left, value);
            sumLeft += value;
            sizeLeft++;
        } else {
            insert(right, value);
            sumRight += value;
            sizeRight++;
        }

        rebalance();
    }

    private void remove(long value) {
        if (left.containsKey(value)) {
            erase(left, value);
            sumLeft -= value;
            sizeLeft--;
        } else {
            erase(right, value);
            sumRight -= value;
            sizeRight--;
        }

        rebalance();
    }

    private void rebalance() {
        /*
         * left must contain either the same number of elements as right,
         * or exactly one more element.
         */
        while (sizeLeft > sizeRight + 1) {
            long value = left.lastKey();

            erase(left, value);
            sumLeft -= value;
            sizeLeft--;

            insert(right, value);
            sumRight += value;
            sizeRight++;
        }

        while (sizeLeft < sizeRight) {
            long value = right.firstKey();

            erase(right, value);
            sumRight -= value;
            sizeRight--;

            insert(left, value);
            sumLeft += value;
            sizeLeft++;
        }
    }

    private long getCost() {
        long median = left.lastKey();

        long leftCost =
            median * sizeLeft - sumLeft;

        long rightCost =
            sumRight - median * sizeRight;

        return leftCost + rightCost;
    }

    private void insert(TreeMap<Long, Integer> map, long value) {
        map.put(value, map.getOrDefault(value, 0) + 1);
    }

    private void erase(TreeMap<Long, Integer> map, long value) {
        int count = map.get(value);

        if (count == 1) {
            map.remove(value);
        } else {
            map.put(value, count - 1);
        }
    }
}
