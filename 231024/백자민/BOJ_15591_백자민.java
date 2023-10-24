package day1024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15591_백자민 {
	
	static int N;
	static ArrayList<Point>[] list;
	
	static int bfs(int k, int v) {
		int cnt = 0;
		
		Queue<Integer> q = new LinkedList<>();
		boolean[] visit = new boolean[N+1];
		
		visit[v] = true;
		q.offer(v);
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			
			for(int i=0,size=list[tmp].size();i<size;i++) {
				if(!visit[list[tmp].get(i).to] && list[tmp].get(i).weight>=k) {
					cnt++;
					q.offer(list[tmp].get(i).to);
					visit[list[tmp].get(i).to] = true;
				}
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[from].add(new Point(to,w));
			list[to].add(new Point(from,w));
		}
		
		StringBuilder sb = new StringBuilder();
		for(int q=0;q<Q;q++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			sb.append(bfs(k,v)+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static class Point {
		int to, weight;
		
		public Point(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}
