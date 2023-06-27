package day0627;

import java.util.Scanner;

public class BOJ_17266_백자민 {
	
	static int N,M;
	static int[] lights;
	
	static boolean check(int height) {
		int tmp = 0;
		
		for(int i=0;i<M;i++) {
			if(lights[i]-height<=tmp) {
				tmp=lights[i]+height;
				continue;
			}
			return false;
		}
		
		if(N-tmp>0)
			return false;
		
		return true;	
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		lights = new int[M];
		
		for(int i=0;i<M;i++)
			lights[i] = sc.nextInt();
		
		//end input
		
		int left=1,right=N, ans=Integer.MAX_VALUE;
		
		while(left<=right) {
			int mid = (left+right)/2;
			
			if(check(mid)) {
				ans = Math.min(ans, mid);
				right = mid-1;
				continue;
			}
			
			left = mid+1;
		}
		
		System.out.println(ans);
	}
}