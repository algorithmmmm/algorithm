package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_거울설치 {
	static int N;
	static char[][] graph;
	static House start;
	static boolean[][][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class House {
		int x;
		int y;
		int dir;
		int cnt;
		
		House(int x, int y, int dir, int cnt) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		graph = new char[N][N];
		
		boolean flag = false;
		for(int i = 0 ; i < N; i++) {
			char[] chs = br.readLine().toCharArray();
			
			for(int j = 0 ; j < N; j++) {
				graph[i][j] = chs[j];
				
				if(graph[i][j] == '#' && !flag) {
					start = new House(i, j, -1, 0);
					flag = true;
				}
			}
		}
		
		// 구현
		visited = new boolean[N][N][4];
		
		System.out.println(bfs());
	}

	private static int bfs() {
	    PriorityQueue<House> queue = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);

	    for (int d = 0; d < 4; d++) {
	        queue.add(new House(start.x, start.y, d, 0));
	        visited[start.x][start.y][d] = true;
	    }

	    while (!queue.isEmpty()) {
	        House p = queue.poll();

	        int nx = p.x + dx[p.dir];
	        int ny = p.y + dy[p.dir];

	        if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
	        if (graph[nx][ny] == '*') continue;
	        if (visited[nx][ny][p.dir]) continue;

	        visited[nx][ny][p.dir] = true;

	        if (graph[nx][ny] == '#') {
	            return p.cnt;
	        }

	        if (graph[nx][ny] == '!') {
	            // '/' 거울
	            int ndir1 = reflect(p.dir, 1);
	            if (!visited[nx][ny][ndir1]) {
	                queue.add(new House(nx, ny, ndir1, p.cnt + 1));
	            }

	            // '\' 거울
	            int ndir2 = reflect(p.dir, 2);
	            if (!visited[nx][ny][ndir2]) {
	                queue.add(new House(nx, ny, ndir2, p.cnt + 1));
	            }
	        }

	        // 직진 or 거울 설치X
	        queue.add(new House(nx, ny, p.dir, p.cnt));
	    }

	    return -1;
	}

	private static int reflect(int dir, int mirror) {
	    // 상:0 하:1 좌:2 우:3
	    if (mirror == 1) { // '/' 거울
	        if (dir == 0) return 3;
	        if (dir == 1) return 2;
	        if (dir == 2) return 1;
	        if (dir == 3) return 0;
	    } else if (mirror == 2) { // '\' 거울
	        if (dir == 0) return 2;
	        if (dir == 1) return 3;
	        if (dir == 2) return 0;
	        if (dir == 3) return 1;
	    }
	    return -1;
	}

}
