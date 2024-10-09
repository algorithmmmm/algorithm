package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_대기업승범이네 {
	static int N;
	static List<Integer>[] graph;
	static int[] abilities;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
				
		graph = new ArrayList[N + 1];
		for(int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 2; i < N + 1; i++) {
			int sasu = Integer.parseInt(st.nextToken());
			graph[sasu].add(i);
		}
		
		abilities = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			abilities[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N + 1][2]; //0: 멘토 관계X, 1: 멘토 관계
		for(int i = 1; i < N + 1; i++) {
			dp[i][0] = -1;
			dp[i][1] = -1;
		}
		
		System.out.println(dfs(1, 0));
	}
	
	private static int dfs(int v,int hasMentor) {
		//이미 값이 있으면 값 리턴
		if(dp[v][hasMentor] != -1) {
			return dp[v][hasMentor];
		}
		
		//초기화
		dp[v][hasMentor] = 0;
		
		//멘토 관계 없이 탐색
		for(int child : graph[v]) {
			dp[v][hasMentor] += dfs(child, 0);
		}

		//부모와의 멘토 관계가 아닐 경우 자식과 멘토 맺기 가능 
		if(hasMentor == 0){
			int curSum = dp[v][hasMentor]; //멘토X일때 현재까지의 합
			
			for(int child : graph[v]) {
				int nextSum = dfs(child, 1) + abilities[v] * abilities[child]; //멘토 맺을 경우의 합
				
				dp[v][hasMentor] = Math.max(dp[v][hasMentor], curSum - dfs(child, 0) + nextSum);
			}
		}
		
		return dp[v][hasMentor];
	}

}
