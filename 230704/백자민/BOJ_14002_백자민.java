package day0704;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_14002_백자민 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		for(int n=0;n<N;n++) {
			arr[n] = sc.nextInt();
		}
		
		int ans = Integer.MIN_VALUE;
		for(int k=0;k<N;k++) {
			dp[k] = 1;
			for(int i=0;i<k;i++) {
				if(arr[i]<arr[k] && dp[k]<=dp[i]) {
					dp[k] = dp[i]+1;
				}
			}
			ans = Math.max(ans, dp[k]);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(ans+"\n");
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i=N-1;i>=0;i--) {
			if(ans==dp[i]) {
				stack.push(arr[i]);
				ans--;
			}
		}
		
		while(!stack.isEmpty())
			sb.append(stack.pop()+" ");
		
		System.out.println(sb.toString());
	}
}
