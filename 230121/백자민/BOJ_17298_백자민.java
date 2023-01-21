package day230121;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17298_백자민 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//end input
		
		Stack<Integer> stack = new Stack<>(); 
		for(int i=0;i<N;i++) { //모든 배열 요소 탐
			
			while(!stack.isEmpty() && arr[stack.peek()]<arr[i]) {
//				if(stack.isEmpty()) break; //스택이 비어있으면 다음 배열요소 넣기 
//				if(arr[stack.peek()] > arr[i]) break; //스택의 마지막 요소가 더 크면 지금 배열요소를 스택에 넣기
				
				arr[stack.pop()] = arr[i]; //큰 값이 나올 때까지 현재 배열요소로 바꾸기 
			}
			
			stack.push(i); 
		}
		
		while(!stack.isEmpty()) //탐색을 마치고 스택에 남은 요소 = 뒤에 더 큰 수가 없는 경우 = -1
			arr[stack.pop()] = -1;
		
		StringBuilder sb = new StringBuilder();
		for(int i: arr)
			sb.append(i).append(' ');
		
		System.out.println(sb);
	}
}

//반례 
//7
//3 2 1 2 3 4 5
