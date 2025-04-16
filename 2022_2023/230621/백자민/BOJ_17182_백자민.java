package day0620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17182_백자민 {
	
	static int N,K,ans;
	
	static boolean[] visit;
	
	static int[][] map;
	
	static void dfs(int tmp, int cnt, int sum) {
		if(cnt==N-1) {
			ans = Math.min(ans, sum);
			return;
		}
		
		for(int n=0;n<N;n++) {
			if(!visit[n]) {
				visit[n] = true;
				dfs(n, cnt+1, sum+map[tmp][n]);
				visit[n] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visit = new boolean[N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//end input
		
		for(int k=0;k<N;k++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(i==j) continue;
					
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		
		visit[K] = true;
		ans=Integer.MAX_VALUE;
		dfs(K, 0, 0);
		System.out.println(ans);
	}
}
