class Solution:
    def countPaths(self, grid: List[List[int]]) -> int:
        MOD = 10**9+7
        m,n = len(grid), len(grid[0])
        dp = [[0]*n for _ in range(m)]

        di = [0,0,-1,1]
        dj = [-1,1,0,0]

        def dfs(i,j):
            if dp[i][j]:
                return dp[i][j]
            
            dp[i][j]=1
            for d in range(4):
                ni, nj = i+di[d], j+dj[d]

                if 0<=ni<m and 0<=nj<n and grid[i][j] < grid[ni][nj]:
                    dp[i][j] += dfs(ni,nj)
            
            dp[i][j] %= MOD
            return dp[i][j]
        
        ans = 0
        for i in range(m):
            for j in range(n):
                ans += dfs(i,j)
                ans%=MOD
        
        return ans