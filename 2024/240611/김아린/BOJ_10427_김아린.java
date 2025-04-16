package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_빚 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken());
			int[] arr = new int[N + 1];
			
			for(int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr); //정렬 
			
			//누적합 저장 
			int[] dp = new int[N + 1];
			dp[1] = arr[1];
			
			for(int i = 2; i <= N; i++) {
				dp[i] += dp[i - 1] + arr[i];
			}
			
			//S(i) 구하기 
			int answer = 0; //S(i)의 합 
			
			for(int i = 2; i <= N; i++) {
				int min = arr[N] * i; //갚아야 할 돈의 최솟값 
				
				for(int j = i; j <= N; j++) {
					min = Math.min(min, (arr[j] * i) - (dp[j] - dp[j - i]));
				}
				
				answer += min;
			}
			
			sb.append(answer + "\n");
		}
		
		System.out.println(sb);
	}

}
