package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_소형기관차 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 객차 수
        int S = 3; // 소형 기관차 수
        int[] sum = new int[N + 1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
        	sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }
        
        int M = Integer.parseInt(br.readLine()); // 소형 기관차가 최대로 끌 수 있는 객차 수
        
        // 구현
        int[][] dp = new int[S + 1][N + 1]; // 기관차 수, 객차 번호까지의 최대 승객 수
        
        for(int i = 1; i <= S; i++) {
        	for(int j = i * M; j <= N; j++) {
            	dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - M] + sum[j] - sum[j - M]);
            }
        }
        
        // 출력
        System.out.println(dp[S][N]);
	}
	
	/*
	 * 입력
	 * 7
	 * 35 40 50 10 30 45 60
	 * 2
	 * 
	 * DP
	 * 0	0	0	0	0	0	0	0
	 * 0	0	75	90	90	90	90	105
	 * 0	0	0	0	135	135	165	195
	 * 0	0	0	0	0	0	210	240
	 */

}
