package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_오아시스재결합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		Stack<int[]> stack = new Stack<>(); //[0]: 키, [1]: 몇 연속 같은 수 
		long answer = 0;
		
		for(int i = 0; i < N; i++) {
			int h = Integer.parseInt(br.readLine());
			
			int count = 1;
			while(!stack.isEmpty() && stack.peek()[0] <= h) {
				answer += stack.peek()[1];
				
				if (stack.peek()[0] == h) { //같은 키 누적 
					count += stack.peek()[1];
				}
				
				stack.pop();
			}
			
			if(!stack.empty()) answer++; //마지막 사람 
			
			stack.add(new int[]{h, count});
		}
		
		System.out.println(answer);
	}

}
