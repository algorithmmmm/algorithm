package day1212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15684_백자민 {
	
	static int N,M,H,ans;
	
	static int[][] map;
	
	static boolean flag;
	
	static boolean isPossible() { //조건을 만족하는 사다리인지 체크
		for(int n=1;n<=N;n++) {
			int ti=1, tj=n;
			
			for(int h=0;h<H;h++) {
				if(map[ti][tj]==1) tj++; //가로선타고 오른쪽 이동
				else if(map[ti][tj]==-1) tj--; //가로선타고 왼쪽 이동
				
				ti++;//아래로 이동
			}
			
			if(tj!=n) return false; //하나라도 일치하지 않으면 안됨
		}
		
		return true;
	}
	
	static void dfs(int h, int cnt) {
		
		if(flag) return; //가능한 경우를 찾았다면 탐색 종료
		
		if(ans==cnt) { //조합 완성
			if(isPossible()) flag = true;
			
			return;
		}
		
		for(int i=h;i<=H;i++) {
			for(int j=1;j<N;j++) {
				if(map[i][j]!=0 || map[i][j+1]!=0) continue; //이미 가로선이 존재하는 경우
				
				map[i][j] = 1; //오른쪽 이동 가로선
				map[i][j+1] = -1; //왼쪽 이동 가로선
				
				dfs(i, cnt+1);
				
				map[i][j] = 0;
				map[i][j+1] = 0;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H+1][N+1];
		
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			map[i][j] = 1;//오른쪽 이동 가능
			map[i][j+1] = -1; //왼쪽 이동 가능
		}
		
		flag = false;
		for(int i=0;i<4;i++) {
			ans = i;
			dfs(1,0);
			
			if(flag) break; //가능한 경우를 발견함
		}
		
		System.out.println(flag?ans:-1);
	}
}
