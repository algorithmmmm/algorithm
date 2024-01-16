package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_직사각형과쿼리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //행렬 크기
		
		int[][] A = new int[N + 1][N + 1]; //행렬
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] dp = new int[11][N + 1][N + 1]; //행렬 (i, j)까지의 해당 번호의 개수
		
		//행렬 (i, j)까지의 각 번호별 개수 구하기
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				for(int k = 1; k <= 10; k++) {
					dp[k][i][j] = dp[k][i - 1][j] + dp[k][i][j - 1] - dp[k][i - 1][j - 1];
				}
				dp[A[i][j]][i][j]++;
			}
		}
		
		int Q = Integer.parseInt(br.readLine()); //쿼리 수
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int answer = 0;
			for(int num = 1; num <= 10; num++) {
				int count = dp[num][x2][y2] - dp[num][x1 - 1][y2] - dp[num][x2][y1 - 1] + dp[num][x1 - 1][y1 - 1];
			
				if(count > 0) answer++;
			}
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}

}
