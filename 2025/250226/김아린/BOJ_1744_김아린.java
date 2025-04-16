package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_수묶기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> plusQueue = new PriorityQueue<>((o1, o2) -> o2 - o1); //내림차순
		PriorityQueue<Integer> minusQueue = new PriorityQueue<>(); //오름차순
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num > 0) {
				plusQueue.add(num);
			} else {
				minusQueue.add(num);
			}
		}
		
		// 0이상 수묶기 진행
		int sum_plus = 0;
		int tmp = 0; //중간합
		boolean isPosibble = false; //수 묶기(곱하기) 가능한지
		
		while(!plusQueue.isEmpty()) {
			if(plusQueue.peek() == 1) isPosibble = false; //1은 안 곱하는게 좋음
			
			if(isPosibble) {
				tmp *= plusQueue.poll();
				isPosibble = false;
				//중간합 더하기
				sum_plus += tmp;
				tmp = 0;
			} else {
				tmp += plusQueue.poll();
				isPosibble = true;
				
				if(tmp == 1) {
					sum_plus += tmp;
					tmp = 0;
				}
			}
		}
		sum_plus += tmp; //마지막 남은 중간합 더해줌
		tmp = 0;
		
		// 음수 수묶기 진행
		int sum_minus = 0;
		tmp = 0;
		isPosibble = false;
		
		while(!minusQueue.isEmpty()) {
			if(isPosibble) {
				tmp *= minusQueue.poll();
				isPosibble = false;
				//중간합 더하기
				sum_minus += tmp;
				tmp = 0;
			} else {
				tmp += minusQueue.poll();
				isPosibble = true;
			}
		}
		sum_minus += tmp; //마지막 남은 중간합 더해줌
		
		// 출력
		System.out.println(sum_plus + sum_minus);
	}

}
