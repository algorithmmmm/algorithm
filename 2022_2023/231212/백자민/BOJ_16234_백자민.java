package day1212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234_백자민 {
	
	static int N,L,R;
	static boolean flag;
	
	static int[][] map;
	static boolean[][] visit;
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		ArrayList<int[]> list = new ArrayList<>();
		
		q.offer(new int[] {i,j});
		visit[i][j] = true;
	
		list.add(new int[] {i,j});
		
		int sum = map[i][j];
		
		while(!q.isEmpty()) {
			int ti = q.peek()[0];
			int tj = q.poll()[1];
			
			for(int d=0;d<4;d++) {
				int ni = ti+di[d];
				int nj = tj+dj[d];
				
				if(ni<0 || nj<0 || ni>=N || nj>=N) continue;
				
				if(visit[ni][nj]) continue;
				
				int people = Math.abs(map[ti][tj]-map[ni][nj]);
				if(people<L || people>R) continue;
				
				flag = true;
				visit[ni][nj] = true;
				sum += map[ni][nj];
				list.add(new int[] {ni,nj});
				q.offer(new int[] {ni,nj});
			}
		}
		
		int size = list.size();
		for(int[] tmp: list) {
			map[tmp[0]][tmp[1]] = sum/size;
		}		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} //end input
		
		int answer = 0;
		
		while(true) {
			visit = new boolean[N][N];
			flag = false;
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visit[i][j]) {
						bfs(i,j);
					}
				}
			}
			
			if(!flag) break;
			
			answer++;
		}
		
		System.out.println(answer);
	}
}
