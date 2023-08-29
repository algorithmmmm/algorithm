package day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
10 10
RRLLLLLRRL
UDDDULLDUU
RLURDULRUD
DULULLURRD
RUUURUDUDL
ULRUULLLLU
RDLLDDRLDD
DLRUULLRDL
UUUUUDULLD
URUULLRRRU
*/

public class BOJ_16724_백자민 {
	
	static int N,M,cnt;
	
	static char[][] map;
	static boolean[][] visit,cycle;
	
	static int[] di = {-1,1,0,0}; //U-D-L-R
	static int[] dj = {0,0,-1,1};
	
	static int getDir(char c) {
		switch(c) {
		case 'U' :
			return 0;
		case 'D' :
			return 1;
		case 'L' :
			return 2;
		default :
			return 3;
		}
	}
	
	static void dfs(int i, int j) {
		visit[i][j] = true;
		int d = getDir(map[i][j]);
		
		int ni = i+di[d];
		int nj = j+dj[d];
		
		if(!visit[ni][nj])
			dfs(ni,nj);
		else
			if(!cycle[ni][nj])
				cnt++;
		
		cycle[i][j] = true;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visit = new boolean[N][M];
		cycle = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			map[i] = br.readLine().toCharArray();
		}
		//end input
		
		cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!visit[i][j]) 
					dfs(i,j);
			}
		}
		
		System.out.println(cnt);
	}
}
