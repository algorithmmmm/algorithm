package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_피자판매 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] A = new int[n];
		int[] B = new int[m];
		
		int sumA = 0;
		for(int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(br.readLine());
			sumA += A[i];
		}
		
		int sumB = 0;
		for(int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(br.readLine());
			sumB += B[i];
		}
		
		//구현
		int[] arrA = new int[Math.max(sumA, N) + 1];
		int[] arrB = new int[Math.max(sumB, N) + 1];
		
		arrA[0] = arrA[sumA] = 1;
		arrB[0] = arrB[sumB] = 1;
		
		for(int i = 0; i < n; i++) {
			int sum = 0;
			for(int j = i; j < i + n - 1; j++) {
				sum += A[j % n];
				arrA[sum]++;
			}
		}
		
		for(int i = 0; i < m; i++) {
			int sum = 0;
			for(int j = i; j < i + m - 1; j++) {
				sum += B[j % m];
				arrB[sum]++;
			}
		}
		
		//경우의 수 계산
		int answer = 0;
		
		for(int i = 0; i <= N; i++) {
			answer += arrA[i] * arrB[N - i];
		}
		
		//출력
		System.out.println(answer);
	}

}
