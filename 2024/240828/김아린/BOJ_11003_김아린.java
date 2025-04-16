package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_최솟값찾기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Deque<int[]> deque = new ArrayDeque<>(); //[0] 인덱스, [1] 값
		
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			
			while(!deque.isEmpty() && deque.peekLast()[1] > a) { //뒤로 새로운 값을 넣어야 하는데, 앞에 큰거 빼버리기
				deque.pollLast();
			}
			
			deque.addLast(new int[] {i, a}); //인덱스, 값 넣기
			
			if(deque.peekFirst()[0] <= i - L) { //인덱스가 구간 내 없으면 빼기
				deque.pollFirst();
			}
			
			sb.append(deque.peekFirst()[1] + " "); //최솟값
		}
		
		System.out.println(sb);
	}

}
