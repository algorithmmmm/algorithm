import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12919_김아린 {
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		String T = br.readLine();
		
		answer = 0;
		change(S, T); //연산 수행
		
		System.out.println(answer);
	}

	public static void change(String s, String t) {
		if(s.length() == t.length()) { //종료조건
			if(s.equals(t)) { //S와 T가 같으면 1로 바꾸기
				answer = 1;
			}
			
			return;
		}
		
//		if(answer == 1) return; //가지치기를 위한 종료조건
		
		if(t.charAt(t.length() - 1) == 'A') { //A로 끝나면
			change(s, t.substring(0, t.length() - 1)); //A를 뺀다
		}
		
		String reversed = new StringBuilder(t).reverse().toString(); //뒤집고
		if(reversed.charAt(reversed.length() - 1) == 'B') { //B로 끝나면
			change(s, reversed.substring(0, reversed.length() - 1)); //B를 뺀다
		}
	}

}
