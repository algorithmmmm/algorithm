package day1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18428_백자민 {
	
	static int N;
	
	static String answer;
	
	static String[][] map;
	
	static ArrayList<Point> stlist,tclist;
	
	static int[] di = {0,0,-1,1};
	static int[] dj = {-1,1,0,0};
	
	static void check() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		
		for(Point tmp: tclist) {
			q.offer(tmp);
		}
		
		while(!q.isEmpty()) {
			Point tmp = q.poll();
			visit[tmp.i][tmp.j] = true;
			
			for(int d=0;d<4;d++) {
				int ni = tmp.i+di[d];
				int nj = tmp.j+dj[d];
				
				while (ni>=0 && ni<N && nj>=0 && nj<N) {
					
					if(map[ni][nj].equals("O")) break;
										
					visit[ni][nj] = true;
					ni+=di[d];
					nj+=dj[d];
				}
			}
		}
		
		boolean flag = false;
		for(Point tmp: stlist) {
			if(visit[tmp.i][tmp.j])
				flag = true;
		}
		
		if(!flag)
			answer = "YES";
		
	}
	
	static void find(int cnt) {
		if(cnt == 3) {
			//모든 학생이 감시망에서 벗어났는지 확인
			check();
			return;
		}
		
		if(answer.equals("YES"))
			return;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j].equals("X")) {
					map[i][j] = "O";
					find(cnt+1);
					map[i][j] = "X";
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new String[N][N];
		stlist = new ArrayList<>();
		tclist = new ArrayList<>();
		
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j] = st.nextToken();
				
				if(map[i][j].equals("S"))
					stlist.add(new Point(i,j));
				
				else if(map[i][j].equals("T"))
					tclist.add(new Point(i,j));
			}
		}
		
		answer = "NO";
		//3개의 장애물 설치하기
		find(0);
		
		
		System.out.println(answer);
		
	}
	
	static class Point {
		int i,j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
