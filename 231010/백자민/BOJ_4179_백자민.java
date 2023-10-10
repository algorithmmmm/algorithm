package day1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179_백자민 {
	
	static int R,C,answer;
	
	static char[][] map;
	
	static Queue<Point> q,flist;
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	static void print() {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void fire() { //불 번지기 
		for(int i=0,size=flist.size();i<size;i++) {
			Point p = flist.poll();
			for(int d=0;d<4;d++) {
				int ni = p.i+di[d];
				int nj = p.j+dj[d];
				
				if(ni<0 || nj<0 || ni>=R || nj>=C) continue;
				
				if(map[ni][nj]=='#' || map[ni][nj]=='F') continue;
				
				flist.offer(new Point(ni,nj,-1));
				map[ni][nj]='F';
			}
		}
	}
	
	static void move() { //지훈 이동 
		for(int i=0,size=q.size();i<size;i++) {
			Point p = q.poll();
			for(int d=0;d<4;d++) {
				int ni = p.i+di[d];
				int nj = p.j+dj[d];
				
				if(ni<0 || nj<0 || ni>=R || nj>=C) {
					answer = Math.min(answer, p.cnt);
					return;
				}
				
				if(map[ni][nj]!='.') continue;
				
				q.offer(new Point(ni,nj,p.cnt+1));
				map[ni][nj] = 'J';
			}
		}
	}
	
	static void bfs() {		
		while(!q.isEmpty()) {			
			fire();
			move();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		q = new LinkedList<>();
		flist = new LinkedList<>();
		
		for(int i=0;i<R;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<C;j++) {
				if(map[i][j]=='F') flist.offer(new Point(i,j,-1));
				if(map[i][j]=='J') q.offer(new Point(i,j,1));
			}
		}
		
		answer = Integer.MAX_VALUE;
		bfs();
		
		if(answer==Integer.MAX_VALUE)
			System.out.println("IMPOSSIBLE");
		else
			System.out.println(answer);
		
	}
	
	static class Point {
		int i,j,cnt;
		public Point(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
}
