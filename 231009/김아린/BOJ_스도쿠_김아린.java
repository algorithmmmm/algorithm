package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_스도쿠 {
	static int[][] board;
	static List<int[]> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		board = new int[9][9];
		list = new ArrayList<>(); //빈 칸의 좌표 저장
		
		for(int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if(board[i][j] == 0) {
					list.add(new int[] {i, j});
				}
			}
		}
		
		dfs(0); //리스트 인덱스
	}

	public static void dfs(int depth) {
		if(depth == list.size()) {
			//출력
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					sb.append(board[i][j] + " ");
				}
				sb.append("\n");
			}
			
			System.out.println(sb);
			
			System.exit(0); //결과 있으면 프로그램 종료시키기
		}
		
		int[] tmp = list.get(depth);
		for(int i = 1; i <= 9; i++) { //1~9 넣어봄
			if(isPossible(tmp[0], tmp[1], i)) {
				board[tmp[0]][tmp[1]] = i;
				dfs(depth + 1);
				board[tmp[0]][tmp[1]] = 0; //초기화
			}
		}
	}

	private static boolean isPossible(int x, int y, int n) {
		for(int i = 0; i < 9; i++) {
			if(board[x][i] == n) return false; //행 검사
			if(board[i][y] == n) return false; //열 검사
		}
		
		//박스 안 검사
		int qx = x / 3 * 3;
		int qy = y / 3 * 3;
		
		for(int i = qx; i < qx + 3; i++) {
			for(int j = qy; j < qy + 3; j++) {
				if(board[i][j] == n) return false;
			}
		}
		
		return true;
	}

}
