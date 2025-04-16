package day1212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2467_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Long.parseLong(st.nextToken());
		} //end input
		
		int l=0,r=N-1;
		long min = Long.MAX_VALUE;
		int lidx=0,ridx=0;
		while(l<r) {
			long tmp = arr[l]+arr[r];
			if(min>Math.abs(tmp)) {
				min = Math.abs(tmp);
				lidx=l;
				ridx=r;
			}
			
			if(tmp>0) {
				r--;				
			} else if(tmp<0) {
				l++;				
			} else {
				lidx=l;
				ridx=r;
				break;
			}
		}
		
		System.out.println(arr[lidx]+" "+arr[ridx]);
	}
}
