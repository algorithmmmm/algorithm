package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_고냥이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String str = br.readLine();
		
		HashMap<Character, Integer> map = new HashMap<>(); // 알파벳 - 개수 
		int max = 0; // 최대 문자열 길이
		
		int left = 0;
		for(int right = 0; right < str.length(); right++) {
			char ch = str.charAt(right);
			map.put(ch, map.getOrDefault(ch, 0) + 1);
			
			while(map.size() > N) {
				ch = str.charAt(left);
				map.compute(ch, (key, value) -> (value - 1 == 0) ? null : value - 1); // null 이면 제거됨
				left++;
			}
			
			max = Math.max(max, right - left + 1);
		}
		
		System.out.println(max);
	}

}
