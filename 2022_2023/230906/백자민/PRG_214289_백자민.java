package day0905;

public class PRG_214289_백자민 {
	class Solution {
	    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
	        
	        int N = onboard.length;
	        int[][] dp = new int[51][N+1];
			temperature +=10; 
	        t1 +=10; 
	        t2+=10;
			
					for(int j=N-1;j>=0;j--) {
							for(int i=0;i<51;i++) {
									dp[i][j] = Integer.MAX_VALUE - Math.max(a, b);
									
									if(onboard[j]==1 &&(i<t1 || i>t2)) continue;
					                
					        if(i>0) {
							        if(i>temperature)
									        dp[i][j] = Math.min(dp[i][j], dp[i-1][j+1]);
							        dp[i][j] = Math.min(dp[i][j], dp[i-1][j+1]+a);
					        }
					                
					        if(i<50) {
							        if(i<temperature)
									        dp[i][j] = Math.min(dp[i][j], dp[i+1][j+1]);
							        dp[i][j] = Math.min(dp[i][j], dp[i+1][j+1]+a);
					        }
					                
									if(i==temperature) 
							        dp[i][j] = Math.min(dp[i][j], dp[i][j+1]);
					                
									dp[i][j] = Math.min(dp[i][j], dp[i][j+1]+b);
							}
					}	
					return dp[temperature][0];
	    }
	}
}
