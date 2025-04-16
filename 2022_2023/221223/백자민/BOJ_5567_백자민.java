package day221223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* 
 * 1. ArrayList 배열에 친구 리스트 정보를 저장한다(양방향 모두 저장)
 * 2. dfs 탐색을 하면서 초대 조건에 맞는 사람의 번호를 true로 표시한다(friends 배열)
 * 		2-1. 상근이의 친구와 친구의 친구만 초대할 수 있으므로 탐색 깊이를 카운팅한다(cnt 변수) 
 * 
 * */

public class BOJ_5567_백자민 {
	
	static int n, m;
	static ArrayList<Integer>[] list;
	static boolean[] friends;
	
	static void dfs(int idx, int cnt) {
		if(cnt>1) 
			return;
		
		for(int l: list[idx]) {
			friends[l] = true;
			dfs(l, cnt+1);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		friends = new boolean[n+1];
		
		for(int i=1;i<=n;i++) 
			list[i] = new ArrayList<Integer>(); 
		
		StringTokenizer st;
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			list[start].add(end);
			list[end].add(start);
		}
		
		friends[1] = true;
		dfs(1, 0);
		
		int ans = 0;
		for(int f=2,size=friends.length;f<size;f++) {
			if(friends[f]) ans++;
		}
		
		System.out.println(ans);
	}
}
