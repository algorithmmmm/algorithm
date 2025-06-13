package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_도서관 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 큐 내림차순 정렬
		PriorityQueue<Integer> positive = new PriorityQueue<>((o1, o2) -> o2 - o1);
		PriorityQueue<Integer> negative = new PriorityQueue<>((o1, o2) -> o2 - o1);
		int max = 0; // 가장 먼 거리에 있는 책 위치
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int dist = Integer.parseInt(st.nextToken());
			
			if(dist > 0) {
				positive.add(dist);
				max = Math.max(max, dist);
			} else {
				negative.add(Math.abs(dist));
				max = Math.max(max, Math.abs(dist));
			}
		}
		
		int answer = 0;
		int count = 0;
		while(!positive.isEmpty()) {
			int p = positive.poll();
			
			if(count % M == 0) { // M개 마다 책 갖다놓기
				answer += p * 2;
			}
			count++;
		}
		
		count = 0;
		while(!negative.isEmpty()) {
			int p = negative.poll();
			
			if(count % M == 0) {
				answer += p * 2;
			}
			count++;
		}
		
		answer -= max; // 가장 먼거리에 놓고는 0으로 안 돌아와도 됨
		
		System.out.println(answer);
	}

}
