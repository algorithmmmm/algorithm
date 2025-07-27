package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_같은수로만들기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		int max = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}
		
		// 구현
		long answer = 0;
		
		// 목표 : 최댓값으로 모두 다 되어야 함
		Stack<Integer> stack = new Stack<>();
		
		stack.add(arr[0]);
		
		for(int i = 1; i < N; i++) {
			if(stack.peek() < arr[i]) {
				answer += arr[i] - stack.pop(); // 차이만큼 더함
		        stack.add(arr[i]);
		    } else if(stack.peek() > arr[i]) {
		    	stack.pop();
		    	stack.add(arr[i]);
		    }
		}
		
		// 마지막 남은 최소 수와 최대의 차이를 더함
		while(!stack.isEmpty()) {
			answer += max - stack.pop();
		}
		
		// 출력
		System.out.println(answer);
	}

}
