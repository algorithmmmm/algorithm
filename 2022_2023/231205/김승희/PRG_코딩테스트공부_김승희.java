import java.util.*;
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;
        
        for(int i = 0; i < problems.length; i++){
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }
        
        int[][] dp = new int[maxAlp+1][maxCop+1];
        
        for(int i = 0; i < maxAlp+1; i++){
            for(int j = 0; j < maxCop+1; j++){
                if(i <= alp && j <= cop){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        for(int a = alp; a < maxAlp+1; a++){
            for(int c = cop; c < maxCop+1; c++){
                // 공부해서 올리기
                if(a != 0 && dp[a-1][c] != Integer.MAX_VALUE){
                    dp[a][c] = Math.min(dp[a][c], dp[a-1][c]+1);
                }
                if(c != 0 && dp[a][c-1] != Integer.MAX_VALUE){
                    dp[a][c] = Math.min(dp[a][c], dp[a][c-1] + 1);
                }
                // 현재 풀 수 있는 문제 풀기
                for(int i = 0; i < problems.length; i++){
                    if(problems[i][0] <= a && problems[i][1] <= c){ // 풀 수 있음
                        int tempA = Math.min(a + problems[i][2], maxAlp);
                        int tempC = Math.min(c + problems[i][3], maxCop);
                        dp[tempA][tempC] = Math.min(dp[a][c] + problems[i][4], dp[tempA][tempC]);
                    }
                }
                
            }
        }
        return dp[maxAlp][maxCop];
    }
}