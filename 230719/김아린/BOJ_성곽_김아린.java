package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2234 {
	static int N, M;
	static int[][] map; //성의 지도
	static boolean[][] visited; //방문 여부
	static int[] dx = {0, -1, 0, 1}; //서, 북, 동, 남
	static int[] dy = {-1, 0, 1, 0};
	
	static class Room {
		int x, y;
		
		public Room(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); //열
		M = Integer.parseInt(st.nextToken()); //행
		
		map = new int[M][N];
		
		for(int i = 0; i < M; i++) { //방 정보 입력
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//처리
		visited = new boolean[M][N];
		int num_room = 0; //1. 이 성에 있는 방의 개수
		int max_area1 = 0; //2. 가장 넓은 방의 넓이
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					max_area1 = Math.max(max_area1, bfs(i, j));
					num_room++;
				}
			}
		}
		
		//3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
		int max_area2 = 0;
		for(int i = 0; i < M; i++) { //모든 좌표의
			for(int j = 0; j < N; j++) {
				for(int d = 0; d < 4; d++) { //사방에 대해 탐색
					visited = new boolean[M][N]; //초기화
					
					if((map[i][j] & (1 << d)) == 1) { //벽이 있으면
						map[i][j] -= (1 << d); //벽 제거하기
						max_area2 = Math.max(max_area2, bfs(i, j)); //그 좌표에서 bfs 돌리기(= 그 벽이 없었다면 나올 수 있는 방 넓이 반환)
						map[i][j] += (1 << d); //벽 원상 복구
					}
				}
			}
		}
		
		//출력
		System.out.println(num_room);
		System.out.println(max_area1);
		System.out.println(max_area2);
	}

	public static int bfs(int i, int j) {
		Queue<Room> queue = new LinkedList<>();
		int area = 1;
		
		queue.add(new Room(i, j));
		visited[i][j] = true;
		
		while(!queue.isEmpty()) {
			Room room = queue.poll();
			
			for(int d = 0; d < 4; d++) { //4방 탐색
				int nx = room.x + dx[d];
				int ny = room.y + dy[d];
				
				if(0 <= nx && nx < M && 0 <= ny && ny < N) { //범위 안에 있고
					if((map[room.x][room.y] & (1 << d)) == 0 && !visited[nx][ny]) { //벽X, 방문한적X
						queue.add(new Room(nx, ny));
						visited[nx][ny] = true;
						area++;
					}
				}
			}
		}
		
		return area;
	}
}