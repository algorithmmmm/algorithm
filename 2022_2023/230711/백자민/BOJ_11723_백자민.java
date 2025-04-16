package day0711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11723_백자민 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int bit = 0, n = 0;
		String op;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			op = st.nextToken();
			
			switch(op) {
				case "add":
					n = Integer.parseInt(st.nextToken())-1;
					bit = bit|(1<<n);
					break;
				case "remove":
					n = Integer.parseInt(st.nextToken())-1;
					bit = bit&~(1<<n);
					break;
				case "check":
					n = Integer.parseInt(st.nextToken())-1;
					String tmp = (bit&(1<<n))>0?"1":"0";
					sb.append(tmp+"\n");
					break;
				case "toggle":
					n = Integer.parseInt(st.nextToken())-1;
					bit = bit^(1<<n);
					break;
				case "all":
					bit = bit|(~0);
					break;
				case "empty":
					bit = bit&0;
					break;
			}
		}
		
		System.out.println(sb.toString());
	}
}
