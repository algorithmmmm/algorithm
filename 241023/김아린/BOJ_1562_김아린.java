package algorithm;

import java.io.*;

public class BOJ_계단수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][][] dp = new int[10][N][1 << 10]; //현재 숫자, n번째 자리, 지금까지 방문한 수(비트마스킹)
	
		//첫번째 자리
		for(int i = 1; i < 10; i++) {
            dp[i][0][1 << i] = 1;
        }
		
		//두번째 자리부터
		for (int digit = 1; digit < N; digit++) {
            for (int num = 0; num <= 9; num++) {
                for (int k = 0; k < (1 << 10); k++) {
                    if (num > 0) {
                        dp[num][digit][k | (1 << num)] += dp[num - 1][digit - 1][k];
                        dp[num][digit][k | (1 << num)] %= 1_000_000_000;
                    }
                    
                    if (num < 9) {
                        dp[num][digit][k | (1 << num)] += dp[num + 1][digit - 1][k];
                        dp[num][digit][k | (1 << num)] %= 1_000_000_000;
                    }
                }
            }
        }
		
		//마지막 자릿수에서 모든 숫자 방문했을 떄 합하기
		int answer = 0;
		for(int i = 0; i <= 9; i++) {
			answer += dp[i][N - 1][(1 << 10)-1];
			answer %= 1_000_000_000;
		}
		
		System.out.println(answer);
	}

}
