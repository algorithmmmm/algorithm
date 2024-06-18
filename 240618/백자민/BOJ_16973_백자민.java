package day240618;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_16973_백자민 {
	
	static int N,M,H,W;
	static int Si,Sj,Ei,Ej;
	
	static int[][] map;
	
	static ArrayList<int[]> wallList;
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	static boolean isPossible(int si, int sj) { //다음 이동 위치로 이동이 가능한지 검사하는 함수
		
		int ei = si+H-1,ej = sj+W-1; //직사각형의 오른쪽 아래 꼭짓점
		if(ei>=N || ej>=M) { //직사각형이 범위를 벗어나는 경우
			return false;
		}
		
		for(int[] w: wallList) { //모든 벽의 좌표가 직사각형 안에 포함되는지 체크
			if(w[0]>=si && w[0]<=ei && w[1]>=sj && w[1]<=ej) {
				return false;
			}
		}
		
		return true;
	}
	
	static int bfs() {
		Queue<Rect> q = new LinkedList<>();
		
		q.offer(new Rect(Si, Sj, 0));
		map[Si][Sj] = -1;
		
		while(!q.isEmpty()) {
			Rect tmp = q.poll();
			
			if(tmp.i==Ei && tmp.j==Ej) { //도착위치에 도달한 경우
				return tmp.cnt; 
			}
			
			for(int d=0;d<4;d++) {
				int ni = tmp.i+di[d];
				int nj = tmp.j+dj[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M || map[ni][nj]<0) continue;
				if(!isPossible(ni,nj)) continue; //벽이 있어 이동이 불가능한 경우
				
				q.offer(new Rect(ni,nj,tmp.cnt+1));
				map[ni][nj] = -1;
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		wallList = new ArrayList<>(); //벽의 위치를 저장할 리스트
		
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j]==1) { 
					wallList.add(new int[] {i,j});
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		Si = Integer.parseInt(st.nextToken())-1;
		Sj = Integer.parseInt(st.nextToken())-1;
		Ei = Integer.parseInt(st.nextToken())-1;
		Ej = Integer.parseInt(st.nextToken())-1;
		//end input
		
		System.out.println(bfs());
	}
	
	static class Rect {
		int i,j,cnt; //현재 좌표와 이동 횟수
		
		public Rect(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
		
	}
}
