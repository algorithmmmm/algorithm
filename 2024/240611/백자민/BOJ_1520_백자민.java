package day240611;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1520_백자민 {
	
	static int M,N;
	
	static int[][] map, dp;
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	static int dfs(int ti, int tj) {
		if(ti==M-1 && tj==N-1) { //도착위치에 도달했을 경우
			return 1; //현재 도착지점까지 도착한 경로가 하나 존재하므로 1 리턴			
		}
		
		if(dp[ti][tj]>-1) { //이미 이전에 (ti,tj)~(M-1,N-1) 까지의 경로를 탐색한 경우
			return dp[ti][tj]; //중복 탐색할 필요 없이 탐색한 경우의 수를 리턴
		}
		
		int tmp=0;
		for(int d=0;d<4;d++) {
			int ni = ti+di[d];
			int nj = tj+dj[d];
			
			if(ni<0 || ni>=M || nj<0 || nj>=N) continue;
			if(map[ni][nj]>=map[ti][tj]) continue; //내리막이 아닌 경우
			
			tmp += dfs(ni, nj);
		}
		
		dp[ti][tj] = tmp;
		return tmp;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} //end input
		
		dp = new int[M][N];
		for(int i=0;i<M;i++) {
			Arrays.fill(dp[i], -1);
		} //init
		
		System.out.println(dfs(0,0));
		
	}
}
