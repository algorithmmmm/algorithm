package day0711;

import java.util.Scanner;

public class BOJ_1182_백자민 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int S = sc.nextInt();
		
		int[] arr = new int[N];
		
		for(int i=0;i<N;i++)
			arr[i] = sc.nextInt();
		//end input
		
		int answer = 0;
		for(int i=1;i<(1<<N);i++) {
			int tmp = 0;
			for(int j=0;j<N;j++) {
				if((i&(1<<j)) >0)
					tmp += arr[j];
			}
			
			if(tmp==S)
				answer++;
		}
		
		System.out.println(answer);
	}
}
