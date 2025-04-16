package day1024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11561_백자민 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=0;t<T;t++) {
			long N = Long.parseLong(br.readLine());
			long s=0,e=(long)Math.sqrt((2*N-1));
			long answer = 0;
			
			while(s<=e) {
				long mid = (s+e)/2;
				long sum = (mid)*(mid+1)/2;
				
				if(sum>N) { //불가능한 경우 
					e = mid-1;
				} else {
					answer = Math.max(mid, answer); //최댓값 갱신
					s = mid+1;
				}
			}
			
			sb.append(answer+"\n");
		}
		
		System.out.println(sb.toString());
	}
}
