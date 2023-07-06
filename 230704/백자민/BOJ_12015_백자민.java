package day0704;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 
6
1 2 4 2 3 4

6
50 30 20 20 10 10
정답: 1 / 오답: 2
 * */

public class BOJ_12015_백자민 {
	
	static int[] arr;
	static ArrayList<Integer> list;
	
	static int find(int size, int tmp) {
		int l=1, r=size-1;
		while(l<r) {
			int mid = (l+r)/2;
			if(list.get(mid)<tmp)
				l = mid+1;
			else
				r = mid;
		}
		
		return r;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		
		for(int i=0;i<N;i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		
		list.add(0); //초기값 세팅
		
		for(int n: arr) {
			if(list.get(list.size()-1)<n) {
				list.add(n);
			} else {
				int idx = find(list.size(), n); //해당 원소가 들어갈 자리 탐색(이분탐색) 
				list.set(idx,n);
				
			}
		}
		
		System.out.println(list.size()-1);
	}
}
