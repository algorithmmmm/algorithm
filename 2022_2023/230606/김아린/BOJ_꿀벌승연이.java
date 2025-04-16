package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_꿀벌승연이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); //행
		int M = Integer.parseInt(st.nextToken()); //열
		
		boolean[][] graph = new boolean[N][M]; //벌집 : true(구멍 칸)
		
		int K = Integer.parseInt(br.readLine()); //구멍 칸 개수
		
		//구멍 칸의 정보
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			graph[x][y] = true;
		}
		
		//구현
		long[][][] dp = new long[N + 1][M + 1][3]; //0: 아래쪽, 1: 오른쪽 위, 2: 오른쪽 아래 칸으로 들어오는 경우의 수
		
		
		dp[1][2][2] = dp[2][1][0] = 1;
		
		for(int i = 1; i <= M; i++) {
			for(int j = 1; j <= N; j++) {
				for(int k = 0; k < 3; k++) {
					if(!graph[j][i]) { //구멍칸이 아님
						//
						
					}
				}
			}
		}
		
		//정답
		int answer = (int) ((dp[N][M][0] + dp[N][M][1] + dp[N][M][2]) % (1000000000 + 7));
		System.out.println(answer);
		
		
	}

}
