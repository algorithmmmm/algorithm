package algorithm;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1의개수세기2_틀림 {
	static long[] dp; // dp[n] = 2^n - 1 까지의 누적합

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		int dp_length = (int)(Math.log(B) / Math.log(2)); // dp 배열 길이는 log2의 B를 내림 수
		
		//미리 필요한 누적합 계산
		dp = new long[dp_length + 1];
		dp[0] = 0;
		
		for(int i = 1; i <= dp_length; i++) {
//			dp[i] = (long) (1L * Math.pow(2, i - 1)) + dp[i - 1] * 2; // 앞에 새로 붙는 1의 개수(1 * 2^i-1) + 이전 누적합 * 2
			dp[i] = (1L << (i - 1)) + (dp[i - 1] << 1);
		}

		System.out.println(getSum(B) - getSum(A - 1));
	}
	
	public static long getSum(long n) {
	    long sum = 0;
	    while (n > 0) {
	        int idx = (int)(Math.log(n) / Math.log(2)); //최대로 계산 가능한 dp 인덱스
	        long next = n - (1L << idx); // 계산 가능한 수 빼기
	        
	        sum += dp[idx] + (next + 1); //추가되는 1의 개수 더함
	        n = next;
	    }
	    return sum;
	}

}
