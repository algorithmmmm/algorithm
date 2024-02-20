package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_색종이붙이기 {
	static int[][] graph;
	static List<int[]> list;
	static int[] paper;
//	static boolean[][] visited;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		graph = new int[10][10];
		list = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 10; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				
				if(graph[i][j] == 1) {
					list.add(new int[] {i, j});
				}
			}
		}
		
//		visited = new boolean[10][10];
		paper = new int[6]; //사용한 색종이 개수
		min = Integer.MAX_VALUE; //정답 : 최소 개수
		
		dfs(0, 0); //list의 인덱스
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	public static void dfs(int idx, int depth) {
		if(idx == list.size()) { //1적힌 칸 다 덮음
			min = Math.min(min, depth); //최솟값 갱신
			return;
		}
		
		if (min <= depth) return; //가지치기
		
		int x = list.get(idx)[0];
		int y = list.get(idx)[1];
		
		if(graph[x][y] == 0) { //이미 방문함
			dfs(idx + 1, depth); //종이 안붙이고 탐색
			return;
		}
		
		for(int size = 5; size >= 1; size--) { //사이즈5부터 1까지 살펴보기
			if(paper[size] < 5 && possible(x, y, size)) { //해당 사이즈 5개 미만 붙임, 붙일 수 있음
				//붙이기
				check(x, y, size, 0);
				paper[size]++;
				dfs(idx + 1, depth + 1);
				//안붙이기
				paper[size]--;
				check(x, y, size, 1);
			}
		}
		
	}

	public static boolean possible(int x, int y, int size) {
		for(int i = x; i < x + size; i++) {
			for(int j = y; j < y + size; j++) {
				if(i >= 10 || j >= 10) { //범위 넘어감
					return false;
				}
				
				if(graph[i][j] == 0) { //못붙임
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void check(int x, int y, int size, int mark) { //체크하기
		for(int i = x; i < x + size; i++) {
			for(int j = y; j < y + size; j++) {
				graph[i][j] = mark;
			}
		}
	}

}
