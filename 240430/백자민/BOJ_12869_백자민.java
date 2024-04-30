package day240429;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_12869_백자민 {
		
	static int N,ans;
	
	static int[] scv;
	
	static boolean[][][] visit; //중복체크를 위한 배열
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		scv = new int[3]; //scv수가 3보다 작다면 초기 체력은 0으로 간주
		for(int i=0;i<N;i++) {
			scv[i] = sc.nextInt();
		} //end input
		
		Arrays.sort(scv);
		visit = new boolean[scv[2]+1][scv[1]+1][scv[0]+1];
		
		ans = Integer.MAX_VALUE;
		attack(scv[2], scv[1], scv[0], 0); //a=가장 큰 체력, b=중간, c=가장 적은 체력
		
		System.out.println(ans);
	}
	
	static void attack(int a, int b, int c, int cnt) {
		
		if(a<=0 && b<=0 && c<=0) { //모든 svc체력이 0이하면 최솟값 갱신
			ans = Math.min(ans,  cnt);
			return;
		}
		
		if(ans<cnt) return; //이미 최솟값을 넘긴 경우
		
		//음수는 0으로 보정(배열 index 에러 방지)
		a = Math.max(a,0);
		b = Math.max(b,0);
		c = Math.max(c,0);
		
		int[] tmp = {a,b,c};
		Arrays.sort(tmp); //중복 체크를 위해 정렬상태 유지
		
		a = tmp[2];
		b = tmp[1];
		c = tmp[0];
		
		if(visit[a][b][c]) return; //중복체크
		
		visit[a][b][c] = true;
		
		//6가지 가능한 공격의 경우대로 공격 진행
		attack(a-9,b-3,c-1, cnt+1);
		attack(a-9,b-1,c-3, cnt+1);
		attack(a-3,b-1,c-9, cnt+1);
		attack(a-3,b-9,c-1, cnt+1);
		attack(a-1,b-3,c-9, cnt+1);
		attack(a-1,b-9,c-3, cnt+1);
	}
}
