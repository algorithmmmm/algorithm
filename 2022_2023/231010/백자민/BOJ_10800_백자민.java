package day1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10800_백자민 {
	
	static int N;
	
	static Ball[] blist;
	static int[] result, colors;
	
	static void calc() {
		int idx = 0, sum = 0;
		
		for(Ball temp: blist) {
			while(blist[idx].size < temp.size) {
				sum += blist[idx].size;
				colors[blist[idx].color] += blist[idx].size;
				idx++;
			}
			result[temp.idx] = sum - colors[temp.color];
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		blist = new Ball[N];
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			blist[i] = new Ball(c,s,i);
		}//end input
		
		Arrays.sort(blist, (o1, o2) -> o1.size-o2.size);
		
		result = new int[N];
		colors = new int[N+1];
		
		calc();
		
		StringBuilder sb = new StringBuilder();
		for(int r: result) {
			sb.append(r+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static class Ball {
		int color,size,idx;
		
		public Ball(int color, int size, int idx) {
			this.color = color;
			this.size = size;
			this.idx = idx;
		}
	}
}
