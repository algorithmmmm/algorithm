package day0912;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_15666_백자민 {
	
	static int N,M;
	
	static int[] arr,result;
	
	static StringBuilder sb;
	
	static void dfs(int idx, int depth) {
		if(depth==M) {
			for(int r:result) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		int prev = -1;
		for(int i=idx;i<N;i++) {
			int tmp = arr[i];
			if(prev!=tmp) {
				prev = tmp;
				result[depth] = arr[i];
				dfs(i,depth+1);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		result = new int[M];
		sb = new StringBuilder();
		
		dfs(0,0);
		System.out.println(sb.toString());
	}
}
