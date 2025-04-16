package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_욕심쟁이판다 {
	static int N;
	static int[][] graph;
	static int[][] dp;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][N];
		
		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				max = Math.max(max, dfs(i, j));
			}
		}
		
		System.out.println(max);
	}

	public static int dfs(int x, int y) {
		if(dp[x][y] != 0) { //이미 방문한적 있음
			return dp[x][y];
		}
		
		dp[x][y] = 1; //초기값
		
		for(int d = 0; d < 4; d++) { //사방탐색
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue; //범위 벗어남
			
			if(graph[x][y] < graph[nx][ny]) {
				dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1); //탐색 : 최댓값이 되어야함
			}
		}
		
		return dp[x][y];
	}

}
