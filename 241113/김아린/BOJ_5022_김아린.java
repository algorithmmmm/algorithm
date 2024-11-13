package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_연결 {
	static int N, M;
	static int[] A1, A2, B1, B2;
	static boolean[][] visited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A1 = new int[2];
		A2 = new int[2];
		B1 = new int[2];
		B2 = new int[2];
		
		st = new StringTokenizer(br.readLine(), " ");
		A1[0] = Integer.parseInt(st.nextToken());
		A1[1] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		A2[0] = Integer.parseInt(st.nextToken());
		A2[1] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		B1[0] = Integer.parseInt(st.nextToken());
		B1[1] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		B2[0] = Integer.parseInt(st.nextToken());
		B2[1] = Integer.parseInt(st.nextToken());
		
		//구현
		//1. A -> B 순서로 잇기
		visited = new boolean[N + 1][M + 1];
		
		visited[B1[0]][B1[1]] = true;
		visited[B2[0]][B2[1]] = true;
		
		int d1 = bfs(A1, A2);
		int d2 = bfs(B1, B2);
		
		int dist1 = Integer.MAX_VALUE;
		if(d1 != -1 && d2 != -1) {
			dist1 = d1 + d2;
		}
		
		//2. B -> A 순서로 잇기
		visited = new boolean[N + 1][M + 1];
		
		visited[A1[0]][A1[1]] = true;
		visited[A2[0]][A2[1]] = true;
		
		d1 = bfs(B1, B2);
		d2 = bfs(A1, A2);
		
		int dist2 = Integer.MAX_VALUE;
		if(d1 != -1 && d2 != -1) {
			dist2 = d1 + d2;
		}
		
		//결과 및 출력
		int min = Math.min(dist1, dist2);
		String answer = (min == Integer.MAX_VALUE) ? "IMPOSSIBLE" : Integer.toString(min);
		
		System.out.println(answer);
	}

	public static int bfs(int[] dot1, int[] dot2) {
		Queue<int[]> queue = new LinkedList<>();
		
		int[][] preX = new int[N + 1][M + 1]; //역추적 위한 이전 좌표 기록
        int[][] preY = new int[N + 1][M + 1];
        
        for (int i = 0; i <= N; i++) {
            Arrays.fill(preX[i], -1);
            Arrays.fill(preY[i], -1);
        }
		
		queue.add(new int[]{dot1[0], dot1[1]});
		visited[dot1[0]][dot1[1]] = true;
		
		int count = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int s = 0; s < size; s++) {
				int[] p = queue.poll();
				int x = p[0];
				int y = p[1];
				
				//목표 도착
				if(x == dot2[0] && y == dot2[1]) {
					//역추적해서 루트 저장하기
					visited = new boolean[N + 1][M + 1];
					
					visited[x][y] = true;
					
					int curX = x;
	                int curY = y;
	                while(!(curX == dot1[0] && curY == dot1[1])) {
	                    int px = preX[curX][curY];
	                    int py = preY[curX][curY];
	                    
	                    visited[px][py] = true;
	                    
	                    curX = px;
	                    curY = py;
	                    
	                }
					
					return count;
				}
				
				//사방탐색
				for(int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if(nx < 0 || nx > N || ny < 0 || ny > M || visited[nx][ny]) continue;
					
					queue.add(new int[]{nx, ny});
					visited[nx][ny] = true;
					
					preX[nx][ny] = x;
					preY[nx][ny] = y;
				}
			}
			count++;
		}
		
		return -1;
	}

}
