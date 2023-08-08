package day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11967_백자민 {
	
	static int N, cnt;
	
	static ArrayList<Point> map[][];
	static boolean[][] visit;
	static boolean[][] light;
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(1,1));
		
		while(!q.isEmpty()) {
			Point tmp = q.poll();
			int ti = tmp.i, tj = tmp.j;
			
			if(!map[ti][tj].isEmpty()) { //스위치가 아직 남아있으면 다 켜기 
				visit = new boolean[N+1][N+1];
				visit[ti][tj] = true;
				
				for(Point p: map[ti][tj]) {
					if(!light[p.i][p.j]) {
						light[p.i][p.j] = true;
						cnt++;
					}
				}
				map[ti][tj].clear();
			}
			
			for(int d=0;d<4;d++) {
				int ni = ti+di[d];
				int nj = tj+dj[d];
				
				if(ni<=0 || ni>N || nj<=0 || nj>N) continue;
				
				if(visit[ni][nj]) continue;
				
				if(!light[ni][nj]) continue;
				
				q.offer(new Point(ni,nj));
				visit[ni][nj] = true;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1][N+1];
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[i][j].add(new Point(r,c));
		}
		
		visit = new boolean[N+1][N+1];
		light = new boolean[N+1][N+1];
		
		visit[1][1] = true;
		light[1][1] = true;
		
		cnt=1;
		bfs();
		System.out.println(cnt);
	}
	
	static class Point {
		int i,j;
		
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
			
		}
	}
}
