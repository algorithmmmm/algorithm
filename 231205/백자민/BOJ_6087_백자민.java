package day1205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_6087_백자민 {
	
	static int W,H,min;
	
	static char[][] map;
	
	static Point start, end;
	
	static int[] di = {-1,0,1,0};
	static int[] dj = {0,1,0,-1};
	
	static void bfs() {
		PriorityQueue<Point> q = new PriorityQueue<>();
		q.offer(start);
		
		int[][][] visit = new int[4][H][W]; //지나갈 때 최소 거울 개수 & 방향 체크
		
		while(!q.isEmpty()) {
			Point tmp = q.poll();
			
			if(tmp.i==end.i && tmp.j==end.j) {
				min = Math.min(min, tmp.cnt);
				continue;
			}
			
			for(int d=0;d<4;d++) {
				int ni = tmp.i+di[d];
				int nj = tmp.j+dj[d];
				
				if(ni<0 || nj<0 || ni>=H || nj>=W) continue;
				
				if(map[ni][nj]=='*') continue; 
				
				if(Math.abs(tmp.d-d)==2) continue;
				
				int ncnt = tmp.d==d?tmp.cnt:tmp.cnt+1;
				
//				if(visit[ni][nj]!=0 && visit[ni][nj]<=ncnt) continue; //더 많은 거울을 사용해서 재방문한 경우
				
				if(visit[d][ni][nj]==0 || visit[d][ni][nj]>ncnt) {
					visit[d][ni][nj] = ncnt; //현재까지 사용한 거울 개수 기록
					q.offer(new Point(ni,nj,d,ncnt));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		
		for(int i=0;i<H;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<W;j++) {
				if(map[i][j]=='C') {
					if(start == null)
						start = new Point(i,j,-10,-1);
					else
						end = new Point(i,j,-10,-1);
				}
			}
		} //end input
		
		min = Integer.MAX_VALUE;
		bfs();
		
		System.out.println(min);
		
	}
	
	static class Point implements Comparable<Point>{
		int i,j,d,cnt;
		
		public Point(int i, int j, int d, int cnt) {
			this.i = i;
			this.j = j;
			this.d = d;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Point o) {
			return this.cnt - o.cnt;
		}
	}
}
