package day1121;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_백자민 {
	
	static int N,K;
	
	static boolean[][] map;
	
	static Queue<Integer> snake;
	static HashMap<Integer, String> dir;
	
	static int[] di = {0,1,0,-1};
	static int[] dj = {1,0,-1,0}; //R-D-L-U (시계방향)
	
	static int start() {
		int time = 0;
		
		snake.offer(0); //처음 시작위치(0,0)
		int ti=0,tj=0,d=0;
		
		while(true) {
			time++;
			int ni = ti+di[d];
			int nj = tj+dj[d];
			
			if(ni<0 || nj<0 || ni>=N || nj>=N) break; //벽
			
			int tmp = ni*N+nj;
			if(snake.contains(tmp)) break;//몸통 
			
			//머리 이동
			if(map[ni][nj]) { //사과: 몸 늘어남 
				map[ni][nj] = false;
			} else { //빈칸: 꼬리이동
				snake.poll();
			}
			snake.offer(tmp);
			ti = ni;
			tj = nj;
			
			//이동 후 방향 전환
			if(dir.containsKey(time)) {
				if(dir.get(time).equals("D")) { //오른쪽
					d = d+1==4?0:d+1;
				} else { //왼쪽
					d = d-1<0?3:d-1;
				}
			}
		}
		
		return time;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new boolean[N][N];
		snake = new LinkedList<>();
		dir = new HashMap<>();
		
		StringTokenizer st;
		for(int k=0;k<K;k++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
		}
		
		int L = Integer.parseInt(br.readLine());
		for(int l=0;l<L;l++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String d = st.nextToken();
			dir.put(time, d);
		}
		//end input
		
		System.out.println(start());
		
		
		
	}
}
