package day0704;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110_백자민 {
	
	static int N,C;
	static int[] house;
	
	static boolean isPossible(int dist) {
		int cnt=1;
		int loc = house[0];
		
		for(int i=1;i<N;i++) {
			int l = house[i];
			
			if(l-loc >= dist) {
				cnt++;
				loc = l;
			}
		}
		
		return cnt<C;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		house = new int[N];
		
		for(int i=0;i<N;i++)
			house[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(house);
		
		int l = 1;
		int r = house[N-1]-house[0]+1;
		
		while(l<r) {
			int mid = (l+r)/2;
			
			if(isPossible(mid))
				r = mid;
			else
				l = mid+1;
		}
		
		System.out.println(l-1);
	}
}
