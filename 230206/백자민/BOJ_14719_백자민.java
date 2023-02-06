package day230206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719_백자민 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[] map = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<W;i++) 
			map[i] = Integer.parseInt(st.nextToken());
		
		int left=0, right=0, sum=0;
		for(int i=1;i<W-1;i++) {
			
			for(int l=0;l<i;l++) {
				left = left>map[l]?left:map[l];
			}
			
			for(int r=i+1;r<W;r++) {
				right = right>map[r]?right:map[r];
			}
			
			if(map[i]<left && map[i]<right)
				sum += Math.min(left, right) - map[i];
			
			//초기화
			left = 0;
			right = 0;
			
		}
		
		System.out.println(sum);
		
	}
}
