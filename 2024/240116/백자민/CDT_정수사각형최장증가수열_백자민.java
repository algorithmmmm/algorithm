package day_240116;

import java.io.*;
import java.util.*;

public class CDT_정수사각형최장증가수열_백자민 {
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		int[][] dp = new int[N][N];

		StringTokenizer st;
		PriorityQueue<Point> pq = new PriorityQueue<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				pq.offer(new Point(i,j,map[i][j]));
			}
		} // end input

		int ans = Integer.MIN_VALUE;
		while(!pq.isEmpty()) {
			Point tmp = pq.poll();
			int ti = tmp.i;
			int tj = tmp.j;
			int tn = tmp.n;

			for (int d=0;d<4;d++) {
				int ni = ti+di[d];
				int nj = tj+dj[d];

				if (ni<0 || ni>=N || nj<0 || nj>=N)
					continue;
				if (map[ni][nj] <= tn)
					continue;

				dp[ni][nj] = Math.max(dp[ni][nj],dp[ti][tj]+1);
			}

			ans = Math.max(dp[ti][tj],ans);
		}

		System.out.println(ans+1);

	}

	static class Point implements Comparable<Point> {
		int i,j,n;

		public Point(int i,int j,int n) {
			this.i = i;
			this.j = j;
			this.n = n;
		}

		@Override
		public int compareTo(Point o) {
			return this.n-o.n;
		}
	}
}
