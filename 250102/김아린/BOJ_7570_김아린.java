package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//구현
		int[] dp = new int[N + 1]; //해당 숫자까지의 연속된 LIS 길이
		int LIS = 0;
		
		for(int i = 1; i <= N; i++) { //연속된 LIS 길이 구하기
			int num = arr[i];
			dp[num] = dp[num - 1] + 1; //이전 인덱스의 dp값 활용
			LIS = Math.max(LIS, dp[num]);
		}
		
		//출력
		System.out.println(N - LIS);
	}

}
