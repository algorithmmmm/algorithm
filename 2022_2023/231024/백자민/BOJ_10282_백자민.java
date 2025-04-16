package day1024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_10282_백자민 {
	
	static ArrayList<Point>[] list;
	static int[] dist;
	static boolean[] visit;
	
	static void dijkstra(int start) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		
		dist[start] = 0;
		pq.offer(new Point(start, 0));
		
		while(!pq.isEmpty()) {
			int tmp = pq.poll().depend;
			
			if(visit[tmp]) continue;
			
			visit[tmp] = true;
			
			for(Point n: list[tmp]) {
				if(dist[n.depend] <= dist[tmp]+n.time) continue;
				
				dist[n.depend] = dist[tmp]+n.time;
				pq.offer(new Point(n.depend, dist[n.depend]));
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list = new ArrayList[n+1];
			dist = new int[n+1];
			visit = new boolean[n+1];
			
			for(int i=1;i<=n;i++) {
				dist[i] = Integer.MAX_VALUE;
				list[i] = new ArrayList<>();
			}
			
			for(int i=0;i<d;i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				list[b].add(new Point(a,s));
			} //end input
			
			dijkstra(c);
			
			int comCnt=0, totalTime=0;
			
			for(int i=1;i<n+1;i++) {
				if(dist[i]<Integer.MAX_VALUE) {
					comCnt++;
					totalTime = Math.max(totalTime, dist[i]);
				}
			}
			
			sb.append(comCnt+" "+totalTime+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static class Point implements Comparable<Point> {
		int depend, time;
		
		public Point(int depend, int time) {
			this.depend = depend;
			this.time = time;
		}
		
		@Override
		public int compareTo(Point o) {
			return this.time - o.time;
		}
	}
}
