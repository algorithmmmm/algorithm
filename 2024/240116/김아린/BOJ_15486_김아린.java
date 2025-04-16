package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_퇴사2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N + 1][2];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N + 2];
		
		for(int i = 1; i <= N; i++) {
			int time = arr[i][0];
			int pay = arr[i][1];
			
			if(i + time <= N + 1) {
				dp[i + time] = Math.max(dp[i + time], dp[i] + pay);
			}
			
			dp[i + 1] = Math.max(dp[i + 1], dp[i]);
		}
		
		System.out.println(dp[N + 1]);
	}

}