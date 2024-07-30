package algorithm;

import java.util.*;
import java.io.*;

public class BOJ_태상이의훈련소생활 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] heights = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i <= N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] section = new int[N + 2];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			section[a] += k;
			section[b + 1] -= k;
		}
		
		int[] dp = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			dp[i] = dp[i - 1] + section[i];
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= N; i++) {
			sb.append(heights[i] + dp[i]).append(" ");
		}
		
		System.out.println(sb);
	}

}
