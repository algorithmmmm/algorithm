package day240123;

import java.util.Scanner;

public class BOJ_2671_백자민 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		
		String regx = "^(100+1+|01)+$";
		
		System.out.println(s.matches(regx)?"SUBMARINE":"NOISE");
	}
}
