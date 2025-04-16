import java.util.*;
/*
wall[i][j] : (i, j)가 벽이면 true
path[i][j] : (i, j)를 올 수 있는 경우의 수
-> 오른쪽과 아래쪽으로만 움직이기 때문에
-> 왼쪽과 위쪽에서 올 수 있는지 확인한다.

*/
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        boolean[][] wall = new boolean[n][m];
        for(int p = 0; p < puddles.length; p++){
            wall[puddles[p][1]-1][puddles[p][0]-1] = true;
        }
        int[][] path = new int[n][m];
        int[] di = {0, 1};
        int[] dj = {1, 0};
        
        path[0][0] = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(wall[i][j]) continue;
                // 왼쪽
                int nj = j-1;
                if(nj >= 0 && !wall[i][nj]){
                    path[i][j] = (path[i][j] + path[i][nj]) % 1_000_000_007;
                }
                // 위쪽
                int ni = i -1; 
                if(ni  >= 0 && !wall[ni][j]){
                    path[i][j] = (path[i][j] + path[ni][j]) % 1_000_000_007;
                }
            }
        }
        
        return path[n-1][m-1];
    }
}