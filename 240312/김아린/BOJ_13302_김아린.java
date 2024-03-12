package algorithm;

import java.io.*;
import java.util.*;

class BOJ_리조트 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashSet<Integer> set = new HashSet<>(); //갈 수 없는 날 저장
		
		if(M > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < M; i++) {
				set.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		//구현
		int[][] dp = new int[N + 6][N + 3]; //날, 쿠폰 개수
		
		for(int[] arr : dp) { //최댓값으로 초기화
			Arrays.fill(arr, Integer.MAX_VALUE);
		}
		
		dp[0][0] = 0;
		
		for(int i = 0; i < N + 1; i++) {
			for(int j = 0; j < N + 1; j++) {
				if(dp[i][j] == Integer.MAX_VALUE) continue; //불가능한 경우 건너뛰기
				
				//못 감
				if(set.contains(i + 1)) {
					dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
				}
				
				//쿠폰 3개 이상
				if(j >= 3) {
					dp[i + 1][j - 3] = Math.min(dp[i + 1][j - 3], dp[i][j]);
				}
				
				//1일권
				dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 10000);
				
				//3일권
				for(int k = 1; k <= 3; k++) {
					dp[i + k][j + 1] = Math.min(dp[i + k][j + 1], dp[i][j] + 25000);
				}
				
				//5일권
				for(int k = 1; k <= 5; k++) {
					dp[i + k][j + 2] = Math.min(dp[i + k][j + 2], dp[i][j] + 37000);
				}
			}
		}
		
		for(int i = 0; i < N + 1; i++) {
			for(int j = 0; j < N + 1; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		
		int min = Integer.MAX_VALUE;
		for(int j = 0; j < N + 1; j++) { //최솟값 구하기
			min = Math.min(min, dp[N][j]);
		}
		
		System.out.println(min);
	}

}
