package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_친구비 {
	static int N;
	static int[] arr;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[v].add(w);
			graph[w].add(v);
		}
		
		visited = new boolean[N + 1];
		
		int cost = 0;
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				min = 10000;
				dfs(i);
				cost += min;
			}
		}
		
		if(cost <= K) {
			System.out.println(cost);
		} else {
			System.out.println("Oh no");
		}
	}
	
	public static void dfs(int i) {
		visited[i] = true;
		min = Math.min(min, arr[i]);
		
		for(int n : graph[i]) {
			if(!visited[n]) {
				dfs(n);
			}
		}
	}

}
