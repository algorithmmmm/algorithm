package day0627;

import java.util.Scanner;

public class BOJ_2023_백자민 {
	
	static int N;
	static StringBuilder sb;
	
	static boolean isPrime(int n) {
		if(n==0 || n==1)
			return false;
		
		for(int i=2;i<=Math.sqrt(n);i++) {
			if(n%i==0)
				return false;
		}
		
		return true;
	}
	
	static void find(int num, int cnt) {
		if(cnt==N) {
			if(isPrime(num))
				sb.append(num).append("\n");
			return;
		}
		
		for(int i=0;i<10;i++) {
			int tmp = num*10+i;
			if(isPrime(tmp))
				find(tmp, cnt+1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sb = new StringBuilder();
		find(0,0);
		System.out.println(sb.toString());
	}
}
