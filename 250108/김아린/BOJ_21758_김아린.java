package algorithm;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_꿀따기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N + 1];
		int[] dp = new int[N + 1]; //누적합 저장
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = dp[i - 1] + arr[i]; //누적합 구하기
		}
		
		//구현
		int answer = 0;
		
		//1. 벌통은 맨 오른쪽에 위치, 1번 벌은 맨 왼쪽에 위치하는 경우
		int sum1 = dp[N] - arr[1];
		int sum2 = dp[N];
		for(int i = 2; i < N; i++) { //모든 가능한 2번 벌 위치 탐색
			int total = (sum1 - arr[i]) + (sum2 - dp[i]);
			answer = Math.max(answer, total);
		}
		
		//2. 벌통이 중간에 위치, 벌들은 양쪽 끝에 위치하는 경우
		for(int i = 2; i < N; i++) { //모든 가능한 벌통 위치 탐색
			int total = (dp[i] - arr[1]) + (dp[N - 1] - dp[i - 1]);
			answer = Math.max(answer, total);
		}
		
		//3. 벌통은 맨 왼쪽에 위치, 1번 벌은 맨 오른쪽에 위치하는 경우
		sum1 = dp[N] - arr[N];
		for(int i = 2; i < N; i++) { //모든 가능한 2번 벌 위치 탐색
			int total = (sum1 - arr[i]) + dp[i - 1];
			answer = Math.max(answer, total);
		}
		
		System.out.println(answer);
	}

}
