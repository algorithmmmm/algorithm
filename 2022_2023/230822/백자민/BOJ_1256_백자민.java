package day0822;

import java.util.Scanner;

public class BOJ_1256_백자민 {
	
	static int N,M,K;
	
	static int[][] dp;
	
	static String getWord() {
		StringBuilder sb = new StringBuilder();
		
		while(N>0 && M>0) {
			if(dp[N-1][M]>=K) {
				sb.append("a");
				N--;
			} else {
				sb.append("z");
				K = K-dp[N-1][M];
				M--;
			}
		}
		
		if(N==0) {
			for(int i=0;i<M;i++)
				sb.append("z");
		}
		
		if(M==0) {
			for(int i=0;i<N;i++)
				sb.append("a");
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		dp = new int[101][101];
		
		for(int i=1;i<=100;i++) {
			dp[i][0] = 1;
			dp[0][i] = 1;
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				dp[i][j] = dp[i-1][j]+dp[i][j-1];
				if(dp[i][j] > 1000000000 ) dp[i][j] = 1000000000; 
			}
		}		
		
		String answer = "";
		if(dp[N][M]<K)
			answer = "-1";
		else 
			answer = getWord();
		
		System.out.println(answer);
	}
}
