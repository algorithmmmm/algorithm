package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_연산최대로 {
	static int N, P, Q;
	static int[] arr;
	static boolean[] visited;
	static int[] result;
	static boolean[] isPlus;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		P = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N];
		result = new int[N]; // 숫자 위치 기록
		isPlus = new boolean[N - 1]; // 덧샘 위치 기록
		answer = 0;
		
		permutation(0); // 1. 순열로 숫자 순서 구하기
		System.out.println(answer);
	}
	
	private static void permutation(int depth) {
		if(depth == N) {
			chooseOps(0, 0); // 2. 연산자 위치 구하기 : 깊이, 덧샘 개수
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			result[depth] = arr[i];
			permutation(depth + 1);
			visited[i] = false;
		}
	}
	
	private static void chooseOps(int depth2, int count) {
        if(count > P) return; // 덧셈 개수 초과
        if(depth2 == N - 1) {
            if(count == P) {
                calculate(); // 3. 수식 계산하기
            }
            return;
        }
        
        // 덧샘
        isPlus[depth2] = true;
        chooseOps(depth2 + 1, count + 1);
        // 곱샘
        isPlus[depth2] = false;
        chooseOps(depth2 + 1, count);
    }

	private static void calculate() {
		int sum = 1;
		int tmp = result[0];
        for(int i = 0; i < N - 1; i++) {
            if(isPlus[i]) {
                tmp += result[i + 1];
            } else {
            	sum *= tmp;
                tmp = result[i + 1];
            }
        }
        sum *= tmp;
        answer = Math.max(answer, sum);
	}

}
