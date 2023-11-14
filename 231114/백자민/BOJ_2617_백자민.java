package day1114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2617_백자민 {
	
	static final int INF = 101;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[N+1][N+1];
		
		for(int[] g: graph) {
			Arrays.fill(g, INF);
		}
		
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			graph[s][e] = 1;
		}//end input
		
		for(int k=1;k<=N;k++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(i==j) continue;
					
					graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
				}
			}
		}
		
		int num = N/2+1;
		int[][] cnt = new int[2][N+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(i==j || graph[i][j]==INF) continue;
				cnt[0][i]++;
				cnt[1][j]++;
			}
		}
		
		int answer = 0;
		for(int i=1;i<=N;i++) {
			if(cnt[0][i]>=num) answer++;
			if(cnt[1][i]>=num) answer++;
		}
		
		System.out.println(answer);
	}
}
