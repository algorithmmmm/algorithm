package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_진우의민트초코우유 {
	static int N, H;
	static int[][] graph;
	static int[] house;
	static List<int[]> milkList;
	static int max;
//	static boolean[][] visited;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		
		house = new int[2]; //집 좌표
		milkList = new ArrayList<>(); //우유 좌표 리스트
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				
				if(graph[i][j] == 1) { //집
					house[0] = i;
					house[1] = j;
				} else if(graph[i][j] == 2) { //우유
					milkList.add(new int[] {i, j});
				}
			}
		}
		
		max = 0;
//		visited = new boolean[N][N];
		visited = new boolean[milkList.size()]; //우유 방문 체크
		
		dfs(house[0], house[1], M, 0); //집 좌표, 초기 체력, 마신 우유
		
		System.out.println(max);
	}

	public static void dfs(int x, int y, int hp, int count) {
		if(Math.abs(x - house[0]) + Math.abs(y - house[1]) <= hp) { //집까지 돌아갈 수 있음
			max = Math.max(max, count); //갱신
		}
		
		for(int i = 0; i < milkList.size(); i++) { //모든 우유 탐색
			int nx = milkList.get(i)[0];
			int ny = milkList.get(i)[1];
			
			if(!visited[i]) { //방문x
				int dist = Math.abs(x - nx) + Math.abs(y - ny);
				if(dist <= hp) { //이동 가능
					visited[i] = true;
					dfs(nx, ny, hp - dist + H, count + 1); //탐색
					visited[i] = false;
				}
			}
		}
	}

}
