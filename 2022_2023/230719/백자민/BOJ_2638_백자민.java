package day0719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638_백자민 {
	
	static int N,M,cheeseCnt;
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	static int[][] map;
	
	static ArrayList<int[]> cheese;
	
	static void melt() {
		for(int c=0;c<cheese.size();c++) {
			int i = cheese.get(c)[0];
			int j = cheese.get(c)[1];
			
			int cnt = 0;
			for(int d=0;d<4;d++) { //공기와 닿은 면 카운팅 
				int ni = i+di[d];
				int nj = j+dj[d];
				
				if(map[ni][nj]==-1)
					cnt++;
			}
			
			if(cnt>=2) {
				map[i][j]=0;
				cheeseCnt--;
				cheese.remove(c);
				c--;
			}
		}
	}
	
	static void find() { //바깥영역 찾기 
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visit = new boolean[N][M];
		q.add(new int[] {0,0});
		map[0][0] = -1;
		visit[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int i = tmp[0];
			int j = tmp[1];
			
			for(int d=0;d<4;d++) {
				int ni = i+di[d];
				int nj = j+dj[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
				if(visit[ni][nj] || map[ni][nj]==1) continue;
								
				map[ni][nj] = -1;
				q.add(new int[] {ni,nj});
				visit[ni][nj] = true;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheeseCnt = 0;
		
		map = new int[N][M];
		cheese = new ArrayList<int[]>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					cheese.add(new int[] {i,j}); //치즈 위치 기억해두기 
					cheeseCnt++;
				}
			}
		}
		int answer = 0;
		while(cheeseCnt>0) {
			answer++;
			find();
			melt();
		}
		System.out.println(answer);
	}
}
