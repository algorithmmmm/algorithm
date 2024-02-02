package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_선발명단 {
	static int[][] graph;
	static boolean[] visited;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int C = Integer.parseInt(br.readLine());
		
		for(int c = 0; c < C; c++) {
			graph = new int[11][11];

			for(int i = 0; i < 11; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				
				for(int j = 0; j < 11; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited = new boolean[11];
			max = 0;
			
			dfs(0, 0);
			
			System.out.println(max);
		}
		
	}

	public static void dfs(int depth, int sum) {
		if(depth == 11) {
			max = Math.max(max, sum);
			return;
		}
		
		//가지치기
		if(sum + (11 - depth) * 100 < max) { //앞으로 선수를 다 100으로 채워도 max보다 작음
			return;
		}
		
		for(int i = 0; i < 11; i++) {
			if(!visited[i] && graph[depth][i] > 0) {
				visited[i] = true;
				dfs(depth + 1, sum + graph[depth][i]);
				visited[i] = false;
			}
		}
	}

}
