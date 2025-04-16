package day1205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_16637_백자민 {
	
	static int N,max;
	
	static List<Character> op;
	static List<Integer> num;
	
	static int calc(int oidx, int x, int y) {
		char tmp = op.get(oidx);
		if(tmp=='+')
			return x+y;
		if(tmp=='-')
			return x-y;
		if(tmp=='*')
			return x*y;
		
		return 1;
	}
	
	static void dfs(int idx, int sum) {
		if(idx>=N/2) {
			max = Math.max(max, sum);
			return;
		}
		
		dfs(idx+1, calc(idx, sum, num.get(idx+1))); //괄호X
		
		if(idx+1>=N/2) return;
		
		int tmp = calc(idx+1, num.get(idx+1), num.get(idx+2));
		dfs(idx+2, calc(idx, sum, tmp));
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		op = new ArrayList<>();
		num = new ArrayList<>();
		
		String operation = br.readLine();
		for(int i=0;i<N;i++) {
			if(i%2==0) 
				num.add(operation.charAt(i)-'0');
			else
				op.add(operation.charAt(i));
		}
		
		max = Integer.MIN_VALUE;
		dfs(0, num.get(0));
		System.out.println(max);
	}
}
