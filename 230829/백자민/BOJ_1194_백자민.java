package day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194_백자민 {
	
	static int N,M;
	
	static Point start;
	
	static char[][] map;
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	static int bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][][] visit = new boolean[N][M][64];
		
		q.offer(start);
		visit[start.i][start.j][start.key] = true;
		
		while(!q.isEmpty()) {
			Point temp = q.poll();
			if(map[temp.i][temp.j]=='1') //도착한 경우 
				return temp.cnt; 
			
			for(int d=0;d<4;d++) {
				int ni = temp.i+di[d];
				int nj = temp.j+dj[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
				
				if(map[ni][nj]=='#' || visit[ni][nj][temp.key]) continue;
				
				if(map[ni][nj]>='a' && map[ni][nj] <='f') { //열쇠를 얻은 경우 
					int newKey = 1<<map[ni][nj]-'a';
					newKey = temp.key | newKey;
					
					q.offer(new Point(ni,nj,temp.cnt+1,newKey));
					visit[ni][nj][newKey] = true;
				} else if(map[ni][nj]>='A' && map[ni][nj] <='F') { //문에 도달한 경우 
					//열쇠 갖고 있는지 검사 
					if((temp.key & (1<<(map[ni][nj]-'A'))) > 0) {
						visit[ni][nj][temp.key] = true;
						q.offer(new Point(ni,nj,temp.cnt+1,temp.key));
					}
				} else {
					visit[ni][nj][temp.key] = true;
					q.offer(new Point(ni,nj,temp.cnt+1,temp.key));
				}
			}
			
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for(int i=0;i<N;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				if(map[i][j]=='0')
					start = new Point(i,j,0,0);
			}
		}
		//end input
		
		System.out.println(bfs());
	}
	
	static class Point {
		int i,j,cnt,key;
		public Point(int i, int j, int cnt, int key) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.key = key;
		}
	}
}
