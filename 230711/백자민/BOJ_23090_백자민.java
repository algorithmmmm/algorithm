package day0711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_23090_백자민 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>();
		StringBuilder sb = new StringBuilder();
		
		int pmid = 0;
		long pdist = 0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Math.abs(Integer.parseInt(st.nextToken()));
			int y = Integer.parseInt(st.nextToken());
						
			int pmax = maxPQ.size();
			int pmin = minPQ.size();
			
			if(maxPQ.size()==minPQ.size())
				maxPQ.offer(y);
			else
				minPQ.offer(y);
			
			if(!maxPQ.isEmpty() && !minPQ.isEmpty() && maxPQ.peek()>minPQ.peek()) {
				int temp = minPQ.poll();
				minPQ.offer(maxPQ.poll());
				maxPQ.offer(temp);
			}
			
			int tmid = maxPQ.peek();
			long tdist;
			
			if(pdist==0 && pmid==0)
				tdist = x;
			else
				tdist = pdist + Math.abs(pmid-tmid) * Math.abs(pmax-pmin) + Math.abs(tmid-y)+x;
			
			sb.append(tmid+" "+tdist+"\n");
			pmid = tmid;
			pdist = tdist;
		}
		System.out.println(sb.toString());
	}
}
