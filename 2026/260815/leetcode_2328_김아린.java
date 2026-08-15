class Solution {
    private static int[][] grid;
    private static int[][] dp;
    private static int m, n;
    private final int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    public int countPaths(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        dp = new int[m][n];

        int answer = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                answer = (answer + dfs(i, j)) % 1_000_000_007;
            }
        }
        return answer;
    }

    private int dfs(int i, int j) {
        if(dp[i][j] != 0) {
            return dp[i][j];
        }

        long sum = 1;
        for(int[] d : dir) {
            int ni = i + d[0];
            int nj = j + d[1];

            if(ni >= 0 && ni < m && nj >= 0 && nj < n) {
                if(grid[ni][nj] > grid[i][j]) {
                    sum += dfs(ni, nj);
                }
            }
        }
        dp[i][j] = (int)(sum % 1_000_000_007);
        return dp[i][j];
    }
}