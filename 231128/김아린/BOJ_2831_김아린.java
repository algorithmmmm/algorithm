package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_댄스파티_김아린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		//남성
		PriorityQueue<Integer> man_positive = new PriorityQueue<>(); //큰 사람 선호 남자
		PriorityQueue<Integer> man_negative = new PriorityQueue<>(); //작은 사람 선호 남자
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			
			if(height > 0) {
				man_positive.add(height);
			} else {
				man_negative.add(height * -1); //양수로 바꿔줌
			}
		}
		
		//여성
		PriorityQueue<Integer> woman_positive = new PriorityQueue<>(); //큰 사람 선호 여자
		PriorityQueue<Integer> woman_negative = new PriorityQueue<>(); //작은 사람 선호 여자
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			
			if(height > 0) {
				woman_positive.add(height);
			} else {
				woman_negative.add(height * -1); //양수로 바꿔줌
			}
		}
		
		//구현
		int count = 0;
		
		//작은 사람 선호 남자, 큰 사람 선호 여자
		while(!man_negative.isEmpty() && !woman_positive.isEmpty()) {
			if(man_negative.peek() > woman_positive.peek()) {
				count++;
				woman_positive.poll();
			}
			man_negative.poll();
		}
		
		//큰 사람 선호 남자, 작은 사람 선호 여자
		while(!man_positive.isEmpty() && !woman_negative.isEmpty()) {
			if(man_positive.peek() < woman_negative.peek()) {
				count++;
				man_positive.poll();
			}
			woman_negative.poll();
		}
		
		System.out.println(count);
	}

}
