package algorithm;

import java.util.*;
import java.io.*;

public class BOJ_출근기록 {
	static int[] nums;
	static boolean[][][][][] dp;
	static char[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		
		nums = new int[3]; //A, B, C의 개수 
		
		for(int i = 0; i < S.length(); i++) {
			if(S.charAt(i) == 'A') {
				nums[0]++;
			} else if(S.charAt(i) == 'B') {
				nums[1]++;
			} else {
				nums[2]++;
			}
		}
		
		dp = new boolean[51][51][51][3][3]; //A개수, B개수, C개수, 다음날 출근(0, 1, 2), 다다음날 출근한 사람이 가능한가?
		result = new char[51];
		
		StringBuilder sb = new StringBuilder();
		if(dfs(0, 0, 0, 0, 0)) { //올바른 출근 기록 가능 
			for(int i = 0; i < S.length(); i++) {
				sb.append(result[i]);
			}
		} else { //불가능 
			sb.append(-1);
		}
		
		System.out.println(sb);
	}
	
	static boolean dfs(int a, int b, int c, int day1, int day2) { //A개수, B개수, C개수, 전전날 출근, 전날 출근한 사람 
		//종료 조건: 가능한 조합 다 만듦 
		if(a == nums[0] && b == nums[1] && c == nums[2]) return true;
		
		//가지치기
		if(dp[a][b][c][day1][day2]) return false;
		
		dp[a][b][c][day1][day2] = true;
		
		if (a + 1 <= nums[0]) {
			result[a + b + c] = 'A';
			if(dfs(a + 1, b, c, day2, 0)) return true;
		}
		
		if (b + 1 <= nums[1]) {
			result[a + b + c] = 'B';
			if(day2 != 1 && dfs(a, b + 1, c, day2, 1)) return true;
		}
		
		if (c + 1 <= nums[2]) {
			result[a + b + c] = 'C';
			if(day1 != 2 && day2 != 2 && dfs(a, b, c + 1, day2, 2)) return true;
		}
		
		return false;
	}

}
