package day1017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2512_백자민 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] budgets = new int[N];
		int l=0,r=-1;
		
		for(int i=0;i<N;i++) {
			budgets[i] = Integer.parseInt(st.nextToken());
			r = Math.max(r, budgets[i]);
		}
		
		int M = Integer.parseInt(br.readLine());
		//end input
		
		while(l<=r) {
			int mid = (l+r)/2;
			long budget = 0;
			for(int i=0;i<N;i++) {
				if(budgets[i]>mid) 
					budget += mid;
				else
					budget += budgets[i];
			}
			if(budget<=M)
				l = mid+1;
			else
				r = mid-1;
		}
		
		System.out.println(r);
	}
}
