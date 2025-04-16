package day0919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1261_백자민 {
	static int N,M,answer;
	static char[][] map;
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for(int i=0;i<N;i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		PriorityQueue<Point> q = new PriorityQueue<>();
		q.offer(new Point(0,0,0));
		
		boolean[][] visit = new boolean[N][M];
		visit[0][0] = true;
		
		answer = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			Point tmp = q.poll();
			
			if(tmp.i==N-1 && tmp.j==M-1) {
				answer = Math.min(answer, tmp.cnt);
			}
			
			for(int d=0;d<4;d++) {
				int ni = tmp.i+di[d];
				int nj = tmp.j+dj[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
				
				if(visit[ni][nj]) continue;
				
				visit[ni][nj] = true;
				
				if(map[ni][nj]=='1')
					q.offer(new Point(ni,nj,tmp.cnt+1));
				else
					q.offer(new Point(ni,nj,tmp.cnt));
			}
		}
		
		System.out.println(answer);
		
	}
	
	static class Point implements Comparable<Point> {
		int i,j,cnt;
		public Point(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Point o) {
			return this.cnt-o.cnt;
		}
		
	}
}
