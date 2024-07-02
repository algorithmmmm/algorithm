package algorithm;

import java.io.*;

public class BOJ_N으로만들기 {
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String N = br.readLine();
		
		recursive(N);
		
		System.out.println(count);
	}
	
	public static void recursive(String num) {
		if(num.length() == 1) {
			count++;
			return;
		}
		
		String frontRemoved = num.substring(1, num.length());
		String backRemoved = num.substring(0, num.length() - 1);
		
		recursive(frontRemoved);
		
		if(frontRemoved.equals(backRemoved)) return;
		
		recursive(backRemoved);
	}

}
