package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_행복유치원 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(); //인접한 원생과 키 차이 저장(오름차순)  
		
		for(int i = 1; i < N; i++) {
			pq.add(arr[i] - arr[i - 1]);
		}
		
		int count = N - K; //뽑아야 되는 차이 개수 
		int answer = 0;
		while(count > 0) {
			answer += pq.poll();
			count--;
		}
		
		System.out.println(answer);
	}

}
