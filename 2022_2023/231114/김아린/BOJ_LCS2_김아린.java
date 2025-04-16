import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] s1 = br.readLine().toCharArray();
		char[] s2 = br.readLine().toCharArray();

		int[][] dp = new int[s1.length + 1][s2.length + 1];

		for (int i = 1; i < s1.length + 1; i++) {
			for (int j = 1; j < s2.length + 1; j++) {
				if (s1[i - 1] == s2[j - 1]) { // 두 문자 같음
					dp[i][j] = dp[i - 1][j - 1] + 1; // 왼쪽 대각선 위에서 +1
				} else { // 다름
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // 왼쪽과 위에 중 큰 값
				}
			}
		}
		
		//문자 구하기
		Stack<Character> stack = new Stack<>();
		
		int i = s1.length; //뒤에서부터 줄여가면서 역추적
		int j = s2.length;
		
		while(i != 0 && j != 0){
	        if(dp[i][j] == dp[i - 1][j]){ //위와 같으면
	            i--; //위로 이동
	        }else if(dp[i][j] == dp[i][j - 1]){ //왼쪽과 같으면
	            j--; //왼쪽으로 이동
	        }else{ //아니면
	        	stack.push(s1[i - 1]); //문자 담기
	            i--; //대각선 위로 이동
	            j--;
	        }
	    }
		
		//출력
		System.out.println(dp[s1.length][s2.length]);
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.println(sb);
		
	}

}