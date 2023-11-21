package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_뱀 {
	static class Point {
		int i, j;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static int N, K, L, map[][], second, head;
	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };
	static HashMap<Integer, Character> direction;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		// K개의 줄에는 사과의 위치가 주어짐
		StringTokenizer st;
		map = new int[N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			map[a][b] = 1; // 사과 표시
		}

		L = Integer.parseInt(br.readLine());
		direction = new HashMap<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			direction.put(s, c);
		}
		
		move();
		
		System.out.println(second);

	}

	public static void move() {
		Queue<Point> Q = new LinkedList<>();
		Q.add(new Point(0, 0));
		map[0][0] = 2; // 뱀 표시
		int nowi = 0, nowj = 0;
		head = 0;
		second = 0;

		while (true) {
			int ni = nowi + di[head];
			int nj = nowj + dj[head];
			second++;

			if (ni < 0 || nj < 0 || ni >= N || nj >= N)
				break;
			if (map[ni][nj] == 2)
				break;

			if (map[ni][nj] == 1) { // 사과 먹음
				map[ni][nj] = 2;
				Q.add(new Point(ni, nj));
			} else { // 안 먹음
				Q.add(new Point(ni, nj));
				map[ni][nj] = 2;
				
				Point tail = Q.poll();
				map[tail.i][tail.j]= 0; 
			}

			if (direction.containsKey(second)) {
				char dir = direction.get(second);
				if (dir == 'L') {
					head -= 1;
					if (head == -1)
						head = 3;
				} else {
					head += 1;
					if (head == 4)
						head = 0;
				}
			}

			nowi = ni;
			nowj = nj;
		}
	}
}
