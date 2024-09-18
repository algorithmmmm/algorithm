package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_배열에서이동 {
	static int N;
	static int[][] graph;
	static int numMin, numMax;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		graph = new int[N][N];
		
		numMin = 200; //원소의 최솟값
		numMax = 0; //원소의 최댓값s
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				
				numMin = Math.min(numMin, graph[i][j]);
				numMax = Math.max(numMax, graph[i][j]);
			}
		}
		
		//이분탐색
		int low = 0;
		int high = numMax - numMin;
		int answer = 0;
		
		while(low <= high) {
			int mid = (low + high) / 2; //최대 최소값의 차이
			
			//가능한지 판별
			if(isPossible(mid)) {
				answer = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		
		System.out.println(answer);
	}

	private static boolean isPossible(int mid) {
		for(int i = numMin; i <= numMax - mid; i++) { //mid 차이 나는 모든 경우
			int min = i;
			int max = i + mid;
			
			if(bfs(min, max)) return true; //min, max 안에 있는 경로로 탐색 가능한지
		}
		
		return false;
	}

	private static boolean bfs(int min, int max) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		if(min <= graph[0][0] && graph[0][0] <= max) {
			queue.add(new int[]{0, 0});
			visited[0][0] = true;
		}
		
		while(!queue.isEmpty()) {
			int[] p = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = p[0] + dx[d];
				int ny = p[1] + dy[d];
				
				if(nx < 0 || N <= nx || ny < 0 || N <= ny || visited[nx][ny]) continue;
				
				if(min <= graph[nx][ny] && graph[nx][ny] <= max) {
					if(nx == N - 1 && ny == N - 1) return true;
					
					queue.add(new int[]{nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
		
		return false;
	}

}
