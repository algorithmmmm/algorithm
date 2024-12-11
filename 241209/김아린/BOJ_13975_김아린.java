package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_파일합치기3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			
			PriorityQueue<Long> pq = new PriorityQueue<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < K; i++) {
				int n = Integer.parseInt(st.nextToken());
				pq.add(Long.valueOf(n));
			}
			
			//구현
			long answer = 0;
			while(pq.size() >= 2) {
				long p1 = pq.poll();
				long p2 = pq.poll();
				
				long sum = p1 + p2;
				
				answer += sum;
				pq.add(sum);
			}
			
			//출력
			sb.append(answer + "\n");
		}
		
		System.out.println(sb);
	}

}
