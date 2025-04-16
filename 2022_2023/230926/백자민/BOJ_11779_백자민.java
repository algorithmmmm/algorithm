package day0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_11779_백자민 {
	
	static int N,M,from,to;
	
	static int[] cost, prev;
	
	static ArrayList<Point>[] list;
	
	static void dijkstra() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(from, 0));
		cost[from] = 0;
		
		while(!pq.isEmpty()) {
			Point tmp = pq.poll();
			int e = tmp.to;
			
			if(cost[e] < tmp.w) continue;
			
			for(Point p: list[e]) {
				if(cost[p.to] > cost[e] + p.w) {
					cost[p.to] = cost[e]+p.w;
					prev[p.to] = e;
					pq.offer(new Point(p.to, cost[p.to]));
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N+1];
		
		for(int i=1;i<=N;i++)
			list[i] = new ArrayList<>();
		
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[s].add(new Point(e,w));
		}
		
		st = new StringTokenizer(br.readLine());
		from = Integer.parseInt(st.nextToken());
		to = Integer.parseInt(st.nextToken());
		//end input
		
		cost = new int[N+1];
		prev = new int[N+1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		dijkstra();
		StringBuilder sb = new StringBuilder();
		sb.append(cost[to]+"\n"); //최단거리
		
		//경로 찾기
		Stack<Integer> stack = new Stack<>();
		stack.push(to);
		int cnt = 0;
		while(prev[to]!=0) {
			stack.push(prev[to]);
			to = prev[to];
			cnt++;
		}
		cnt++;
		sb.append(cnt+"\n"); //도시 개수
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop()+" ");
		}
		
		System.out.println(sb);
		
		
		
	}
	
	static class Point implements Comparable<Point> {
		int to, w;
		
		public Point(int to, int w) {
			this.to = to;
			this.w = w;
		}
		
		@Override
		public int compareTo(Point o) {
			return this.w - o.w;
		}
	}
}
