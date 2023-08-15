import java.io.*;
import java.util.*;

public class Main {
	static int N; //수열 크기
	static int[] arr; //수열
	static int[] op; //연산자별 개수
	static int[] result; //연산자 순서 결과
	static int max = -1000000000; //정답 : 최댓값
	static int min = 1000000000; //정답 : 최솟값
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		op = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
	
		for(int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		
		//수행
		result = new int[N - 1];
		
		permutation(0);
		
		//출력
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void permutation(int depth) {
		if(depth == N - 1) {
			int result = calculate(); //연산 결과
			
			max = Math.max(max, result);
			min = Math.min(min, result);
			
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(op[i] != 0) { //해당 연산자가 있으면
				op[i]--;
				result[depth] = i; //연산자 순서 저장
				permutation(depth + 1); //다음 자리 탐색
				op[i]++;
			}
		}
	}

	public static int calculate() {
		Stack<Integer> stack = new Stack<>();
		
		stack.add(arr[0]); //처음 수 넣고 시작
		
		for(int i = 1; i < N; i++) {
			int num = 0; //중간 연산 결과
			
			if(result[i - 1] == 0) { //덧셈
				num = arr[i];
			} else if(result[i - 1] == 1) { //뺄셈
				num = arr[i] * -1;
			} else if(result[i - 1] == 2) { //곱셈
				num = stack.pop() * arr[i];
			} else if(result[i - 1] == 3) { //나눗셈
				num = stack.pop() / arr[i];
			}
			
			stack.add(num); //중간 연산 결과 넣기
		}
		
		int result = 0; //연산 결과
		for(int n : stack) {
			result += n;
		}
		
		return result;
	}

}