class Solution {
    public int climbStairs(int n, int[] costs) {
        long[] dp = new long[n + 1];
        dp[0] = 0;

        for (int j = 1; j <= n; j++) {
            dp[j] = Long.MAX_VALUE;

            for (int jump = 1; jump <= 3 && j - jump >= 0; jump++) {
                long cost = dp[j - jump] + costs[j - 1] + (long) jump * jump;
                dp[j] = Math.min(dp[j], cost);
            }
        }

        return (int) dp[n];
    }
}
