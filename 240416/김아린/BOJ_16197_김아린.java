package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_두동전 {
	static int N, M;
	static char[][] graph;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static List<int[]> coinList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new char[N][M];
		coinList = new ArrayList<>(); //동전 좌표 저장 
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				graph[i][j] = line.charAt(j);
				
				if(graph[i][j] == 'o') coinList.add(new int[] {i, j});
			}
		}
		
		System.out.println(bfs());
	}

	public static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		
		int x1 = coinList.get(0)[0];
		int y1 = coinList.get(0)[1];
		int x2 = coinList.get(1)[0];
		int y2 = coinList.get(1)[1];
		
		queue.add(new int[] {x1, y1, x2, y2});
		visited[x1][y1][x2][y2] = true;
		
		int count = 0; //정답 : 버튼 횟수 
		
		while(!queue.isEmpty()) {
			if(count >= 10) return -1; //10번보다 많이 누름 
			
			int size = queue.size();
			
			count++;
			for(int s = 0; s < size; s++) { //큐 사이즈만큼 끊어서 탐색 
				int[] p = queue.poll();
				
				for(int d = 0; d < 4; d++) { //사방탐색 
					int nx1 = p[0] + dx[d];
					int ny1 = p[1] + dy[d];
					int nx2 = p[2] + dx[d];
					int ny2 = p[3] + dy[d];
					
					if(isInside(nx1, ny1) && isInside(nx2, ny2)) { //보드 안에 있음 
						//벽에 있으면 그대로 
						if(graph[nx1][ny1] == '#') {
							nx1 = p[0];
							ny1 = p[1];
						}
						
						if(graph[nx2][ny2] == '#') {
							nx2 = p[2];
							ny2 = p[3];
						}
						//방문 안했으면 이동하기
						if(!visited[nx1][ny1][nx2][ny2]) {
							queue.add(new int[] {nx1, ny1, nx2, ny2});
							visited[nx1][ny1][nx2][ny2] = true;
						}
					} else if(!isInside(nx1, ny1) && !isInside(nx2, ny2)) { //둘다 벗어남 
						continue;
					} else if(!isInside(nx1, ny1)) { //1번 탈출 
						return count;
					} else if(!isInside(nx2, ny2)) { //2번 탈출 
						return count;
					}
				}
			}
		}
		
		return -1;
	}
	
	public static boolean isInside(int nx, int ny) { //범위 안에 있는지 확인 
		if(0 <= nx && nx < N && 0 <= ny && ny < M) {
			return true;
		}
		return false;
	}

}
