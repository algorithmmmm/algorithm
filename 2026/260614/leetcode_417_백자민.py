class Solution:
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        N,M = len(heights), len(heights[0])

        visit1 = [[0]*M for _ in range(N)]
        visit2 = [[0]*M for _ in range(N)]

        di = [0,0,-1,1]
        dj = [-1,1,0,0]

        ans = []

        def dfs(i,j,visit,prev):
            if 0<=i<N and 0<=j<M and visit[i][j]==0 and heights[i][j]>=prev:
                visit[i][j] = 1

                for d in range(4):
                    ni,nj = i+di[d], j+dj[d]
                    dfs(ni,nj,visit,heights[i][j])
        
        for i in range(N):
            dfs(i,0,visit1,0)
            dfs(i,M-1,visit2,0)
        
        for j in range(M):
            dfs(0,j,visit1,0)
            dfs(N-1,j,visit2,0)
        
        for i in range(N):
            for j in range(M):
                if visit1[i][j]==1 and visit2[i][j]==1:
                    ans.append([i,j])
        
        return ans