package boj1303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
/*
 * Map의 각 방문하지 않은 좌표에 대해 BFS를 돌린다.
 * BFS를 돌려서 인접한 같은 편의 숫자를 구한다.
 * Map의 값에 따라 white나 blue에 위력을 누적한다.
 * */
public class Main {
	static class Point {
		int i, j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	static int N, M;
	static char[][] Map;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inarr = br.readLine().split(" ");
		M = Integer.parseInt(inarr[0]);	// M : Map의 가로 길이
		N = Integer.parseInt(inarr[1]);	// N : Map의 세로 길이
		Map = new char[N][];	// Map[i][j] : (i, j) 위치의 병사가 아군(W)인지 적군(B)인지
		for (int i = 0; i < N; i++) {
			Map[i] = br.readLine().toCharArray();
		}

		int white = 0;	// white : 아군의 위력
		int blue = 0;	// blue : 적군의 위력
		visit = new boolean[N][M];	// visit[i][j] : Map[i][j] 위치를 방문했는가
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visit[i][j])
					continue;
				int num = bfs(new Point(i, j));
				if(Map[i][j] == 'W') {
					white += num*num;
				}else {
					blue += num*num;
				}
			}
		}
		System.out.println(white + " " + blue);
	}

	// 델타 탐색을 하기 위해 필요한 di, dj
	static int[] di = { -1, 1, 0, 0 };	
	static int[] dj = { 0, 0, -1, 1 };

	// 입력으로 위치 pnt가 들어오고
	// 출력으로 pnt를 포함하여 같은 팀 병사가 주변에 몇 명인지 
	static int bfs(Point pnt) {
		visit[pnt.i][pnt.j] = true;
		Queue<Point> Q = new ArrayDeque<>();

		int size = 1;	// size : 주변에 같은 팀의 수. 나를 포함해야하기 때문에 1부터
		Q.add(pnt);
		while (!Q.isEmpty()) {
			Point p = Q.poll();
			for (int k = 0; k < 4; k++) {
				int ni = p.i + di[k];
				int nj = p.j + dj[k];
				// 범위를 벗어나거나 이미 방문했다면 continue
				if (ni < 0 || nj < 0 || ni >= N || nj >= M || visit[ni][nj]) {
					continue;
				}
				// 같은 팀이 아니라면 continue
				if (Map[pnt.i][pnt.j] != Map[ni][nj]) {
					continue;
				}
				Q.add(new Point(ni, nj));
				visit[ni][nj] = true;
				size++;
			}
		}
		return size;
	}

}
