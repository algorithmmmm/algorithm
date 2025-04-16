import java.io.*;
import java.util.*;

public class BOJ_PuyoPuyo {
	static final int H = 12;
	static final int W = 6;
	static char[][] graph;
	static boolean[][] visited;
	static int dx[] = {1, -1, 0, 0}; //상하좌우
    static int dy[] = {0, 0, 1, -1};
    static Queue<Point> queue2; //연속된 블록 담는 큐
    
    static class Point { //좌표
		int x, y; //행, 열
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		graph = new char[H][W]; //필드
		
		for(int i = 0; i < H; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < W; j++) {
				graph[i][j] = line.charAt(j);
			}
		}
		
		//구현
		int answer = 0; //몇연쇄
		
		while(true) {
			visited = new boolean[H][W];
			
			//여러 그룹 동시에 터트리기
			boolean isBroken = false;
			
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					if(!visited[i][j] && graph[i][j] != '.') { //색깔인 경우
						//연결된 같은 색 뿌요 찾아서
						queue2 = new LinkedList<>();
						bfs(i, j, graph[i][j]);
						
						//4개이상이면 없애기
						if(queue2.size() >= 4) {
							remove();
							isBroken = true;
						}
					}
				}
			}
			
			//하나도 터지지 않는다면 중단
			if(!isBroken) {
				break;
			}
			
			//내리기
			down();
			
			answer++;
		}
		
		//출력
		System.out.println(answer);
	}

	public static void bfs(int i, int j, char color) {
		Queue<Point> queue1 = new LinkedList<>();
		
		queue1.add(new Point(i, j));
		queue2.add(new Point(i, j));
		visited[i][j] = true;
		
		while(!queue1.isEmpty()) {
			Point point = queue1.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = point.x + dx[d];
				int ny = point.y + dy[d];
				
				if(0 <= nx && nx < H && 0 <= ny && ny < W) {
					if(!visited[nx][ny] && graph[nx][ny] == color) {
						queue1.add(new Point(nx, ny));
						queue2.add(new Point(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
	}
	
	public static void remove() {
		for(Point point : queue2) {
			graph[point.x][point.y] = '.';
		}
	}
	
	public static void down() {
		for(int i = 0; i < W; i++) { //세로줄마다 확인
			for(int j = H - 2; j >= 0; j--) {
				if(graph[j][i] != '.') { //문자면
					int idx = j;
					
					while(true) { //문자만날때까지 or 바닥까지 내리기
						idx++;
						if(idx == H || graph[idx][i] != '.') {
							char tmp = graph[j][i];
							graph[j][i] = graph[idx - 1][i];
							graph[idx - 1][i] = tmp;
							
							break;
						}
					}
				}
			}
		}
	}

}