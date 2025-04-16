package day_240109;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ_1038_백자민 {
	
	static List<Long> list;
	
	static void calc(long n, int len) {
		if(len>10) //10자리 이상은 만들 수 없음. 만들 수 있는 최대 숫자: 987654321
			return; 
		
		list.add(n);
		for(int i=0;i<n%10;i++) { //마지막 자릿수보다 작은 수 붙이기
			calc((n*10)+i, len+1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		list = new ArrayList<>();
		
		if(N<=10) {
			System.out.println(N);
			return;
		}
		
		if(N>1022) {
			System.out.println(-1);
			return;
		}
		
		for(int i=0;i<10;i++) {
			calc(i, 1);
		}
		Collections.sort(list);
		System.out.println(list.get(N));
	}
}
