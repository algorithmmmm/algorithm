package day0719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ_1238_백자민 {
	
	static int N, M, X, ans;
	
	static int[] std, rstd;
	
	static List<List<Node>> map, rmap;
	
	static void dijkstra(List<List<Node>> list, int[] arr) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visit = new boolean[N+1];
		
		pq.offer(new Node(X,0));
		arr[X] = 0;
		
		while(!pq.isEmpty()) {
			Node tmpN = pq.poll();
			int tmp = tmpN.e;
			
			if(visit[tmp]) continue;
			
			visit[tmp] = true;
			
			for(Node n: list.get(tmp)) {
				if(arr[n.e]>arr[tmp]+n.w) {
					arr[n.e] = arr[tmp]+n.w;
					pq.offer(new Node(n.e, arr[n.e]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		map = new ArrayList<>();
		rmap = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			map.add(new ArrayList<Node>());
			rmap.add(new ArrayList<Node>());
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			map.get(s).add(new Node(e, w));
			rmap.get(e).add(new Node(s,w));
		}
		
		std = new int[N+1];
		rstd = new int[N+1];
		Arrays.fill(std, Integer.MAX_VALUE);
		Arrays.fill(rstd, Integer.MAX_VALUE);
		
		dijkstra(map, std);
		dijkstra(rmap, rstd);
		
		ans = Integer.MIN_VALUE;
		for(int i=1;i<=N;i++) {
			ans = Math.max(ans, std[i]+rstd[i]);
		}
		System.out.println(ans);
		
	}

	static class Node implements Comparable<Node> {
		int e,w;
		
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node n) {
			return w-n.w;
		}
	}
}
