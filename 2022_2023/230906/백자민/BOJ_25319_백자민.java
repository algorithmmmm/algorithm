package day0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_25319_백자민 {
	
	static int N,M,S,C;
	
	static String id;
	
	static StringBuilder sb;
	
	static char[][] map;
	
	static int[] di = {-1,0,1,0};
	static int[] dj = {0,-1,0,1};
	static String[] ds = {"U","L","D","R"};
	
	static int[] bfs(int i, int j, int idx, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i,j});
		
		boolean[][] visit = new boolean[N][M];
		visit[i][j] = true;
		while(true) {
			int ti = q.peek()[0];
			int tj = q.poll()[1];
			
			for(int d=0;d<4;d++) {
				int ni = ti+di[d];
				int nj = tj+dj[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
				
				if(visit[ni][nj]) continue;
				
				visit[ni][nj] = true;
				if(map[ni][nj]==id.charAt(idx)) {
					if(idx==id.length()-1) 
						return new int[] {ni,nj,0,c+1};
					else
						return new int[] {ni,nj,idx+1,c};
				}
				q.offer(new int[] {ni,nj});
			}
		}
	}
	
	static void move(int i, int j, int targeti, int targetj) {
		while(true) {
			if(i==targeti && j== targetj) break;
			
			if(i<targeti) {
				sb.append(ds[2]);
				i+=di[2];
			} else if(i>targeti) {
				sb.append(ds[0]);
				i+=di[0];
			} else if(j<targetj) {
				sb.append(ds[3]);
				j+=dj[3];
			} else if(j>targetj) {
				sb.append(ds[1]);
				j+=dj[1];
			}
		}
	}
	
	static void start() {
		
		int idx=0,ti=0,tj=0,c=0;
		
		//1. 시작점에서 가장 가까운 id 첫 번째 글자 찾기
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==id.charAt(0)) {
					move(ti,tj,i,j);
					if(idx==id.length()-1) { //반례: 아이디가 한 글자면 이미 강화에 성공했다.
						c++;
						idx = 0;
					} else {
						idx++;
					}
					ti = i;
					tj = j;
					map[i][j] = '.';
					sb.append("P");
					break;
				}
			}
			
			if(sb.length()>0)
				break;
		}
		
		//2. bfs 탐색을 하면서 강화하기
		while(true) {
			
			if(c==C) {
				break;
			}
			
			int[] temp = bfs(ti,tj,idx,c); //강화할 아이템 찾았음 
			move(ti,tj,temp[0],temp[1]); //이동
			
			//다음 탐색 시작 위치 변경 
			ti = temp[0];
			tj = temp[1];
			
			map[ti][tj] = '.'; //아이템 줍기 
			sb.append("P");
			
			idx = temp[2];
			c = temp[3];
		}
		
		//3. 탈출구로 이동하기
		move(ti,tj,N-1,M-1);
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		HashMap<Character, Integer> mapMap = new HashMap<>();
		
		for(int i=0;i<N;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				mapMap.put(map[i][j], mapMap.getOrDefault(map[i][j], 0)+1);
			}
		}
		
		HashMap<Character, Integer> idMap = new HashMap<>();
		id = br.readLine();
		for(int i=0,size=id.length();i<size;i++) {
			char c = id.charAt(i);
			idMap.put(c, idMap.getOrDefault(c, 0)+1);
		}
		//end input
		
		//최대 강화 횟수(C) 찾기 
		C = Integer.MAX_VALUE;
		for(char c: idMap.keySet()) {
			int tmp = Math.floorDiv(mapMap.getOrDefault(c, 0), idMap.get(c));
			C = Math.min(C, tmp);
		}
		
		sb = new StringBuilder();
		if(C==0) {
			move(0,0,N-1,M-1); //최대 강화 횟수가 0이라면 바로 탈출구로 이동 			
		}
		else {
			start();
		}
		
		
		System.out.println(C+" "+sb.length());
		System.out.println(sb);
		
	}
}
