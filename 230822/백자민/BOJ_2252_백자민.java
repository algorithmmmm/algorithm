package day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] ecnt = new int[N+1];
		
		ArrayList<Integer>[] graph = new ArrayList[N+1];
		for(int i=1;i<=N;i++)
			graph[i] = new ArrayList<Integer>();
		
		int a,b;
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			ecnt[b]++;
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1,size=ecnt.length;i<size;i++) {
			if(ecnt[i]==0)
				q.offer(i);
		}
		
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			int tmp = q.poll();
			sb.append(tmp+" ");
			
			for(int i=0,size=graph[tmp].size();i<size;i++) {
				int cur = graph[tmp].get(i);
				ecnt[cur]--;
				
				if(ecnt[cur]==0)
					q.offer(cur);
			}
		}
		
		System.out.println(sb.toString());
	}
}
