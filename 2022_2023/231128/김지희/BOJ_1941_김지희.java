import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class Point{
		int i,j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	static char[][] map;
	static boolean[][] visit;
	static int answer;
	static Point[] select;
	
	static int[] di = {0, -1, 1, 0};
	static int[] dj = {1, 0, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][5];
		for(int i=0; i<5; i++) {
			map[i] = br.readLine().toCharArray();
		}
		select = new Point[7];
		visit = new boolean[5][5];
		comb(0, 0);
		System.out.println(answer);
	}
	
	public static void comb(int depth, int cnt) {
		if(cnt==7) {
			if(bfs()) answer++;
			return;
		}
		for(int i=depth; i<25; i++) {
			if(visit[i/5][i%5]) continue;
			visit[i/5][i%5] = true;
			select[cnt] = new Point(i/5, i%5);
			comb(i+1, cnt+1);
			visit[i/5][i%5] = false;
		}
		
	}
	
	public static boolean bfs() {
		boolean check[][] = new boolean[5][5];
		for(int i=0; i<5; i++) {
			check[i] = visit[i].clone();
		}
		Point start = select[0];
		Queue<Point> Q = new LinkedList<>();
		Q.add(start);
		check[start.i][start.j]= false; 
		int cnt = 1;
		int somCnt =map[start.i][start.j]=='S' ? 1 : 0; 
		
		while(!Q.isEmpty()) {
			Point cur = Q.poll();
			
			for(int d=0; d<4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				
				if(ni<0 || nj<0 || ni>=5 || nj>=5) continue;
				if(!check[ni][nj]) continue;
				
				if(map[ni][nj]=='S') somCnt++;
				cnt++;
				Q.add(new Point(ni, nj));
				check[ni][nj] = false;
			}
		}
		
		if(cnt==7 && somCnt>=4) return true;
		return false;
	}
}