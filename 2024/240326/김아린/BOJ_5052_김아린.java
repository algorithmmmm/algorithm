package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_전화번호목록 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			String[] strs = new String[N];
			
			for(int i = 0; i < N; i++) {
				strs[i] = br.readLine();
			}
			
			Arrays.sort(strs);
			
			boolean isOk = true;
			
			int left = 0;
			for(int right = 1; right < N; right++) {
				if(strs[right].startsWith(strs[left])) {
					isOk = false;
					break;
				} else {
					left++;
				}
			}
			
			if(isOk) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}
		
		System.out.println(sb);
	}

}
