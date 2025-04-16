package day240220;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_11964_백자민 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int A = sc.nextInt();
		int B = sc.nextInt();
		
		int max = 0;
		
		Queue<Bessie> q = new LinkedList<>();
		boolean[] visit = new boolean[T+1];
		
		q.offer(new Bessie(0, false));
		visit[0] = true;
		
		while(!q.isEmpty()) {
			Bessie tmp = q.poll();
			
			if(tmp.fullness==T) {
				max=T;
				break;
			}
			
			max = Math.max(max, tmp.fullness);
			
			int eatA = tmp.fullness+A;
			if(eatA<=T && !visit[eatA]) {
				q.offer(new Bessie(eatA, tmp.isDrink));
				visit[eatA] = true;
			}
			
			int eatB = tmp.fullness+B;
			if(eatB<=T && !visit[eatB]) {
				q.offer(new Bessie(eatB, tmp.isDrink));
				visit[eatB] = true;
			}
			
			int drink = tmp.fullness/2;
			if(!tmp.isDrink && !visit[drink]) {
				q.offer(new Bessie(drink, true));
				visit[drink] = true;
 			}
		}
		
		System.out.println(max);
	}
	
	static class Bessie {
		int fullness;
		boolean isDrink;
		
		public Bessie(int fullness, boolean isDrink) {
			this.fullness = fullness;
			this.isDrink = isDrink;
		}
	}
}
