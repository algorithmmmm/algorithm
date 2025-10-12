package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_파일합치기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			int[] arr = new int[K + 1];
			int[] sum = new int[K + 1]; // 누적합
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= K; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + arr[i];
			}
			
			// 구현
			int[][] dp = new int[K + 1][K + 1]; // i~j 파일까지의 최소비용
			
			for(int len = 2; len <= K; len++) { // 구간 길이
                for(int i = 1; i + (len - 1) <= K; i++) { // 시작
                    int j = i + (len - 1); // 끝
                    dp[i][j] = Integer.MAX_VALUE;
                    
                    for(int k = i; k < j; k++) { // 중간 인덱스
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum[j] - sum[i - 1]);	
					}
				}
			}
			
			// 출력
			sb.append(dp[1][K] + "\n");
		}
		
		System.out.println(sb);
	}

}
