package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_호텔 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][2];
		
		int max_client = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			arr[i][0] = Integer.parseInt(st.nextToken()); //비용
			arr[i][1] = Integer.parseInt(st.nextToken()); //고객 수
			
			max_client = Math.max(max_client, arr[i][1]);
		}
		
		int[] dp = new int[C + max_client]; //고객 수-비용
		Arrays.fill(dp, 100 * (C + max_client));
		dp[0] = 0;
		
		for(int i = 1; i < C + max_client; i++) {
			for(int j = 0; j < N; j++) { //모든 호텔 탐색
				int cost = arr[j][0];
				int client = arr[j][1];
				
				if(i - client >= 0) {
					dp[i] = Math.min(dp[i], dp[i - client] + cost);
				}
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for(int i = C; i < C + max_client; i++) {
			answer = Math.min(answer, dp[i]);
		}
		
		System.out.println(answer);
	}

}
