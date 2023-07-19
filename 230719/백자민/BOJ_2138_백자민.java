package day0719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2138_백자민 {
	
	static int N, ans;
	static char[] target;
	
	static void bulb(int idx, int cnt, char[] org) {
		if(idx==N) {
			if(org[idx-1]==target[idx-1])
				ans = Math.min(ans, cnt);
			return;
		}
		
		if(org[idx-1]!=target[idx-1]) 
			bulb(idx+1, cnt+1, turnSwitch(idx, org));
		else 
			bulb(idx+1, cnt, org);
	}
	
	static char[] turnSwitch(int idx, char[] org) {
		for(int i=idx-1;i<=idx+1;i++) {
			if(i<0 || i>=N) continue;
			org[i] = org[i]=='0'?'1':'0';
		}
		
		return org;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		char[] origin1 = br.readLine().toCharArray();
		char[] origin2 = origin1.clone();
		target = br.readLine().toCharArray();
		
		ans = Integer.MAX_VALUE;
		bulb(1,0,origin1);
		bulb(1,1,turnSwitch(0,origin2));
		
		System.out.println(ans==Integer.MAX_VALUE?-1:ans);
	}
}
