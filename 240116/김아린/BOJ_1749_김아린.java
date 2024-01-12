package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_점수따먹기2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N + 1][M + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N + 1][M + 1]; //행렬 (i, 1)~(i, j)까지의 합
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				dp[i][j] = dp[i][j - 1] + arr[i][j];
			}
		}
		
		int max = Integer.MIN_VALUE; //정답 : 부분 행렬 합의 최대
		
		for(int i = 1; i <= M; i++) {
			for(int j = i; j <= M; j++) {
				int sum = 0;
				for(int k = 1; k <= N; k++) { //행 늘려감
					int row = dp[k][j] - dp[k][i - 1]; //한줄
					sum += row; //한줄 더함
					if(row > sum) sum = row; //한줄 이 더 크면, 이전 기록 버리고 sum을 갱신
					max = Math.max(max, sum); //최댓값 갱신
				}
			}
		}
		
		System.out.println(max);
	}

}
