package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_과일탕후루 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] count = new int[10]; // 탕후루 종류별 개수 저장
		int kind = 0;
		int max = 0;
		
		int left = 0;
		for(int right = 0; right < N; right++) {
			// 새 과일 추가
			if(++count[arr[right]] == 1) kind++; // 새 종류 추가
			
			while(kind > 2) { // 종류 2개 될 때까지 빼기
				if(--count[arr[left]] == 0) kind--;
				left++;
			}
			
			max = Math.max(max, right - left + 1); // 최대 길이 갱신
		}
		
		System.out.println(max);
	}

}
