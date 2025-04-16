package day0912;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
......
......
......
......
......
......
......
......
......
......
RR..RR
RR..RR
*/

public class BOJ_11559_백자민 {
	
	static int answer;
		
	static char[][] map;
	static boolean[][] visit;
	
	static ArrayList<int[]> plist;
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	static void print(String s) {
		
		System.out.println(s);
		
		for(int i=0;i<12;i++) {
			for(int j=0;j<6;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void move(int j) {
		Queue<Character> q = new LinkedList<>();
		for(int i=11;i>=0;i--) {
			if(map[i][j]!='.') {
				q.offer(map[i][j]);
				map[i][j] = '.';
			}
		}
		
		int idx = 11;
		while(!q.isEmpty()) {
			map[idx][j] = q.poll();
			idx--;
		}
	}
	
	static void down() {
		for(int j=0;j<6;j++) {
			for(int i=11;i>=0;i--) {
				if(map[i][j]=='.') {
					move(j);
				}
			}
		}
	}
	
	static void find(int i, int j, char c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i,j});
		plist.add(new int[] {i,j});
		
		visit[i][j] = true;
		
		while(!q.isEmpty()) {
			
			int ti = q.peek()[0];
			int tj = q.poll()[1];
			
			for(int d=0;d<4;d++) {
				int ni = ti+di[d];
				int nj = tj+dj[d];
				
				if(ni<0 || ni>=12 || nj<0 || nj>=6) continue;
				
				if(visit[ni][nj] || map[ni][nj]!=c) continue;
				
				visit[ni][nj] = true;
				q.offer(new int[] {ni,nj});
				plist.add(new int[] {ni,nj});
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];
		answer = 0;
		
		for(int i=0;i<12;i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		boolean flag;
		while(true) {
			flag = false;
			visit = new boolean[12][6];
			
			for(int i=0;i<12;i++) {
				for(int j=0;j<6;j++) {
					if(map[i][j]!='.') { //뿌요 찾으면
						plist = new ArrayList<>();
						find(i,j,map[i][j]); //덩어리 찾기
						
						if(plist.size()>=4) { //연쇄 가능
							flag = true;
							for(int[] p: plist) //폭발할 뿌요 다 터트리기
								map[p[0]][p[1]] = '.'; 
						}
					}
				}
			}
			
			
			if(!flag) break; //더 이상 연쇄반응이 일어나는 뿌요가 없음 - 탐색 종료
			down();
			answer++;
		}
		
		System.out.println(answer);
	}
}