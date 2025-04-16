package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_백조의호수 {
	static int R, C;
	static char[][] graph;
	static int[][] swan; //백조 위치
	static Queue<int[]> waterQueue; //물 탐색 큐
	static Queue<int[]> swanQueue; //백조 탐색 큐
	static boolean[][] visited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new char[R][C];
		visited = new boolean[R][C];
		
		swan = new int[2][2];
		waterQueue = new LinkedList<>();
		swanQueue = new LinkedList<>();
		
		int idx = 0;
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				graph[i][j] = line.charAt(j);
				
				if(graph[i][j] == 'L') {
					swan[idx][0] = i;
					swan[idx++][1] = j;
					waterQueue.add(new int[] {i, j}); //백조가 있는 곳도 물
				} else if(graph[i][j] == '.') {
					waterQueue.add(new int[] {i, j});
				}
			}
		}
		
		//백조 초기 위치
		swanQueue.add(new int[] {swan[0][0], swan[0][1]});
		visited[swan[0][0]][swan[0][1]] = true;
		
		int answer = 0;
		while(!hasMet()) {
			melt();
			answer++;
		}
		
		System.out.println(answer);
	}

	public static boolean hasMet() {
		Queue<int[]> nextQueue = new LinkedList<>();
		
		while(!swanQueue.isEmpty()) {
			int[] p = swanQueue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = p[0] + dx[d];
				int ny = p[1] + dy[d];
				
				//범위 초과, 이미 방문
				if(0 > nx || nx >= R || 0 > ny || ny >= C) continue;
				if(visited[nx][ny]) continue;
				
				//다른 백조 만남
				if(nx == swan[1][0] && ny == swan[1][1]) return true;
				
				if(graph[nx][ny] == '.') { //물 만남
					swanQueue.add(new int[] {nx, ny});
					visited[nx][ny] = true;
				} else if(graph[nx][ny] == 'X') { //얼음 만남
					nextQueue.add(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
		
		swanQueue = nextQueue; //백조의 위치 옮겨주기
		
		return false;
	}
	
	public static void melt() {
		int size = waterQueue.size();
		
		for(int s = 0; s < size; s++) {
			int[] p = waterQueue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = p[0] + dx[d];
				int ny = p[1] + dy[d];
				
				if(0 > nx || nx >= R || 0 > ny || ny >= C) continue;
				
				if(graph[nx][ny] == 'X') { //빙판 만남
					graph[nx][ny] = '.';
					waterQueue.add(new int[] {nx, ny});
				}
			}
		}
	}

}
