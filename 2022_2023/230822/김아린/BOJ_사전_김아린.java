package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_사전_김아린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(),  " ");
		
		int N = Integer.parseInt(st.nextToken()); //a의 개수
		int M = Integer.parseInt(st.nextToken()); //z의 개수
		int K = Integer.parseInt(st.nextToken()); //k번째
		
		int[][] dp = new int[N + M + 1][N + 1]; //n+mCn
		
		for (int i = 0; i < N + M + 1; i++) {
	        for(int j = 0; j <= i && j < N + 1; j++) {
	            if(j == 0 || i == j ) {
	            	dp[i][j] = 1;
	            }else {
	            	dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
	            }
	        }
	    }
		
		if(dp[N + M][N] < K) { //사전에 수록되어 있는 문자열의 개수가 K보다 작음
			System.out.println(-1);
		} else {
			//문자열 구하기
			
		}
		
	}

}
