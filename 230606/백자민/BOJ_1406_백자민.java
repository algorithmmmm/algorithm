package day0606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class BOJ_1406_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String origin = br.readLine();
		int M = Integer.parseInt(br.readLine());
		
		LinkedList<Character> list = new LinkedList<>();
		
		for(int i=0;i<origin.length();i++) {
			list.add(origin.charAt(i));
		}
		
		ListIterator<Character> iter = list.listIterator();
		while(iter.hasNext()) { //맨 뒤로 보내기(초기 커서 위치)
			iter.next();
		}
		
		for(int m=0;m<M;m++) {
			String str = br.readLine();
			char op = str.charAt(0);
			
			if(op=='P') {
				char target = str.charAt(2);
				iter.add(target);
			} else if(op=='L') {
				if(iter.hasPrevious())
					iter.previous();
			} else if(op=='D') {
				if(iter.hasNext())
					iter.next();
			} else if(op=='B') {
				if(iter.hasPrevious()) {
					iter.previous();
					iter.remove();					
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(char c: list) {
			sb.append(c);
		}
		
		System.out.println(sb.toString());
	}
}
