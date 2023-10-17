package day1017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_7490_백자민 {
	
	static int N;
	static ArrayList<String> answer;
	static StringBuilder sb;
	
	static void dfs(int n, int tmp, int sum, int op, String s) {
		if(n==N) {
			sum += (tmp*op);
			
			if(sum==0)
				sb.append(s+"\n");
			
			return;
		}
		
		dfs(n+1,tmp*10+n+1, sum, op, s+" "+(n+1));
		dfs(n+1, n+1, sum+tmp*op, 1, s+"+"+(n+1));
		dfs(n+1, n+1, sum+tmp*op, -1, s+"-"+(n+1));
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		sb = new StringBuilder();
		for(int t=0;t<T;t++) {
			N = Integer.parseInt(br.readLine());
			dfs(1,1,0,1,"1");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
