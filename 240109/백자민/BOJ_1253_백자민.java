package day_240109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} //end input
		
		Arrays.sort(arr);
		
		int cnt=0;
		
		for(int n=0;n<N;n++) {
			int l=0,r=N-1;
			
			while(l<r) {
				if(n==l) {
					l++;
					continue;
				}
				if(r==n) {
					r--;
					continue;
				}
				
				int tmp = arr[l]+arr[r];
				
				if(tmp>arr[n]) {
					r--;
				} else if(tmp<arr[n]) {
					l++;
				} else {
					cnt++;
					break;
				}
				
				
			}
		}
		
		System.out.println(cnt);
	}
}
