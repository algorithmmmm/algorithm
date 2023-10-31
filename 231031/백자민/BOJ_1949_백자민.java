package day1031;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1949_백자민 {
	
	static int N;
	
	static int[] citizen;
	static int[][] dp;
	
	static ArrayList<Integer>[] map;
	
	static void find(int tmp, int parent) {
		for(int m: map[tmp]) {
			if(m==parent) continue;
			
			find(m, tmp);
			dp[tmp][0] += Math.max(dp[m][0], dp[m][1]);
			dp[tmp][1] += dp[m][0];
		}
		
		dp[tmp][1] += citizen[tmp];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		citizen = new int[N+1];
		map = new ArrayList[N+1];
		dp = new int[N+1][2];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			citizen[i] = Integer.parseInt(st.nextToken());
			map[i] = new ArrayList<>();
		}
		
		for(int i=1;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			map[from].add(to);
			map[to].add(from);
		}//end input
		
		find(1,0);
		
		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}
}
