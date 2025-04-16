package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_텀프로젝트 {
	static int[] arr;
	static boolean[] visited;
	static boolean[] cycled;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			arr = new int[n + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for(int i = 1; i < n + 1; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			//탐색하기
			visited = new boolean[n + 1]; //방문했는지
			cycled = new boolean[n + 1]; //사이클 만들었는지    
			answer = n; //정답 : 팀에 속하지 못한 학생 
			
			for(int i = 1; i < n + 1; i++) {
				if(!visited[i]) {
					dfs(i);
				}
			}
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int cur) {
		visited[cur] = true;
		
		int next = arr[cur];
		
		if(!visited[next]) { //방문x
			dfs(next);
		} else { //방문
			if(!cycled[next]) { //이전에 사이클 만들러 방문
				answer--;
				
				while(cur != next) { //사이클 돌기
					answer--;
					next = arr[next];
				}
			}
		}
		
		cycled[cur] = true;
	}

}
