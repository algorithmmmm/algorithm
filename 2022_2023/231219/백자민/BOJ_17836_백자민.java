package day1219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17836_백자민 {
	
	static int N,M,T,min;
	
	static int[][] map;
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];
		
		q.offer(new Point(0,0));
		visit[0][0] = true;
		int time = 0;
		
		while(!q.isEmpty()) {
			
			for(int i=0,size=q.size();i<size;i++) {
				int ti = q.peek().i;
				int tj = q.poll().j;
				
				if(time>T) continue; //시간 초과
				
				if(map[ti][tj]==2) { //검이 있으므로 최단 거리로 이동 가능
					int ans = time+(N-1-ti)+(M-1-tj);
					min = Math.min(min, ans);
					continue;
				} else if(ti==N-1 && tj==M-1) { //도착
					min = Math.min(min, time);
					continue;
				}
				
				for(int d=0;d<4;d++) {
					int ni = ti+di[d];
					int nj = tj+dj[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
					
					if(visit[ni][nj]) continue;
					
					if(map[ni][nj]==1) continue;
					
					visit[ni][nj] = true;
					q.offer(new Point(ni,nj));
				}
			}
			time++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//end input
		
		min = Integer.MAX_VALUE;
		bfs();
		System.out.println(min>T?"Fail":min);
	}
	
	static class Point {
		int i,j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
