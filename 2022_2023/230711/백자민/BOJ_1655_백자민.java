package day0711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ_1655_백자민 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>(Collections.reverseOrder()); //큰 수가 우선순위 
		PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>(); //작은 수가 우선순위 
		
		for(int i=0;i<N;i++) {
			int n = Integer.parseInt(br.readLine());
			
			if(maxPQ.size()==minPQ.size())
				maxPQ.offer(n);
			else 
				minPQ.offer(n);
			
			if(!maxPQ.isEmpty() && !minPQ.isEmpty() && maxPQ.peek()>minPQ.peek()) {
				int temp = maxPQ.poll();
				maxPQ.offer(minPQ.poll());
				minPQ.offer(temp);
			}
			
			sb.append(maxPQ.peek()+"\n");
		}
		
		System.out.println(sb.toString());
	}
}
