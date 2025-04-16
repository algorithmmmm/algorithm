package day1219;

import java.util.Scanner;

public class BOJ_15989_백자민 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		StringBuilder sb = new StringBuilder();
		int[][] dp = new int[10001][4];
		for(int i=1;i<4;i++) {
			for(int j=1;j<=i;j++) {
				dp[i][j] = 1;
			}
		}
		
		for(int n=4;n<10001;n++) {
			dp[n][1] = dp[n-1][1];
			dp[n][2] = dp[n-2][1] + dp[n-2][2];
			dp[n][3] = dp[n-3][1] + dp[n-3][2] + dp[n-3][3];
		}
		
		for(int t=0;t<T;t++) {
			int n = sc.nextInt();
			
			sb.append((dp[n][1]+dp[n][2]+dp[n][3])+"\n");
		}
		System.out.println(sb.toString());
	}
}
