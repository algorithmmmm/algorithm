package day0801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11437_백자민 {
	
	static List<Integer>[] list;
	static int[] parent, depth;
	
	static void lca(int i, int j) {
		int id = depth[i];
		int	jd = depth[j];
		
		while(id>jd) {
			i = parent[i];
			id--;
		}
		
		while(jd>id) {
			j = parent[j];
			jd--;
		}
		
		while(i!=j) {
			i = parent[i];
			j = parent[j];
		}
		System.out.println(i);
	}
	
	static void dfs(int tmp, int tp, int td) {
		depth[tmp] = td;
		parent[tmp] = tp;
		
		for(int i: list[tmp]) {
			if(i!=tp) {
				dfs(i, tmp, td+1);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N+1];
		parent = new int[N+1];
		depth = new int[N+1];
		
		for(int n=0;n<=N;n++) {
			list[n] = new ArrayList<>(); 
		}
		
		
		for(int n=1;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			list[i].add(j);
			list[j].add(i);
		}
		
		dfs(1,0,1);
		
		int M = Integer.parseInt(br.readLine());
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			lca(i,j);
		}

	}
}
