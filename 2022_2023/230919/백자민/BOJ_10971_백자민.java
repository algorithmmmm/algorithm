package day0919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10971_백자민 {
	
	static int N,cost;
	
	static int[][] map;
	static boolean[] visit;
	
	static void dfs(int start, int temp, int cnt, int sum) {
		if(cnt==N-1) { //모든 노드를 방문한 경우
			if(map[temp][start]!=0) {
				sum += map[temp][start]; //처음 노드로 복귀 
				cost = Math.min(cost, sum);
			}
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(visit[i] || map[temp][i]==0) continue;
			
			visit[i] = true;
			dfs(start, i, cnt+1, sum+map[temp][i]);
			visit[i] = false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visit = new boolean[N];
		
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cost = Integer.MAX_VALUE;
		for(int i=0;i<N;i++) {
			visit[i] = true;
			dfs(i,i,0,0);
		}
		
		System.out.println(cost);
	}
}
