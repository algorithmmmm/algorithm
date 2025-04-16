package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/* 
 * A = [3, 5, 2, 7] => 5, 7, 7, -1
 * 스택 상단에 있는 수와 비교 -> 안에 있는 수가 크면, TOP에 있는 수 출력
 * 안에 있는 수가 작으면, 계속 POP
 * 작업이 끝나면 현재 수를 넣는다
 */

public class BOJ_17298_김아린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //수열 A의 크기
		
		int[] A = new int[N]; //수열
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		//구현
		int[] NGE = new int[N]; //오큰수(정답)
		
		Stack<Integer> stack = new Stack<>(); //수를 넣었다가 뺐다가할 스택
		
		for(int i = N - 1; i >= 0; i--) {
			while(true) {
				if(stack.size() == 0) { //스택에 남아있는 오큰수가 없으면
					NGE[i] = -1;
					break;
				}
				
				if(A[i] < stack.peek()) { //스택 상단에 있는 수와 비교해서 오큰 수 발견
					NGE[i] = stack.peek();
					break;
				} else { //오큰수 발견할때까지 계속 스택에서 뽑기
					stack.pop();
				}
			}
			
			stack.push(A[i]); //자기자신 넣기
		}
		
		//출력
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			sb.append(NGE[i] + " ");
		}
		
		System.out.println(sb);
	}

}
