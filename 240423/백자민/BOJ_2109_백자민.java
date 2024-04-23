package day240423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2109_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Lecture> pq = new PriorityQueue<>();
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			pq.offer(new Lecture(p,d));
		} //end input
		
		int[] schedule = new int[100001]; //d일째 받을 강연료 저장
		while(!pq.isEmpty()) {
			int td = pq.peek().d;
			int tp = pq.poll().p;
			
			//d일 안에 강의를 해야 하므로 1일~d일 사이에 현재 강의보다 강의료가 적은 강의를 빼기
			//없으면 현재 강의 포기
			for(int i=td;i>0;i--) {
				if(schedule[i]<tp) {
					schedule[i] = tp;
					break;
				}
			}
		}
		
		int ans=0;
		for(int s: schedule) {
			ans+=s;
		}
		
		System.out.println(ans);
	}
	
	static class Lecture implements Comparable<Lecture> {
		int p,d;
		
		public Lecture(int p, int d) {
			this.p = p;
			this.d = d;
		}
		
		@Override
		public int compareTo(Lecture l) { //비용 기준 내림차순 정렬
			if(this.p==l.p) {
				return l.d-this.d;
			}
			return l.p-this.p;
		}
	}
}
