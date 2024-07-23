package algorithm;

import java.util.*;
import java.io.*;

public class BOJ_합리적인이동경로 {
	static int N;
	static List<Node>[] graph;
	static int[] dist;
	static int[] dp;
	
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
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N + 1];
		for(int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			graph[A].add(new Node(B, C));
			graph[B].add(new Node(A, C));
		}
		
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dijkstra(2); //T(2)~모든 정점까지 최단거리 구하기
		
		dp = new int[N + 1]; //경로 개수 저장 
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		dp[2] = 1; //초기화 
		System.out.println(dfs(1)); //1부터 시작 
	}

	public static void dijkstra(int start) {
		Queue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		boolean[] visited = new boolean[N + 1];
		
		q.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int to = node.to;
			
			if(visited[to])	continue;
			visited[to] = true;
			
			for(Node next : graph[to]) {
				if(dist[next.to] > dist[to] + next.cost) {
					dist[next.to] = dist[to] + next.cost;
					q.add(new Node(next.to, dist[next.to]));
				}
			}
		}
	}
	
	public static int dfs(int cur) {
		if(dp[cur] != Integer.MAX_VALUE) return dp[cur]; //이미 방문함 
		
		dp[cur] = 0; //초기화 
		
		for(Node next : graph[cur]) {
			if(dist[cur] > dist[next.to]) { //더 합리적인 경로면 탐색 
				dp[cur] += dfs(next.to); //경로 개수 누적 
			}
        }
        return dp[cur];
	}

}
