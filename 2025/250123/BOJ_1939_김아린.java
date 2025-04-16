package algorithm;

import java.util.*;
import java.io.*;

public class BOJ_중량제한 {
	static List<Node>[] graph;
	static boolean[] visited;
	static boolean isPossible;
	
	static class Node {
		int to;
		int cost;
		
		Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int low = 0; //중량의 최소
		int high = 0; //중량의 최대
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			graph[A].add(new Node(B, C));
			graph[B].add(new Node(A, C));
			
			high = Math.max(high, C);
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		while(low <= high) {
			int mid = (low + high) / 2;
			isPossible = false;
			visited = new boolean[N + 1];
			
			dfs(v1, v2, mid);
			
			if(isPossible) { //가능
				low = mid + 1; //중량 올리기
				answer = mid;
			} else {
				high = mid - 1; //중량 내리기
			}
		}
		
		System.out.println(answer);
	}
	
	static void dfs(int cur, int goal, int mid) {
		if(cur == goal) {
			isPossible = true;
			return;
		}
		
		visited[cur] = true;
		
		for(Node node : graph[cur]) {
			if(!visited[node.to] && mid <= node.cost) {
				dfs(node.to, goal, mid);
			}
		}
	}

}
