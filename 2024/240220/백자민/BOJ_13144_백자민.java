package day240220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_13144_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} //end input
		
		HashSet<Integer> set = new HashSet<>();
		int l=0,r=1;
		long ans=0;
		
		int idx=0;
		for(int i=0;i<N;i++) {
			if(!set.contains(arr[i])) {
				set.add(arr[i]);
				continue;
			}
			
			for(int j=idx;j<i;j++) {
				ans += i-j;
				idx++;
				
				if(arr[i]==arr[j]) break;
				
				set.remove(arr[j]);
			}
		}
		
		while(idx<N) {
			ans += N-idx;
			idx++;
		}
		
		System.out.println(ans);
		
	}
}
