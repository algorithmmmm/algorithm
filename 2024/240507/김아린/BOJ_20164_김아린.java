package algorithm;

import java.io.*;

public class BOJ_홀수홀릭호석 {
	static int min, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String N = br.readLine();
		
		min = Integer.MAX_VALUE;
		max = 0;
		
		int count = countOdd(N);
		
		divide(N, count);
		
		System.out.println(min + " " + max);
	}

	private static int countOdd(String n) { //홀수 개수 반환 
		int count = 0;
		for(int i = 0; i < n.length(); i++) {
			if((n.charAt(i) - '0') % 2 == 1) count++;
		}
		return count;
	}

	public static void divide(String num, int count) {
		if(num.length() == 1) { //한 자리 
			min = Math.min(min, count);
			max = Math.max(max, count);
			return;
		} else if(num.length() == 2) { //두 자리
			int sum = num.charAt(0) - '0' + num.charAt(1) - '0';
			
			String nextNum = Integer.toString(sum);
			int nextCount = count + countOdd(nextNum);
			
			divide(nextNum, nextCount);
		} else if(num.length() >= 3) { //세 자리 이상 
			for(int i = 1; i < num.length() - 1; i++) {
				for(int j = i + 1; j < num.length(); j++) {
					int n1 = Integer.parseInt(num.substring(0, i));
					int n2 = Integer.parseInt(num.substring(i, j));
					int n3 = Integer.parseInt(num.substring(j, num.length()));
					
					String nextNum = Integer.toString(n1 + n2 + n3);
					int nextCount = count + countOdd(nextNum);
					
					divide(nextNum, nextCount);
				}
			}
		}
	}

}
