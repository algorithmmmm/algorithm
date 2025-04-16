package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_행성터널 {
	static int N;
	static List<Node>[] graph;

	static class Node {
			int to, cost;
			
			Node(int to, int cost) {
				this.to = to;
				this.cost = cost;
			}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		List<int[]> x_list = new ArrayList<>();
		List<int[]> y_list = new ArrayList<>();
		List<int[]> z_list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			x_list.add(new int[]{i, x});
			y_list.add(new int[]{i, y});
			z_list.add(new int[]{i, z});
		}
		
		//오름차순 정렬
		x_list.sort((o1, o2) -> o1[1] - o2[1]);
		y_list.sort((o1, o2) -> o1[1] - o2[1]);
		z_list.sort((o1, o2) -> o1[1] - o2[1]);
		
		//간선 정보 저장 그래프
		graph = new ArrayList[N];
		for(int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
		}
		
		//좌표별로 인근 정점간의 연결 비용 구하기
		for(int i = 0; i < N - 1; i++) {
			graph[x_list.get(i)[0]].add(new Node(x_list.get(i + 1)[0], Math.abs(x_list.get(i)[1] - x_list.get(i + 1)[1])));	
			graph[x_list.get(i + 1)[0]].add(new Node(x_list.get(i)[0], Math.abs(x_list.get(i)[1] - x_list.get(i + 1)[1])));
			
			graph[y_list.get(i)[0]].add(new Node(y_list.get(i + 1)[0], Math.abs(y_list.get(i)[1] - y_list.get(i + 1)[1])));	
			graph[y_list.get(i + 1)[0]].add(new Node(y_list.get(i)[0], Math.abs(y_list.get(i)[1] - y_list.get(i + 1)[1])));
			
			graph[z_list.get(i)[0]].add(new Node(z_list.get(i + 1)[0], Math.abs(z_list.get(i)[1] - z_list.get(i + 1)[1])));	
			graph[z_list.get(i + 1)[0]].add(new Node(z_list.get(i)[0], Math.abs(z_list.get(i)[1] - z_list.get(i + 1)[1])));
		}
		
		//프림 알고리즘으로 최소 비용 구하기
		System.out.println(prim(0));
	}
	
	static long prim(int start) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		boolean[] visited = new boolean[N];
		
		pq.add(new Node(start, 0));
		
		long total = 0;
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int to = cur.to;
			int cost = cur.cost;
			
			if(visited[to]) continue;
			
			visited[to] = true;
			total += cost;
			
			for(Node next : graph[to]) {
				if(!visited[next.to]) {
					pq.add(next);
				}
			}
		}
		
		return total;
	}

}
