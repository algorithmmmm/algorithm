package day240507;

import java.util.Scanner;

public class BOJ_15831_백자민 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int B = sc.nextInt();
		int W = sc.nextInt();
		
		char[] stone = sc.next().toCharArray();
		//end input
		
		int l=0,r=0,max=0;
		int tmpb=0,tmpw=0; //현재 가진 조약돌 개수
		
		while(r<N) {
			if(tmpb>B) { //돌을 너무 많이 가진 경우
				if(stone[l]=='B') tmpb--;
				else tmpw--;
				
				l++;
			} else { //그 외 경우 더 긴 구간 탐색		
				if(stone[r]=='B') tmpb++;
				else tmpw++;
				
				r++;
			}
			
			if(tmpb<=B && tmpw>=W) { //조건에 부합하는 경우
				max = Math.max(max, r-l); //구간 최대 길이 갱신 
			} 
		}
		
		System.out.println(max);
	}
}
