package day1212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_백자민 {
	
	static int N,M,cnt;
	
	static int[][] map;
	
	static int[] di = {-1,0,1,0};
	static int[] dj = {0,1,0,-1};
	
	static void clean(int ti, int tj, int d) {
		map[ti][tj] = -1;
		
		for(int k=0;k<4;k++) {
			d = (d+3)%4; //반시계 
			
			int ni = ti+di[d];
			int nj = tj+dj[d];
			
			if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
			
			if(map[ni][nj]!=0) continue;
			
			cnt++;
			clean(ni,nj,d);
			return;
		}
		
		int nd = (d+2)%4; //청소를 못한 경우 후진
		int ni = ti+di[nd];
		int nj = tj+dj[nd];
		
		if(ni>=0 && ni<N && nj>=0 && nj<M && map[ni][nj]!=1) {
			clean(ni,nj,d); //후진은 현재 방향 유지
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int si = Integer.parseInt(st.nextToken());
		int sj = Integer.parseInt(st.nextToken());
		int sd = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} //end input
		
		cnt = 1;
		clean(si,sj,sd);
		System.out.println(cnt);
	}
}
