package day240416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16197_백자민 {
	
	static int N,M,ans;
	
	static char[][] map;
		
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	static boolean isDrop(int i, int j) { //동전이 보드에서 떨어졌는지 체크하는 함수
		if(i<0 || i>=N || j<0 || j>=M)  return true;
		
		return false;
	}
	
	static void bfs(int i1, int j1, int i2, int j2) {
		Queue<Coin> q = new LinkedList<>();
		q.offer(new Coin(i1, j1, i2, j2, 0));
		
		ans = 11;
		
		while(!q.isEmpty()) {
			Coin tmp = q.poll();
			
			if(tmp.cnt>10) continue; //10번보다 많이 버튼을 누르는 경우
			
			for(int d=0;d<4;d++) { //4방향 버튼 눌러보기
				int ni1 = tmp.i1+di[d];
				int nj1 = tmp.j1+dj[d];
				
				int ni2 = tmp.i2+di[d];
				int nj2 = tmp.j2+dj[d];
				
				boolean coin1=isDrop(ni1,nj1), coin2=isDrop(ni2,nj2);
				
				if(coin1 && coin2) continue; //두 동전이 모두 떨어진 경우
				
				if(coin1 || coin2) { //한 동전만 떨어진 경우
					ans = Math.min(ans, tmp.cnt+1); //최소값 갱신
					continue;
				}
				
				//벽이라면 이동하지 않음
				if(map[ni1][nj1]=='#') {
					ni1 = tmp.i1;
					nj1 = tmp.j1;
				}
				if(map[ni2][nj2]=='#') {
					ni2 =  tmp.i2;
					nj2 = tmp.j2;
				}
				//두 동전 모두 떨어지지 않은 경우
				q.offer(new Coin(ni1, nj1, ni2, nj2, tmp.cnt+1));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		int i1=-1,j1=-1,i2=-1,j2=-1;
		for(int i=0;i<N;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				if(map[i][j]=='o') {
					if(i1<0) {
						i1 = i;
						j1 = j;
					} else {
						i2 = i;
						j2 = j;
					}
					map[i][j]='.';
				}
			}
		} //end input
		
		bfs(i1,j1,i2,j2);
		System.out.println(ans==11?-1:ans);
		
	}
	
	static class Coin {
		int i1, j1, i2, j2, cnt;
		
		public Coin(int i1, int j1, int i2, int j2, int cnt) {
			this.i1 = i1;
			this.j1 = j1;
			this.i2 = i2;
			this.j2 = j2;
			this.cnt = cnt;
		}
	}
}
