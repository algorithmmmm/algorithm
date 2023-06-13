package day0613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_23085_백자민 {
	
	static int N;
	static int K;
	
	static int bfs(int T) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{T, 0});
		boolean[] visit = new boolean[N+1];
		visit[T] = true;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int t = temp[0];
			int cnt = temp[1];
			int h = N-t;
			
			if(t==N) return cnt;
			
			for(int i=0;i<=K;i++) {
				int tmpb = i;
				int tmpf = K-i;
				
				if(tmpb>t || tmpf>h) continue;
				
				if(visit[t-tmpb+tmpf]) continue;
				
				visit[t-tmpb+tmpf] = true;
				q.add(new int[] {t-tmpb+tmpf, cnt+1});
			}
		}
		
		return -1;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		char[] coins = br.readLine().toCharArray();
		
		int T=0;
		for(char c: coins) {
			if(c=='T')
				T++;
		}
		
		if(T==N)
			System.out.println(0);
		else {
			System.out.println(bfs(T));
		}
		
	}
}
