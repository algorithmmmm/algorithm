package algorithm;

import java.util.*;

class PRO_42898_김아린 {
    public int solution(int m, int n, int[][] puddles) {
        int[][] graph = new int[n + 1][m + 1]; //지역 그래프(물에 잠긴지역 = 1)
        
        for(int i = 0; i < puddles.length; i++) { //물에 잠긴 지역을 그래프에 저장
            int r = puddles[i][0]; //물에 잠긴 지역의 행
            int c = puddles[i][1]; //물에 잠긴 지역의 열
            
            graph[r][c] = 1; //물에 잠긴 지역 표시
        }
        
        long[][] dp = new long[n + 1][m + 1];
        
        if(graph[1][2] != 1) dp[1][2] = 1;
        if(graph[2][1] != 1) dp[2][1] = 1;
        
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < m + 1; j++) {
                if(graph[i][j] != 1) {
                    dp[i][j] += (dp[i][j - 1] + dp[i - 1][j]);
                }  
            }
        }
        
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
        
        long count = dp[n][m];
        
        int answer = (int)count % 1000000007;
        
        return answer;
    }
}
