package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_가장긴짝수연속한부분수열large {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); //수열 S의 길이 
		int K = Integer.parseInt(st.nextToken()); //삭제할 수 있는 최대 횟수
		
		int[] S = new int[N]; //수열
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		
		//구현
		int right = 0;
		int count = 0; //홀수의 개수
		int max = 0; //정답 : 최대 길이
		
		for(int left = 0; left < N; left++) { //왼쪽 포인터 하나씩 옮겨가며
			while(right < N && (count < K || S[right] % 2 == 0)) { //오른쪽 포인터를 홀수가 K개 미만 or 짝수일때까지 옮김
				if(S[right] % 2 == 1) count++; //홀수면 개수 증가
				right++;
			}
			
			max = Math.max(max, right - left - count); //짝수의 길이 갱신
			
			if(S[left] % 2 == 1) count--; //왼쪽 포인터가 홀수이면 개수 감소
		}
		
		//출력
		System.out.println(max);
	}

}
