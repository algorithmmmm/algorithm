package day240409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20366_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] snowballs = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			snowballs[i] = Integer.parseInt(st.nextToken());
		} //end input
		
		Arrays.sort(snowballs); //오름차순으로 정렬
		
		long ans = Long.MAX_VALUE;
		//양쪽 끝 범위를 정해두고 안에서 2개 조합해보기
		for(int i=0;i<N-3;i++) {
			for(int j=i+3;j<N;j++) {
				
				long elsa = snowballs[i]+snowballs[j];
				
				int l=i+1,r=j-1;
				while(l<r) {
					long anna = snowballs[l]+snowballs[r];					
					ans = Math.min(ans, Math.abs(elsa-anna));
					
					if(elsa-anna<0) r--;
					else l++;
				}
			}
		}
		
		System.out.println(ans);
	}
}
