package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5567_김아린 {
	static int n; //상근이의 동기의 수
	static int[][] graph; //친구 관계
	static boolean[] checked; //결혼식에 초대할 동기 체크

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine()); //리스트의 길이
		
		graph = new int[n + 1][n + 1]; 
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a][b] = graph[b][a] = 1; //서로 친구이다
		}
		
		//구현
		checked = new boolean[n + 1];
		dfs(1, 0); //상근이의 친구 탐색
		
		int count = 0; //결혼식에 초대하는 동기의 수
		for(boolean c : checked) { //결혼식 초대할 친구 세기
			if(c) count++; 
		}
		
		//출력
		System.out.println(count);
	}

	public static void dfs(int v, int depth) {
		if(depth == 2) { //친구의 친구까지 탐색
			return;
		}
		
		for(int i = 2; i < n + 1; i++) {
			if(graph[v][i] == 1) { //친구면
				checked[i] = true; //결혼식에 초대할 것
				dfs(i, depth + 1); //탐색하기
			}
		}
	}

}
