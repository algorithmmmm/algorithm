package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_크게만들기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
	
		String str = br.readLine();
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = str.charAt(i) - '0';
		}
		
		Stack<Integer> stack = new Stack<>();
		
		int count = 0; //지금까지 지운 숫자 개수

		for(int n : arr) {
			while(!stack.isEmpty() && stack.peek() < n && count < K) { //새로 넣어야 될 숫자보다 작은 수는 제거
				stack.pop();
				count++;
			}
			
			stack.add(n); //새로 넣기
		}
		
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			if(count < K) { //앞에서 K개 만큼 다 안지웠을 경우 제거하기
				stack.pop();
				count++;
			} else {
				sb.append(stack.pop()); //남은 숫자 뒤에서부터 만들기
			}
		}
		
		System.out.println(sb.reverse()); //뒤집어서 출력
	}

}
