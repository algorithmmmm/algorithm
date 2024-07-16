package algorithm;

import java.util.*;
import java.io.*;

public class BOJ_두배열의합 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine());
		int[] B = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
	
		//구현 
		HashMap<Integer, Integer> map = new HashMap<>(); //합 저장 맵 : <합, 해당 합 개수>
		
		for(int i = 0; i < n; i++) {
			int sum = 0;
			for(int j = i; j < n; j++) {
				sum += A[j];
				map.put(sum, map.getOrDefault(sum, 0) + 1);
			}
		}
		
		long count = 0;
		
		for(int i = 0; i < m; i++) {
			int sum = 0;
			for(int j = i; j < m; j++) {
				sum += B[j];
				count += map.getOrDefault(T - sum, 0); //맵에 있으면 개수만큼 카운트 올리기 
			}
		}
		
		System.out.println(count);
	}

}
