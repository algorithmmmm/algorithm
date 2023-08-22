package day0822;

import java.util.Scanner;

public class BOJ_1629_백자민 {
	
	static long calc(int a, int b, int c) {
		if(b==1)
			return a%c;
		
		long tmp = calc(a,b/2,c);
		
		if(b%2==1) 
			return (tmp*tmp%c)*a%c;
		
		return (tmp*tmp)%c;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		long answer = calc(A,B,C);
		System.out.println(answer);
	}
}
