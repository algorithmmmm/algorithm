package day_240109;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_12886_백자민 {
	
	static int[] calc(int x, int y) {
		int nx=x,ny=y;
		if(x>y) {
			nx-=y;
			ny+=y;
		} else {
			nx+=x;
			ny-=x;
		}
		
		return new int[] {nx,ny};
	}
	
	static boolean bfs(Point start) {
		if((start.a+start.b+start.c)%3!=0)
			return false;

		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[1501][1501]; //a,b,c의 합 최댓값 = 1500
		
		q.offer(start);
		visit[start.a][start.b] = true;
		
		while(!q.isEmpty()) {
			Point tmp = q.poll();
			int ta = tmp.a;
			int tb = tmp.b;
			int tc = tmp.c;
			
			if(ta==tb && tb==tc) return true;
			
			if(ta!=tc) {
				int[] nanc = calc(ta, tc);
				
				if(visit[nanc[0]][nanc[1]]) continue;
				
				q.offer(new Point(nanc[0], tb, nanc[1]));
				visit[nanc[0]][nanc[1]] = true;
			}
			
			if(tb!=tc) {
				int[] nbnc = calc(tb,tc);
				
				if(visit[nbnc[0]][nbnc[1]]) continue;
				
				q.offer(new Point(ta, nbnc[0], nbnc[1]));
				visit[nbnc[0]][nbnc[1]] = true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		System.out.println(bfs(new Point(a,b,c))?1:0);
	}
	
	static class Point {
		int a,b,c;
		public Point(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
}
