package day0620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_6198_백자민 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> buildings = new Stack<>();
		
		int N = Integer.parseInt(br.readLine());
		long cnt = 0;
		
		int temp=0;
		for(int i=0;i<N;i++) {
			temp = Integer.parseInt(br.readLine());
			while(!buildings.isEmpty() && buildings.peek()<=temp) {
				buildings.pop();
			}
			buildings.add(temp);
			cnt += buildings.size()-1;
		}
		
		System.out.println(cnt);
		
	}
}
