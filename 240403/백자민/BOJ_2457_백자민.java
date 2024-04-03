package day240403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2457_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Flower> pq = new PriorityQueue<>();
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());
			
			int sdate = sm*100+sd;
			int edate = em*100+ed;
			
			pq.offer(new Flower(sdate, edate));
		} //end input
		
		int ans=0, last=0;
		int start=301;
		while(!pq.isEmpty() && start<1201) {
			boolean flag = false;

			while(!pq.isEmpty()) {
				
				Flower f = pq.poll();
				
				if(f.start>start) { //꽃이 피는 날이 이어지지 않는 경우
					pq.offer(f);
					break;
				}
					
				if(f.end>last) { //꽃이 피는 기간에 공백이 생기지 않는 경우 + 새로운 꽃이 피어있는 기간이 더 긴 경우
					last = f.end;
					flag = true;
				}
			}
			
			if(!flag) break;
			
			start = last;
			ans++;
		}
		
		if(last<1201) //마지막날까지 꽃이 피어있지 않은 경우  
			System.out.println(0);
		else
			System.out.println(ans);
	}
	
	static class Flower implements Comparable<Flower> {
		int start, end;
		
		public Flower(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Flower o) {
			if(this.start==o.start)
				return o.end-this.end;
			
			return this.start-o.start;
		}
	}
}
