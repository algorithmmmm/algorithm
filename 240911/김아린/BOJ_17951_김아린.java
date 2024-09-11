package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_흩날리는시험지속에서내평점이느껴진거야 {
	static int N, K;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		int low = 100000; //합의 최소
		int high = 0; //합의 최대
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			low = Math.min(low, arr[i]);
			high += arr[i];
		}
		
		System.out.println(binarySearch(arr, low, high));
	}
	
	public static int binarySearch(int[] arr, int low, int high) {
		while(low <= high) {
			int mid = (low + high) / 2;
			
			//그룹 수 계산
			int sum = 0;
			int count = 0;
			for(int i = 0; i < N; i++) {
				sum += arr[i];
				
				if(sum >= mid) {
					count++;
					sum = 0;
				}
			}
			
			//범위 줄이기
			if(count >= K) { //그룹이 더 많음 -> 합이 더 커야 함
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		
		return high;
	}

}
