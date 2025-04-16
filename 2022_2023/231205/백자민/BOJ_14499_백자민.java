package day1205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499_백자민 {
	
	static int N,M,ti,tj;
	
	static int[] dice;
	
	static int[][] map;
	
	static StringBuilder sb;
	
	static int[] di = {0,0,-1,1}; 
	static int[] dj = {1,-1,0,0};
	
	static void roll(int d) {
		int tmp = dice[2]; //윗면
		if(d==1) {
			dice[2] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[1];
			dice[1] = tmp;
		} else if(d==2) {
			dice[2] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[3];
			dice[3] = tmp;
		} else if(d==3) {
			dice[2] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[0];
			dice[0] = tmp;
		} else {
			dice[2] = dice[0];
			dice[0] = dice[5];
			dice[5] = dice[4];
			dice[4] = tmp;
		}
 	}
	
	static void move(int d) {
		int ni = ti+di[d-1];
		int nj = tj+dj[d-1];
		
		if(ni<0 || ni>=N || nj<0 || nj>=M) return;
		
		roll(d);
		ti = ni;
		tj = nj;
		
		if(map[ni][nj]==0) {
			map[ni][nj] = dice[5];
		} else {
			dice[5] = map[ni][nj];
			map[ni][nj] = 0;
		}
		sb.append(dice[2]+"\n");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		ti = Integer.parseInt(st.nextToken());
		tj = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dice = new int[6]; //주사위 각 면 저장할 배열
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for(int k=0;k<K;k++) {
			int d = Integer.parseInt(st.nextToken());
			move(d);
		}
		
		System.out.println(sb.toString());
	}
	
//	static class Dice {
//		int up,front,back,left,right,down;
//		public Dice(int up, int front, int back, int left, int right, int down) {
//			this.up = up;
//			this.down = down;
//			this.front = front;
//			this.back = back;
//			this.left = left;
//			this.right = right;
//		}
//	}
}
