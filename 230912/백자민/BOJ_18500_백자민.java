package day0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18500_백자민 {
	
	static int R,C;
	
	static char[][] map;
	
	static boolean[][] visit;
		
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {i,j});
		visit[i][j] = true;

		while(!q.isEmpty()) {
			int ti = q.peek()[0];
			int tj = q.poll()[1];
			
			for(int d=0;d<4;d++) {
				int ni = ti+di[d];
				int nj = tj+dj[d];
				
				if(ni<0 || ni>=R || nj<0 || nj>=C) continue;
				if(visit[ni][nj] || map[ni][nj] == '.') continue;
				
				visit[ni][nj] = true;
				q.offer(new int[] {ni,nj});
			}
		}
	}
	
	static void moveMineral() {
		//바닥에 붙어있는 미네랄들(안움직임)찾기
		for(int j=0;j<C;j++) {
			if(map[R-1][j]=='x' && !visit[R-1][j])
				bfs(R-1,j);
		}
		
		
		//떠있는 미네랄 찾아서 리스트에 넣기
		ArrayList<int[]> mlist = new ArrayList<>();
		for(int i=R-2;i>=0;i--) {
			for(int j=0;j<C;j++) {
				if(map[i][j]=='x' && !visit[i][j]) {
					mlist.add(new int[] {i,j});
					map[i][j] = '.';
					visit[i][j] = true;
				}
			}
		}
		
		//미네랄 아래로 내려주기
		if(mlist.size() > 0) {
			
			boolean flag = false;
			
			while(true) {
//				int next = mlist.get(0)[0]+1; //떨어질 미네랄 중 가장 하단에 있는 미네랄 
//				if(next >=R || map[next][mlist.get(0)[1]]=='x') //이미 바닥에 있거나 다른 미네랄에 닿을 경우에는 떨어지기 종료
//					break;
				
				for(int[] m:mlist) {
					int next = m[0]+1;
					if(next>=R || map[next][m[1]]=='x') {
						flag = true;
						break;
					}
				}
				
				if(flag)
					break;
				
				for(int[] m: mlist) {
					m[0]++;
				}
			}
			
			//동굴에 떨어진 미네랄 위치 입력 
			for(int[] m: mlist) {
				map[m[0]][m[1]] = 'x';
			}
		}
		
	}
	
	static void remove(int i, int n) {
		if(n%2==0) {//짝수면 왼쪽에서 오른쪽으로 
			for(int j=0;j<C;j++) {
				if(map[i][j]=='x') {
					map[i][j] = '.';
					break;
				}
			}
		} else { //얜 반대
			for(int j=C-1;j>=0;j--) {
				if(map[i][j]=='x') {
					map[i][j] = '.';
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i=0;i<R;i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int n=0;n<N;n++) {
			int i = R - Integer.parseInt(st.nextToken());
			remove(i,n);
			visit = new boolean[R][C];
			moveMineral();
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
