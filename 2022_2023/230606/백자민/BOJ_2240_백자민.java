package day0606;

import java.util.Scanner;

public class BOJ_2240_백자민 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
	    int W = sc.nextInt();
	 
	    int[][][] dp = new int[T+1][3][W+2]; //dp[시간][현재위치][남은이동횟수]
	    int[] arr = new int[T+1];
	    int ans = 0;
	 
	    for(int t=1;t<=T;t++) {
	      arr[t] = sc.nextInt();
	    }
	    //end input
	 
	    for(int t=1;t<=T;t++) {
	    	for(int w=1;w<=W+1;w++) {
	    		if(arr[t]==1) {
	    			dp[t][1][w] = Math.max(dp[t-1][1][w], dp[t-1][2][w-1])+1; //자두를 얻음 
	    			dp[t][2][w] = Math.max(dp[t-1][1][w-1], dp[t-1][2][w]); //자두 못얻음
	    		} else {
	    			if(t==1 && w==1) //자두는 1번 나무에서 시작하는데 자두가 2번에 떨어짐 = 못얻음 
	    				continue;
	    			dp[t][1][w] = Math.max(dp[t-1][1][w], dp[t-1][2][w-1]);
	    			dp[t][2][w] = Math.max(dp[t-1][1][w-1], dp[t-1][2][w])+1;
	    		}
	    	}
	    }
	    
	    for(int w=1;w<=W+1;w++) {
	    	int tmp = Math.max(dp[T][1][w], dp[T][2][w]);
	    	ans = Math.max(ans, tmp);
	    }
	    
	    System.out.println(ans);
	}
}
