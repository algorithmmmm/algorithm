package day1226;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_2504_백자민 {
	
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		
		Stack<Character> stack = new Stack<>();
		
		int ans=0,tmp=1;
		for(int i=0,size=s.length();i<size;i++) {
			char c = s.charAt(i);
			
			
			if(c=='(') {
				stack.push(c);
				tmp*=2;
			} else if(c=='[') {
				stack.push(c);
				tmp*=3;
			} else if(c==')') {
				if(stack.isEmpty() || stack.peek()!='(') {
					ans = 0;
					break;
				}
				if(s.charAt(i-1)=='(') {
					ans += tmp;
				}
				stack.pop();
				tmp/=2;
			} else if(c==']') {
				if(stack.isEmpty() || stack.peek()!='[') {
					ans = 0;
					break;
				}
				if(s.charAt(i-1)=='[') {
					ans += tmp;
				}
				stack.pop();
				tmp/=3;
			}
		}
		
		if(!stack.isEmpty())
			System.out.println(0);
		else
			System.out.println(ans);
	}
}
