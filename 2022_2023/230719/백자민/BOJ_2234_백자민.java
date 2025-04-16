package day0719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2234_백자민 {
	
	static int N,M;
	static int[][] map, visit;
	
	static int[] di = {0,-1,0,1};
	static int[] dj = {-1,0,1,0};
	
	static ArrayList<Integer> roomlist;
	
	static int find(int i, int j, int max) {
		int size = roomlist.get(visit[i][j]-1);
		
		for(int d=0;d<4;d++) {
			if((map[i][j]&(1<<d))==0) continue;
			int ni = i+di[d], nj = j+dj[d];
			
			if(ni<0 || ni>=M || nj<0 || nj>=N) continue;
			
			if(visit[i][j]==visit[ni][nj]) continue;
			
			if(size + roomlist.get(visit[ni][nj]-1) < max) continue;

			max = size + roomlist.get(visit[ni][nj]-1);
		}
		
		return max;
		
	}
	
	static int bfs(int i, int j, int cnt) {
		Queue<int[]> q = new LinkedList<int[]>();
		
		q.offer(new int[] {i,j});
		visit[i][j] = cnt;
		int size = 1;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int ti = tmp[0], tj = tmp[1];
			
			for(int d=0;d<4;d++) {
				if((map[ti][tj]&(1<<d))>0) continue;
				
				int ni = ti+di[d], nj = tj+dj[d];
				
				if(visit[ni][nj]>0) continue;
				
				visit[ni][nj] = cnt;
				q.offer(new int[] {ni,nj});
				size++;
			}
		}
		
		return size;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} //end input
		
		visit = new int[M][N];
		int cnt = 1, max = Integer.MIN_VALUE; 
		roomlist = new ArrayList<>();		
		
		//방 개수 세기, 제일 큰 사이즈의 방 구하기, 각 방의 정보 입력하기 
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(visit[i][j]==0) {
					int tmp = bfs(i,j,cnt);
					max = Math.max(max, tmp);
					cnt++;
					roomlist.add(tmp);
				}
			}
		}
		cnt--;
		System.out.println(cnt+"\n"+max);
		
		max = Integer.MIN_VALUE;
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				max = Math.max(max, find(i,j, max));
			}
		}
		
		System.out.println(max);
		
		
		
	}
}
