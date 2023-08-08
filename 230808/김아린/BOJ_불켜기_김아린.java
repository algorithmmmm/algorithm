import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static Room[][] graph;
	static boolean[][] isOn; //방이 켜져있는지
	static boolean[][] visited; //스위치 키러 방문 여부
	static boolean[][] canMove; //이동할 수 있는 곳
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	static class Room {
		List<Point> list; //연결된 스위치 좌표 리스트
		
		Room(List<Point> list) {
			this.list = list;
		}
	}
	
	static class Point { //좌표
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new Room[N + 1][N + 1];
		for(int i = 1; i <= N; i++) { //리스트 초기화
			for(int j = 1; j <= N; j++) {
				graph[i][j] = new Room(new ArrayList<>());
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[x][y].list.add(new Point(a, b)); //방에 있는 스위치 연결
		}
		
		isOn = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];
		canMove = new boolean[N + 1][N + 1];
		
		bfs(1, 1); //출발점에서 탐색 시작
		
		int count = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(isOn[i][j]) {
					count++;
				}
			}
		}
		
		System.out.println(count);
	}

	public static void bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.add(new Point(i, j));
		isOn[i][j] = true;
		visited[i][j] = true;
		canMove[i][j] = true;
		
		while(!queue.isEmpty()) {
			Point point = queue.poll();
			
			for(Point p : graph[point.x][point.y].list) { //연결된 방 불
				if(!isOn[p.x][p.y]) { //안 켜져 있으면
					isOn[p.x][p.y] = true; //켜기
				}
				
				//진작에 켜졌지만 나중에 갈 수 있는 곳도 방문해주기
				if(canMove[p.x][p.y] && !visited[p.x][p.y]) { //이동 가능, 방문x
					queue.add(new Point(p.x, p.y));
					visited[p.x][p.y] = true;
				}
			}
			
			for(int d = 0; d < 4; d++) {//사방으로 이동 
				int nx = point.x + dx[d];
				int ny = point.y + dy[d];
				
				if(1 <= nx && nx <= N && 1 <= ny && ny <= N) { //범위 안
					canMove[nx][ny] = true; //움직일 수 있는 곳 표시
					
					if(isOn[nx][ny] && !visited[nx][ny]) { //켜져 있음, 방문x
						queue.add(new Point(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
	}

}