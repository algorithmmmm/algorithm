package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_직사각형탈출 {
	static int N, M;
	static int H, W;
	static int[][] dp;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[N + 1][M + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 1; j <= M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
				
		int Sr = Integer.parseInt(st.nextToken());
		int Sc = Integer.parseInt(st.nextToken());
		int Fr = Integer.parseInt(st.nextToken());
		int Fc = Integer.parseInt(st.nextToken());
		
		//벽 개수의 누적합 저장 
		dp = new int[N + 1][M + 1];
		
		//(1, 1)~(i, j)까지의 벽 개수 합
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + graph[i][j];
			}
		}
		
		//탐색
		System.out.println(bfs(Sr, Sc, Fr, Fc));
	}

	public static int bfs(int sr, int sc, int fr, int fc) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N + 1][M + 1];
		
		queue.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		int count = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s = 0; s < size; s++) {
				int[] p = queue.poll();
				
				if(p[0] == fr && p[1] == fc) return count;
				
				for(int d = 0; d < 4; d++) {
					int nx = p[0] + dx[d];
					int ny = p[1] + dy[d];
					
					if(!isPossible(nx, ny) || visited[nx][ny]) continue;
					
					queue.add(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
			count++;
		}
		
		return -1;
	}

	public static boolean isPossible(int x1, int y1) { //직사각형이 격자 내에 있는지 확인, 벽이 있는지 확인 
		int x2 = x1 + H - 1; //오른쪽 하단 좌표 
		int y2 = y1 + W - 1;
		
		if(1 > x1 || x1 >= N + 1 || 1 > y1 || y1 >= M + 1) return false; //시작점이 격자 내에 있는지 
		if(x2 >= N + 1 || y2 >= M + 1) return false; //끝점이 격자 내에 있는지 
		
		//격자 내 벽이 있는지 확인 
		int num =  dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1];
		
		if(num >= 1) return false;
		
		return true;
	}

}
