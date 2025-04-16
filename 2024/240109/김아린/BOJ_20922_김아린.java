package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_겹치는건싫어 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); //배열의 길이
		int K = Integer.parseInt(st.nextToken()); //가능한 같은 정수의 개수
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//구현
		int[] count = new int[1000001]; //원소별 개수 저장
		int right = 0;
		int max = 0; //정답: 최장 연속 부분 수열의 길이
		
		for(int left = 0; left < N; left++) { //왼쪽은 하나씩 옮김
			while(right < N && count[arr[right]] < K) { //K개 미만일때까지 오른쪽으로 옮김
				count[arr[right]]++;
				right++;
			}
			
			max = Math.max(max, right - left); //최댓값 갱신
			count[arr[left]]--; //왼쪽 원소의 수 빼주기
		}
		
		System.out.println(max);
	}

}