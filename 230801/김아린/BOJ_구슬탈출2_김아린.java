package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ구슬탈출2 {
	static int N, M;
	static char[][] board;
	static boolean[][][][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Point {
		int bx, by, rx, ry; // 파랑 행렬 좌표, 빨강 행렬 좌표
		
		Point() {};

		Point(int bx, int by, int rx, int ry) {
			this.bx = bx;
			this.by = by;
			this.rx = rx;
			this.ry = ry;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];

		Point start = new Point(); // 구슬의 시작 위치

		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();

			// 구슬과 구멍의 위치 저장
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 'R') {
					start.rx = i;
					start.ry = j;
				} else if (board[i][j] == 'B') {
					start.bx = i;
					start.by = j;
				}
			}
		}

		// 처리, 결과
		visited = new boolean[N][M][N][M]; // 파랑, 빨강

		System.out.println(bfs(start));
	}

	public static int bfs(Point start) {
		Queue<Point> queue = new LinkedList<>();

		queue.add(start);
		visited[start.bx][start.by][start.rx][start.ry] = true;

		int count = 0; //횟수
		while (!queue.isEmpty()) {
			count++;
			
			if (count > 10) { // 10회 넘으면 종료
				return -1;
			}
			
			int size = queue.size();
			for(int s = 0; s < size; s++) {
				Point point = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					int nbx = point.bx;
					int nby = point.by;
					int nrx = point.rx;
					int nry = point.ry;

					// 파랑 이동
					boolean isFail = false; // 실패 여부
					boolean isRed = false; // 빨강을 만났는지
					while (board[nbx][nby] != '#') { // 벽에 닿기 전까지
						nbx += dx[d];
						nby += dy[d];

						if (board[nbx][nby] == 'O') { // 구멍에 빠짐
							isFail = true;
							break;
						} else if (nbx == nrx && nby == nry) { // 빨강 만났는지 학인
							isRed = true;
						}
					}
					nbx -= dx[d];
					nby -= dy[d];

					//파랑이 구멍에 빠졌으면 일단 넘김(다음 탐색에서 찾을 수도 있고, 어차피 뒤에서 빨강 못찾으면 중단됨)
					if (isFail) {
						continue;
					}

					if (isRed) { //빨강을 만난적이 있었으므로 한칸 뒤로
						nbx -= dx[d];
						nby -= dy[d];
					}

					// 빨강 이동
					while (board[nrx][nry] != '#') { // 벽 닿기 전까지
						nrx += dx[d];
						nry += dy[d];

						// 성공
						if (board[nrx][nry] == 'O') { // 빨강 구슬이 빠짐
							return count;
						}
					}
					nrx -= dx[d];
					nry -= dy[d];
					
					if (nrx == nbx && nry == nby) { // 파랑 있으면 뒤로 미룸
						nrx -= dx[d];
						nry -= dy[d];
					}
					

					if (!visited[nbx][nby][nrx][nry]) { // 방문한적 없으면 이동
						queue.add(new Point(nbx, nby, nrx, nry));
						visited[nbx][nby][nrx][nry] = true;
					}
				}
			}
		}
		return -1;
	}

}
