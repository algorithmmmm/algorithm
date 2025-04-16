import java.io.*;
import java.util.*;

public class Main {
	static int N, L, R;
	static int[][] graph;
	static boolean[][] visited;
	
	static List<int[]> list;
	static int sum;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//구현
		int day = 0; //정답 : 며칠동안 인구이동 발생
		while(true) {
			visited = new boolean[N][N]; //bfs 탐색을 위한 방문체크
			
			boolean moved = false; //인구 이동 있는지
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						list = new ArrayList<>(); //연합
						sum = 0; //연합국 인구 합
						
						bfs(i, j);
						
						//인구 이동
						if(list.size() >= 2) {
							int population = sum / list.size(); //연합국 새 인구수
							
							for(int[] point : list) {
								graph[point[0]][point[1]] = population;
							}
							
							moved = true;
						} 
					}
				}
			}
			
			if(!moved) break; //인구이동 없었으면 중단
			
			day++;
		}
		
		//출력
		System.out.println(day);
	}
	
	public static void bfs(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.add(new int[] {i, j});
		visited[i][j] = true;
		list.add(new int[] {i,j});
		
		sum += graph[i][j];
		
		while(!queue.isEmpty()) {
			int x = queue.peek()[0];
			int y = queue.poll()[1];
			
			for(int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(0 > nx || nx >= N || 0 > ny || ny >= N) continue; //범위 벗어남
				if(visited[nx][ny]) continue; //이미 방문함
				
				//조건 탐색
				int diff = Math.abs(graph[x][y] - graph[nx][ny]);
				
				if(L <= diff && diff <= R ) { //국경선 열기 가능
					queue.add(new int[] {nx, ny});
					visited[nx][ny] = true;
					list.add(new int[] {nx,ny});
					
					sum += graph[nx][ny];
				}
			}
		}
	}

}