import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[N + 1][M + 1]; 
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < M + 1; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//구현
		int[][] dp = new int[N + 1][M + 1]; //가치의 합 저장
		
		//N = 1 일 때
		for(int i = 1; i < M + 1; i++) { //왼쪽에서 오는 누적 합
			dp[1][i] = dp[1][i - 1] + graph[1][i];
		}
		
		//N > 2 일 때
		for(int i = 2; i < N + 1; i++) {
			//왼쪽에서 오는 누적 합
			int[] left = new int[M + 1];
			left[1] = dp[i - 1][1] + graph[i][1];
			
			for(int j = 2; j < M + 1; j++) {
				left[j] = Math.max(left[j - 1], dp[i - 1][j]) + graph[i][j]; //위에서 오는 값과 비교해서 갱신
			}
			
			//오른쪽에서 오는 누적 합
			int[] right = new int[M + 1];
			right[M] = dp[i - 1][M] + graph[i][M];

			for(int j = M - 1; j >= 1; j--) {
				right[j] = Math.max(right[j + 1] , dp[i - 1][j]) + graph[i][j]; //위에서 오는 값과 비교해서 갱신
			}
			
			//왼쪽, 오른쪽에서 오는 것 중 최대값 저장
			for(int j = 1; j < M + 1; j++) {
				dp[i][j] = Math.max(left[j], right[j]);
			}
		}
		
		System.out.println(dp[N][M]);
		
	}

}