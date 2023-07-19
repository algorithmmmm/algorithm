package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b2206 {
	static int N, M;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = {-1, -1, 0, 0}; //상, 하, 좌, 우
	static int[] dy = {0, 0, -1, 1};
	
	static class Room {
		int x, y, dist;
		boolean isCrashed;
		
		public Room(int x, int y, int dist, boolean isCrashed) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.isCrashed = isCrashed;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); //행
		M = Integer.parseInt(st.nextToken()); //열
		
		map = new int[N + 1][M + 1]; //맵
		
		for(int i = 1; i <= N; i++) { //맵 정보 입력
			String line = br.readLine();
			for(int j = 1; j <= M; j++) {
				map[i][j] = line.charAt(j - 1) - '0';
			}
		}
		
		visited = new boolean[N + 1][M + 1][2]; //행, 열, 벽 안부쉈음 or 부쉈음
		int answer = bfs(1, 1); //(1, 1)에서 시작
		
		System.out.println(answer);
	}

	public static int bfs(int i, int j) {
		Queue<Room> queue = new LinkedList<>();
		
		queue.add(new Room(i, j, 0, false)); 
		visited[i][j][0] = true;
		
		while(!queue.isEmpty()) {
			Room room = queue.poll();
			
			if(room.x == N && room.y == M) { //도착했으면 리턴
				return room.dist;
			}
			
			for(int d = 0; d < 4; d++) { //4방 탐색
				int nx = room.x + dx[d];
				int ny = room.y + dy[d];
				
				if(1 <= nx && nx <= N && 1 <= ny && ny <= M) { //범위 안에 있고
					if(map[nx][ny] == 0) { //벽이 아님
						if(!room.isCrashed && !visited[nx][ny][0]) { //부순적X, 방문한적X
							queue.add(new Room(nx, ny, room.dist +1, false));
							visited[nx][ny][0] = true;
						}else if(room.isCrashed && !visited[nx][ny][1]) { //부순적O, 방문한적X
							queue.add(new Room(nx, ny, room.dist +1, true));
							visited[nx][ny][1] = true;
						}
					} else if(map[nx][ny] == 1) { //벽이 있음
						if(!room.isCrashed) { //부순적X, 방문한적X
							queue.add(new Room(nx, ny, room.dist +1, true));
							visited[nx][ny][1] = true;
						}
					}
				}
			}
		}
		
		return -1; //도착 불가능
	}

}
