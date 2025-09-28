package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_여행 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 도시 수
		int M = Integer.parseInt(st.nextToken()); // 최대 지날 수 있는 도시 수
		int K = Integer.parseInt(st.nextToken()); // 항로 수
		
		int[][] graph = new int[N + 1][N + 1];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a][b] = Math.max(graph[a][b], c);
		}
		
		int[][] dp = new int[N + 1][M + 1]; // 도시 n번까지 m개의 도시를 거쳐왔을 때 최댓값
		for(int i = 1; i < N + 1; i++) {
			Arrays.fill(dp[i], Integer.MIN_VALUE);
		}
		
		dp[1][1] = 0; // 1번에서 출발
		
		for(int i = 1; i < N + 1; i++) {
			for(int j = i + 1; j < N + 1; j++) {
				if(graph[i][j] == 0) continue; // 경로 없음
				
				for(int k = 1; k < M; k++) {
					if(dp[i][k] != Integer.MIN_VALUE) { // 지난적 있음
						dp[j][k + 1] = Math.max(dp[j][k + 1], dp[i][k] + graph[i][j]);
					}
				}
			}
		}
		
		int max = 0;
		for(int i = 1; i < M; i++) {
			max = Math.max(max, dp[N][i]);
		}
		
		System.out.println(max);
	}

}
