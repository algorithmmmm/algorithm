package day240319;

public class PRG_258705_백자민 {
	class Solution {
	    
	    static final int MOD = 10007;
	    
	    public int solution(int n, int[] tops) {
	        
	        int[][] dp = new int[n+1][2];
	        
	        dp[0][0] = 1; //초기값: 전체를 삼각형으로만 채우는 경우 1가지
	        
	        for(int i=0;i<n;i++) {
	            dp[i+1][0] = (dp[i][0]*(2+tops[i]) + dp[i][1]*(1+tops[i]))%MOD;
	            dp[i+1][1] = (dp[i][0]+dp[i][1])%MOD;
	        }
	        
	        return (dp[n][0]+dp[n][1])%MOD;
	    }
	}
}
