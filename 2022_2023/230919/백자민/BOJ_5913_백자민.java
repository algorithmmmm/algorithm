package day0919;

import java.util.Scanner;

public class BOJ_5913_백자민 {
	
	static boolean[][] map;
	
	static int apple;
	
	static int answer;
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	static void move(int i, int j, int cnt) {
		if(i==4 && j==4) {
			if(cnt==apple)
				answer++;
			return;
		}
		
		for(int d=0;d<4;d++) {
			int ni = i+di[d];
			int nj = j+dj[d];
			
			if(ni<0 || ni>4 || nj<0 || nj>4) continue;
			if(map[ni][nj]) continue;
			
			map[ni][nj] = true;
			move(ni,nj,cnt+1);
			map[ni][nj] = false;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		map = new boolean[5][5];
		
		apple = 25;
		answer = 0;
		
		int K = sc.nextInt();
		
		for(int k=0;k<K;k++) {
			int i = sc.nextInt()-1;
			int j = sc.nextInt()-1;
			
			map[i][j] = true;
			apple--;
		}//end input
		
		
		map[0][0] = true;
		move(0,0,1);
		
		System.out.println(answer);
	}
}
