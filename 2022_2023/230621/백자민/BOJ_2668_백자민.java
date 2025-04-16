package day0620;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_2668_백자민 {
	
	static int N;
	static int[] nums;
	static boolean[] visit;
	
	static ArrayList<Integer> list;
	
	static void dfs(int idx, int target) {
		if(!visit[nums[idx]]) {
			visit[nums[idx]] = true;
			dfs(nums[idx], target);
			visit[nums[idx]] = false;
		}
		
		if(nums[idx]==target)
			list.add(target);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		nums = new int[N+1];
		visit = new boolean[N+1];
		list = new ArrayList<>();
		
		for(int i=1;i<=N;i++) {
			nums[i] = sc.nextInt();
		}
		
		for(int i=1;i<=N;i++) {
			visit[i] = true;
			dfs(i,i);
			visit[i] = false;
		}
		
		System.out.println(list.size());
		Collections.sort(list);
		for(int i: list)
			System.out.println(i);
	}
}
