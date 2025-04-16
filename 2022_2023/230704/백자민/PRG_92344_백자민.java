package day0704;

public class PRG_92344_백자민 {
	class Solution {
	    
	    static int N,M;
	    static int[][] sum;
	    
	    static void calc() {
	        for(int i=1;i<N;i++) {
	            for(int j=0;j<M;j++) {
	                sum[i][j] += sum[i-1][j];
	            }
	        }
	        
	        for(int j=1;j<M;j++) {
	            for(int i=0;i<N;i++) {
	                sum[i][j] += sum[i][j-1];
	            }
	        }
	    }
	    
	    public int solution(int[][] board, int[][] skill) {
	        N = board.length;
	        M = board[0].length;
	        sum = new int[N+1][M+1];
	        
	        for(int[] sk: skill) {
	            int r1=sk[1], c1=sk[2], r2=sk[3], c2=sk[4];
	            int degree = sk[0]==1?-1:1;
	            degree *= sk[5];
	            
	            sum[r1][c1] += degree;
	            sum[r1][c2+1] += degree*(-1);
	            sum[r2+1][c1] += degree*(-1);
	            sum[r2+1][c2+1] += degree;
	        }
	        
	        calc();
	        
	        int answer = 0;
	        for(int i=0;i<N;i++) {
	            for(int j=0;j<M;j++) {
	                if(board[i][j]+sum[i][j]>0)
	                    answer++;
	            }
	        }
	        
	        
	        return answer;
	    }
	}
}
