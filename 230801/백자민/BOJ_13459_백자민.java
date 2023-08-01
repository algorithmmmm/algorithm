package day0801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13459_백자민 {
	
	static int N,M;
	
	static Ball[] gem;
	
	static char[][] map;
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	static Ball move(int i, int j, int d, boolean isRed) {
		int ni,nj;
		
		while(true) {
			ni = i+di[d];
			nj = j+dj[d];
			
			if(map[ni][nj]=='#') { //더 이상 이동할 수 없으면 멈추기
				return new Ball(i,j);
			} else if(map[ni][nj]=='O') { //탈출구면 일단 도착
				return new Ball(ni,nj);
			}
			
			i = ni;
			j = nj;
		}
	}
	
	static int bfs() {
		Queue<Ball[]> q = new LinkedList<>();
		q.add(gem);
		int cnt=0;
		
		while(!q.isEmpty()) {
			
			if(cnt>=10) return 0; //10번 이상 기울인 경우
			
			for(int i=0,size=q.size();i<size;i++) {
			
				Ball[] tmp = q.poll();
				for(int d=0;d<4;d++) {
					
					Ball red = move(tmp[0].i, tmp[0].j, d, true); //빨간 구슬 이동
					Ball blue = move(tmp[1].i, tmp[1].j, d, false); //파란 구슬 이동
					
					if(map[red.i][red.j]=='O' && map[blue.i][blue.j]!='O')	return 1; //빨간 구슬이 출구에 빠진 경우 
					if(map[blue.i][blue.j]=='O') continue; //파란 구슬이 빠진 경우
					
					if(red.i==blue.i && red.j==blue.j) {
						if(d==0) { //상
							if(tmp[0].i<tmp[1].i) blue.i += 1;
							else red.i += 1;
						} else if(d==1) { //하
							if(tmp[0].i<tmp[1].i) red.i -= 1;
							else blue.i -= 1;
						} else if(d==2) { //좌
							if(tmp[0].j<tmp[1].j) blue.j += 1;
							else red.j += 1;
						} else { //우
							if(tmp[0].j<tmp[1].j) red.j -= 1;
							else blue.j -= 1;
						}
					}
					q.add(new Ball[] {red, blue});
				}
			}
			
			cnt++;
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		gem = new Ball[2]; //0:빨강i, 1:빨강j, 2:파랑i, 3:파랑j
		
		for(int i=0;i<N;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				if(map[i][j]=='R') {
					Ball red = new Ball(i,j);
					gem[0] = red;
					map[i][j] = '.';
				}
				if(map[i][j]=='B') {
					Ball blue = new Ball(i,j);
					gem[1] = blue;
					map[i][j] = '.';
				}
			}
		} //end input
		
		System.out.println(bfs());
	}
	
	static class Ball {
		int i,j;
		public Ball(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
