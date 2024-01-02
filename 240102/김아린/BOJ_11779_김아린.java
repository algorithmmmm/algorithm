package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_최소비용구하기2 {
	static List<Node>[] graph;
	static int[] dist;
	static boolean[] visited;
	static int[] route; //경로 저장
	
	static class Node {
		int to, cost;
		
		Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); //도시의 개수
		int m = Integer.parseInt(br.readLine()); //버스의 개수
		
		graph = new ArrayList[n + 1];
		for(int i = 1; i < n + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) { //간선 정보 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, cost));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		//다익스트라
		dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE); //비용 무한으로 초기화
		
		visited = new boolean[n + 1];
		route = new int[n + 1];
		
		
		dijkstra(start);
		
		//경로 구하기
		Stack<Integer> stack = new Stack<>();
		stack.add(end);
		
		int count = 1;
		int idx = end;
		
		while(idx != start) {
			idx = route[idx];
			count++;
			stack.add(idx);
		}
		
		//출력
		System.out.println(dist[end]); //최소 비용
		System.out.println(count); //최소 비용 경로에 포함되어 있는 도시 개수
		//최소 비용을 갖는 경로
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}
	
	public static void dijkstra(int start) {
		Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost); //최소힙
		
		queue.add(new Node(start, 0)); //시작점 넣음
		dist[start] = 0;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int now = node.to;
			
			if(visited[now]) continue;
			visited[now] = true;
			
			for(Node next : graph[now]) { //다음 경로에서
				if(dist[next.to] > dist[now] + next.cost) { //최소 경로 갱신
					dist[next.to] = dist[now] + next.cost;
					queue.add(new Node(next.to, dist[next.to]));
					route[next.to] = now; //다음 도시에 현재 도시 저장
				}
			}
		}
	}

}
