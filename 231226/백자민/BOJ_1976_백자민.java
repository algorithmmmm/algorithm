package day1226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976_백자민 {
	
	static int N,M;
	
	static int[] parent;
	
	static int find(int n) {
		if(n==parent[n]) return n;
		
		return find(parent[n]);
	}
	
	static void union(int i, int j) {
		i = find(i);
		j = find(j);
		
		if(i<j)
			parent[j] = i;
		else
			parent[i] = j;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		for(int i=1;i<=N;i++)
			parent[i] = i;
		
		StringTokenizer st;
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp==1)
					union(i,j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int start = find(Integer.parseInt(st.nextToken()));
		for(int i=1;i<M;i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(start != find(tmp)) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
		
	}
}
