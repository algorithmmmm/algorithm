package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_앱 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] memory = new int[N + 1];
		int[] cost = new int[N + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int costSum = 0;
		for(int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			costSum += cost[i];
		}
		
		//구현
		int[][] dp = new int[N + 1][costSum + 1]; //i번째 앱까지 고려했을 때 j의 비용으로 얻을 수 있는 최대 메모리
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j <= costSum; j++) {
				if(j < cost[i]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);
				}
			}
		}
		
		//비용의 최소 찾기
		int answer = 0;
		for(int i = 0; i <= costSum; i++) {
			if(dp[N][i] >= M) {
				answer = i;
				break;
			}
		}
		
		System.out.println(answer);
	}

}
