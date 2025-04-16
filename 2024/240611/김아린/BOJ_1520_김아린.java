package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_내리막길 {
	static int M, N;
	static int[][] graph;
	static int[][] dp;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		graph = new int[M][N];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[M][N];
		
		for(int i = 0; i < M; i++) { //-1로 초기화해야 경로가 없는 경우를 거를 수 있음 
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(dfs(0, 0));
	}

	public static int dfs(int x, int y) {
		if(x == M - 1 && y == N - 1) return 1; //도착 
		if(dp[x][y] != -1) return dp[x][y]; //이미 탐색함  
		
		dp[x][y] = 0; //방문
		
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue; //범위 벗어남
			
			if(graph[x][y] > graph[nx][ny]) {
				dp[x][y] += dfs(nx, ny);
//				System.out.println(x + " " + y + " : " + dp[x][y]);
			}
		}
		
		return dp[x][y];
	}

}
