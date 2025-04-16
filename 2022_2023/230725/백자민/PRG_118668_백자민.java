package day0725;

public class PRG_118668_백자민 {
	class Solution {
	    public int solution(int alp, int cop, int[][] problems) {
	        int agoal=Integer.MIN_VALUE, cgoal=Integer.MIN_VALUE;
	        
	        for(int[] p: problems) {
	            agoal = Math.max(agoal, p[0]);
	            cgoal = Math.max(cgoal, p[1]);
	        }

	        //초기 능력치가 이미 목표치를 달성한 경우
	        if(alp>=agoal && cop>=cgoal)
	            return 0;
	        
	        //초기 능력치 중 하나만 목표치를 달성한 경우를 대비해 범위 체크
	        alp = alp>=agoal?agoal:alp;
	        cop = cop>=cgoal?cgoal:cop;
	        
	        int[][] dp = new int[agoal+2][cgoal+2];
	        for(int i=alp;i<=agoal;i++) {
	            for(int j=cop;j<=cgoal;j++) {
	                dp[i][j] = Integer.MAX_VALUE;
	            }
	        }
	        
	        dp[alp][cop] = 0;
	        
	        for(int i=alp;i<=agoal;i++) {
	            for(int j=cop;j<=cgoal;j++) {
	                
	                //공부를 해서 늘리기
	                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
	                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
	                
	                //문제 풀어서 늘리기
	                for(int[] p: problems) {
	                	//문제를 풀 수 없는 경우 스킵 
	                    if(i<p[0] || j<p[1]) continue;
	                    
	                    //풀고 나서 얻은 능력치+이전 능력치가 목표치를 넘는 경우 목표치로 세팅 
	                    int ni = (i+p[2])>agoal?agoal:(i+p[2]);
	                    int nj = (j+p[3])>cgoal?cgoal:(j+p[3]);
	                    
	                    dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j]+p[4]);
	                }
	            }
	        }
	        
	        return dp[agoal][cgoal];
	    }
	}
}
