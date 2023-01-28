package day230128;

import java.util.Scanner;

public class BOJ_16987_백자민 {
	
	static int N, result;
	static Egg[] list;
	
	static void dfs(int idx, int cnt) {
		
		if(idx == N) { //N개의 경우의 수를 모두 본 경우 
			result = Math.max(cnt, result);
			return;
		}
		
		if(list[idx].d <=0 || cnt == N-1) { //손에 쥔 계란이 깨졌거나 다른 계란이 다 깨진 경우 
			dfs(idx+1, cnt);
			return;
		}
		
		int temp=cnt;
		for(int i=0;i<N;i++) {
			if(i!=idx && list[i].d>0) { //안깨진 계란에 대해서 
				list[idx].d -= list[i].w;
				list[i].d -= list[idx].w;
				//계란 깨기 
					
				if(list[idx].d<=0) cnt++; //손에 쥔게 깨짐 
				if(list[i].d<=0) cnt++; //부딪힌게 깨짐 
				
				dfs(idx+1, cnt);//계속 진행 
					
				//원상복구 
				list[idx].d += list[i].w;
				list[i].d += list[idx].w;
				cnt = temp;
			}
		}

	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		list = new Egg[N];
		
		for(int i=0;i<N;i++) {
			list[i] = new Egg(sc.nextInt(), sc.nextInt());
		}
		//end input
		
		result = 0;
		dfs(0,0);
		System.out.println(result);
	}
	
	
	static class Egg {
		int w, d;

		public Egg(int w, int d) {
			this.w = w;
			this.d = d;
		}
		
	}
}
