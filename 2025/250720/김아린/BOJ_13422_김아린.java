package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_도둑 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int answer = 0;
			long sum = 0;
			
			for(int i = 0; i < M; i++) {
				sum += arr[i];
			}
			
			if(sum < K) answer++;
			
			if(N == M) { // 예외 케이스
				sb.append(answer + "\n");
				continue;
			}
			
			for(int i = 1; i < N; i++) {
				sum -= arr[i -1];
				sum += arr[(i + M - 1) % N];
				if(sum < K) answer++;
			}
			
			sb.append(answer + "\n");
		}
		
		System.out.println(sb);
		
	}

}
