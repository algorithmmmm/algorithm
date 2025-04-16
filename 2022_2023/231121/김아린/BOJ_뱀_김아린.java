package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_뱀 {
	static int N;
	static boolean[][] graph;
	static Deque<Point> snake;
	static int[] dx =  {0, 1, 0, -1}; //우하좌상
	static int[] dy =  {1, 0, -1, 0};
	
	static class Point {
		int x, y, dir;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		graph = new boolean[N][N];
		
		int K = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			graph[x - 1][y - 1] = true;
		}
		
		int L = Integer.parseInt(br.readLine());
		
		HashMap<Integer, Character> orderMap = new HashMap<>(); //명령어 저장
		
		for(int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			
			orderMap.put(X, C);
		}

		snake = new LinkedList<>(); //뱀의 몸
		snake.add(new Point(0, 0)); //맨위 맨좌측에서 시작
		int dir = 0; //방향 
		
		int answer = 0; //시간
		while(true) {
			answer++;
			
			//다음칸에 위치
			Point point = snake.peek();
			int nx = point.x + dx[dir];
			int ny = point.y + dy[dir];
			
			if(isHit(nx, ny)) break;
			
			snake.addFirst(new Point(nx, ny));
			
			if(graph[nx][ny]) { //사과있음
				graph[nx][ny] = false; //사과 없어짐
			} else { //없으면
				snake.pollLast(); //꼬리칸 비워줌
			}
			
			//방향 전환
			if(orderMap.containsKey(answer)) {
				char order = orderMap.get(answer);
				
				if(order == 'L') { //왼쪽
					if(dir == 0) {
						dir = 3;
					} else {
						dir--;
					}
				} else { //오른쪽
					if(dir == 3) {
						dir = 0;
					} else {
						dir++;
					}
				}
			}
		}

		System.out.println(answer);
	}
	
	public static boolean isHit(int nx, int ny) {
		if(0 > nx || nx >= N || 0 > ny || ny >= N) {
			return true;
		}
		
		for(Point p : snake) {
			if(nx == p.x && ny == p.y)  {
				return true;
			}
		}
		
		return false;
	}

}
