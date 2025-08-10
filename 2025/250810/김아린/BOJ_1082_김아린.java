package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_방번호 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		
		// 구현
		String[] dp = new String[M + 1]; // m원으로 만들 수 있는 최대 방번호
		Arrays.fill(dp, "");
		
		String max = "";
		
		for(int i = 1; i <= M; i++) {
			for(int j = 0; j < N; j++) {
				int cost = arr[j];
				
				if(i - cost >= 0) {
					String str = dp[i - cost] + j;
					
					if(str.equals("0")) continue;
					
					dp[i] = getMax(dp[i], str);
				}
			}
			max = getMax(max, dp[i]);
		}
		
		// 출력
		max = max.equals("") ? "0" : max;
		System.out.println(max);
	}

	private static String getMax(String s1, String s2) {
		if (s1.equals("")) return s2;
        if (s2.equals("")) return s1;
		
		if (s1.length() != s2.length()) {
			return s1.length() > s2.length() ? s1 : s2;
		}
		return s1.compareTo(s2) >= 0 ? s1 : s2;
	}

}
