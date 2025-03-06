package algorithm;

import java.io.*;

public class BOJ_문자열교환 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		int length = str.length();
		
		int count_a = 0;
		for(int i = 0; i < length; i++) {
			if(str.charAt(i) == 'a') count_a++;
		}
		
		int count_b = 0;
		for(int i = 0; i < count_a; i++) {
			if(str.charAt(i) == 'b') count_b++;
		}
		
		int min = count_b;
		for(int i = 1; i < length; i++) {
			if(str.charAt(i - 1) == 'b') count_b--;
			if(str.charAt((i + count_a - 1) % length) == 'b') count_b++;
			min = Math.min(min, count_b);
		}
		
		System.out.println(min);
	}

}
