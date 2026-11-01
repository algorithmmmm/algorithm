class Solution {
    public int minTaps(int n, int[] ranges) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 20000);
        dp[0] = 0;

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                int left = Math.max(0, j - ranges[j]);
                int right = Math.min(n, j + ranges[j]);
                if(left < i && i <= right) {
                    dp[i] = Math.min(dp[i], dp[left] + 1);
                }
            }
        }

        return dp[n] >= 20000 ? -1 : dp[n];
    }
}