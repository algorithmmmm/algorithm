package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_비숍 {
	static int N;
	static boolean[][] board;
	static boolean[] checked1;
	static boolean[] checked2;
	static int[] max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		board = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				board[i][j] = n == 1 ? true : false;
			}
		}
		
		max = new int[2]; //정답 : 검정 칸, 흰 칸 최대 개수
		checked1 = new boolean[20]; //대각선 \ (r+c 값이 같음)
		checked2 = new boolean[20]; //대각선 / (r-c 값이 같음)
		
		dfs(0, 0, 0, 0); //검정 칸에 놓음 : (0, 0) 시작, 색깔0
		dfs(0, 1, 1, 0); //흰 칸에 놓음 : (0, 1) 시작, 색깔1
		
		System.out.println(max[0] + max[1]);
	}

	public static void dfs(int r, int c, int color, int count) { //좌표값, 색깔, 개수
		if(c >= N) { //행 이동 
			r++;
			c = c % 2 == 0 ? 1 : 0;
		}
		
		if(r == N) { //다 놓음
			max[color] = Math.max(max[color], count);
			return;
		}
		
		//비숍 놓기
		if(board[r][c] && !checked1[r + c] && !checked2[r - c + N  -1]) { //놓을 수 있는 칸 & 대각선에 다른 말 없음 
			checked1[r + c] = checked2[r - c + N - 1] = true;
			dfs(r, c +  2, color, count + 1);
			checked1[r + c] = checked2[r - c + N - 1] = false;
		}
		
		//비숍 놓지 않기 
		dfs(r, c +  2, color, count);
	}

}
