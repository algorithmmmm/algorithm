package algorithm;

import java.io.*;

public class BOJ_즐거운단어 {
	static String str;
	static long sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str = br.readLine();
		
		sum = 0;
		dfs(0, 0, 0, false, 1);
		
		System.out.println(sum);
	}
	
	public static void dfs(int depth, int v, int c, boolean l, long count) {
		if(v >= 3 || c >= 3) return; // 모음 or 자음 3연속 -> 가지치기
		
		if(depth == str.length()) {
			if(l) sum += count;
			return;
		}
		
		if(str.charAt(depth) == '_') {
			dfs(depth + 1, v + 1, 0, l, count * 5); // 모음
			dfs(depth + 1, 0, c + 1, l, count * 20); // 자음
			dfs(depth + 1, 0, c + 1, true, count); // L
		} else {
			if(isVowel(str.charAt(depth))) {
				dfs(depth + 1, v + 1, 0, l, count);
			} else {
				if(str.charAt(depth) == 'L') {
					dfs(depth + 1, 0, c + 1, true, count);
				} else {
					dfs(depth + 1, 0, c + 1, l, count);
				}
			}
		}
	}

	public static boolean isVowel(char ch) {
		if(ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
			return true;
		}
		return false;
	}

}
