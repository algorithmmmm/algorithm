package day0815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2169_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][M+1];
		int[][] dp = new int[N+1][M+1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} 
		//end input
		
		dp[1][1] = map[1][1];
		
		for(int j=2;j<=M;j++) {
			dp[1][j] = map[1][j]+dp[1][j-1];
		}

		int[] ltmp = new int[M+2];
		int[] rtmp = new int[M+2];
		
		for(int i=2;i<=N;i++) {
			
			rtmp[M+1] = dp[i-1][M];
			for(int j=M;j>=1;j--) {
				rtmp[j] = Math.max(dp[i-1][j], rtmp[j+1])+map[i][j];
			}
			
			ltmp[0] = dp[i-1][1];
			for(int j=1;j<=M;j++) {
				ltmp[j] = Math.max(dp[i-1][j], ltmp[j-1])+map[i][j];
				dp[i][j] = Math.max(ltmp[j], rtmp[j]);
			}
			
			
		}
		
		System.out.println(dp[N][M]);
	}
}
