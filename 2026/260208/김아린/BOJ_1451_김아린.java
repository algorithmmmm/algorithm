package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_직사각형으로나누기 {
	static int N, M;
	static int[][] arr;
	static long[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N + 1][M + 1];
        
        for(int i = 1; i <= N; i++) {
			String str = br.readLine();
			for(int j = 1; j <= M; j++) {
				arr[i][j] = str.charAt(j - 1) - '0'; 
			}
		}
        
        dp = new long[N + 1][M + 1]; // 행렬 (1, 1) ~ (i, j)까지의 합
        
        for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + arr[i][j];
			}
		}
        
        long answer = 0;
        // 세로로 그었을 때
        for(int i = 1; i <= M - 2; i++) {
        	for(int j = 1; j <= M - 1; j++) {
        		long s1 = getSum(1, 1, N, i);
        		long s2 = getSum(1, i + 1, N, j);
        		long s3 = getSum(1, j + 1, N, M);
            	answer = Math.max(answer, s1 * s2 * s3);
            }
        }
        
        // 가로로 그었을 때
        for(int i = 1; i <= N - 2; i++) {
        	for(int j = 1; j <= N - 1; j++) {
        		long s1 = getSum(1, 1, i, M);
        		long s2 = getSum(i + 1, 1, j, M);
        		long s3 = getSum(j + 1, 1, N, M);
            	answer = Math.max(answer, s1 * s2 * s3);
            }
        }
        
        for(int i = 1; i <= N - 1; i++) {
            for(int j = 1; j <= M - 1; j++) {
                // ㅏ
            	long s1 = getSum(1, 1, N, j);
            	long s2 = getSum(1, j + 1, i, M);
            	long s3 = getSum(i + 1, j + 1, N, M);
                answer = Math.max(answer, s1 * s2 * s3);

                // ㅓ
                s1 = getSum(1, 1, i, j);
                s2 = getSum(i + 1, 1, N, j);
                s3 = getSum(1, j + 1, N, M);
                answer = Math.max(answer, s1 * s2 * s3);

                // ㅜ
                s1 = getSum(1, 1, i, M);
                s2 = getSum(i + 1, 1, N, j);
                s3 = getSum(i + 1, j + 1, N, M);
                answer = Math.max(answer, s1 * s2 * s3);

                // ㅗ
                s1 = getSum(1, 1, i, j);
                s2 = getSum(1, j + 1, i, M);
                s3 = getSum(i + 1, 1, N, M);
                answer = Math.max(answer, s1 * s2 * s3);
            }
        }
        
        System.out.println(answer);
	}
	
	static // 행렬 (x1, y1) ~ (x2, y2)까지의 합
	long getSum(int x1, int y1, int x2, int y2) {
		return dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1];
	}

}
