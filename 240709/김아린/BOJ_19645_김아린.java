package algorithm;

import java.util.*;
import java.io.*;

public class BOJ_햄최몇 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] dp = new boolean[2501][2501]; //관우, 철환의 효용이 가능한지 
		dp[0][0] = true;
		
		int sum = 0;
		for(int i = 0; i < N; i++) { //모든 햄버거 탐색
			sum += arr[i];
			
			for(int j = sum; j >= 0; j--) {
				for(int k = sum; k >= 0; k--) {
					if(dp[j][k]) { //이전이 가능한 조합이면, 현재 햄버거 먹은 조합도 가능 
	                    dp[j + arr[i]][k] = true; //관우 먹음 
	                    dp[j][k + arr[i]] = true; //철환 먹음 
	                }
				}
			}
		}
		
		//효용 최대 구하기
		int max = 0;
		for(int i = 0; i <= sum; i++) {
	        for(int j = 0; j <= sum; j++) {
	            if(dp[i][j]) { //가능한 조합
	            	int gilwon = Math.min(sum - i - j, Math.min(i, j)); //가장 낮은 효용이 길원 
	            	max = Math.max(max, gilwon);
	            }
	        }
	    }
		
		System.out.println(max);
	}

}
