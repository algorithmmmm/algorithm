package day240305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16938_백자민 {
	
	static int N,L,R,X,ans;
	
	static int[] arr;
	
	static void pick() {
		
		int cnt,l,r,sum;
		
		for(int i=1;i<(1<<N);i++) {
			cnt=0;
			l=Integer.MAX_VALUE;
			r=Integer.MIN_VALUE;
			sum=0;
			
			for(int j=0;j<N;j++) {
				if((i&(1<<j))>0) {
					cnt++;
					l = Math.min(l, arr[j]);
					r = Math.max(r, arr[j]);
					sum += arr[j];
				}
			}
			
			if(cnt<2 || sum<L || sum>R || r-l<X) continue;
			
			ans++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} //end input
		
		Arrays.sort(arr);
		
		ans=0;
		pick();
		
		System.out.println(ans);
	}
}
