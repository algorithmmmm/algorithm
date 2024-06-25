package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_가운데를말해요 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(i == 0) {
				maxHeap.add(num);
			} else if(i == 1) {
				if(num < maxHeap.peek()) {
					minHeap.add(maxHeap.poll());
					maxHeap.add(num);
				} else {
					minHeap.add(num);
				}
			} else {
				if(num <= minHeap.peek()) {
					maxHeap.add(num);
				} else {
					minHeap.add(num);
				}
				
				//항상 최소힙과 최대힙의 사이즈가 같거나 최대힙이 하나 더 크도록 조정 
				if(minHeap.size() > maxHeap.size()) {
					maxHeap.add(minHeap.poll());
				} else if(minHeap.size() + 1 < maxHeap.size()) {
					minHeap.add(maxHeap.poll());
				}
			}
			
			sb.append(maxHeap.peek()).append("\n");
		}
		
		System.out.println(sb);
	}

}
