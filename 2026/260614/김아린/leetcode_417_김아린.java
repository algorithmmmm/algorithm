class Solution {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific  = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();

        for(int i = 0; i < m; i++) {
            pacific[i][0] = true;
            pQueue.offer(new int[]{i, 0});

            atlantic[i][n - 1] = true;
            aQueue.offer(new int[]{i, n - 1});
        }

        for(int j = 0; j < n; j++) {
            pacific[0][j] = true;
            pQueue.offer(new int[]{0, j});

            atlantic[m - 1][j] = true;
            aQueue.offer(new int[]{m - 1, j});
        }

        bfs(heights, pQueue, pacific,  m, n);
        bfs(heights, aQueue, atlantic, m, n);

        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(pacific[i][j] && atlantic[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }
        return result;
    }

    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] visited, int m, int n) {
        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            int r = p[0], c = p[1];

            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if(visited[nr][nc]) continue;
                if(heights[nr][nc] < heights[r][c]) continue;

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }
    }
}
