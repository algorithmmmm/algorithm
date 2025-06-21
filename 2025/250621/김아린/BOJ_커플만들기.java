package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_커플만들기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] man = new int[N];
		int[] woman = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			man[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			woman[i] = Integer.parseInt(st.nextToken());
		}
		
		// 정렬
		Arrays.sort(man);
		Arrays.sort(woman);
		
		// dp 배열 채우기
		int[][] dp = new int[1001][1001];
		
		for (int i = 1; i <= N; i++) {
	        for (int j = 1; j <= M; j++) {
	        	// 직진
	            if (i == j) {
	                dp[i][j] = dp[i-1][j-1] + Math.abs(man[i-1] - woman[j-1]);
	            }
	            // 여자가 남자 선택
	            else if (i > j) {
	                dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1] + Math.abs(man[i-1] - woman[j-1]));
	            }
	            // 남자가 여자 선택
	            else if (j > i) {
	                dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j-1] + Math.abs(man[i-1] - woman[j-1]));
	            }
	        }
	    }
		
		System.out.println(dp[N][M]);
	}
}
