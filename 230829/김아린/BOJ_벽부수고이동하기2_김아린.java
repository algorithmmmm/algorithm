import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[][] graph;
	static boolean[][][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Point {
		int x;
		int y;
		int count;
		int hit;
		
		public Point(int x, int y, int count, int hit) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.hit = hit;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		graph = new int[N + 1][M + 1];
		visited = new boolean[K + 1][N + 1][M + 1]; //0 : 벽 안부숨, 1~K: 벽 n개 부숨
		
		for(int i = 1; i <= N; i++) {
			String row = br.readLine();
			
			for(int j = 1; j <= M; j++) {
				graph[i][j] = row.charAt(j - 1) - '0';
			}
		}
		
		int answer = bfs(1, 1);
		
		System.out.println(answer);
	}
	
	public static int bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.add(new Point(i, j, 1, 0));
		visited[0][i][j] = true;
		
		while(!queue.isEmpty()) {
			Point point = queue.poll();
			
			if(point.x == N && point.y == M) { // 종료 조건
				return point.count;
			}
			
			for(int d = 0; d < 4; d++) {
				int nx = point.x + dx[d];
				int ny = point.y + dy[d];
				
				if(1 <= nx && nx <= N && 1 <= ny && ny <= M) {
					if(graph[nx][ny] == 0 && !visited[point.hit][nx][ny]) { //벽 아님
						queue.add(new Point(nx, ny, point.count + 1, point.hit));
						visited[point.hit][nx][ny] = true;
					}else if(graph[nx][ny] == 1 && point.hit < K && !visited[point.hit + 1][nx][ny]) { //벽 있음, k개 미만 부숨, n+1부수고 방문한적 없음
						queue.add(new Point(nx, ny, point.count + 1, point.hit + 1)); //벽 부수기
						visited[point.hit + 1][nx][ny] = true;
					}
				}
			}
		}
		
		return -1;
	}

}