package algorithm;

import java.util.*;
import java.io.*;

public class BOJ_캠프준비 {
	static int N, L, R, X;
	static int[] arr;
	static int[] result;
	static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		count = 0;
		
		for(int i = 2; i <= N; i++) {
			result = new int[i];
			combination(0, 0, i);
		}
		
		System.out.println(count);
	}

	public static void combination(int start, int depth, int num) {
		if(depth ==  num) {
			if(possible()) count++;
			return;
		}
		
		for(int i = start; i < N; i++) {
			result[depth] = arr[i];
			combination(i + 1, depth + 1, num);
		}
	}

	public static boolean possible() {
		int sum = 0;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for(int n : result) {
			sum += n;
			min = Math.min(min, n);
			max = Math.max(max, n);
		}
		
		if(L <= sum && sum <= R && max - min >= X) {
			return true;
		}
		return false;
	}

}
