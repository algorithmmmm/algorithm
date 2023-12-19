package day1219;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_13549_백자민 {
	
	static int N,K,min,max=100000;
	
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[] visit = new boolean[100001];
		
		q.offer(new Point(N,0));
		
		while(!q.isEmpty()) {
			int tmp = q.peek().pos;
			int time = q.poll().time;
			
			visit[tmp] = true;
			
			if(tmp==K) {
				min = Math.min(min, time);
				continue;
			}
			
			if(tmp*2<=max && !visit[tmp*2])
				q.offer(new Point(tmp*2, time));
			if(tmp+1<=max && !visit[tmp+1])
				q.offer(new Point(tmp+1, time+1));
			if(tmp-1>=0 && !visit[tmp-1])
				q.offer(new Point(tmp-1, time+1));
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		min = Integer.MAX_VALUE;
		bfs();
		System.out.println(min);
		
	}
	
	static class Point {
		int pos, time;
		public Point(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}
	}
}
