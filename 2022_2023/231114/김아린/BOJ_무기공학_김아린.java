package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_무기공학 {
	static int N, M;
	static int[][] graph;
	static boolean[][] visited;
	//ㄱ, 」, ㄴ, 「
	//[][0]이 부메랑의 중심
	static int[][] dx = {{0, 0, 1}, {0, 0, -1}, {0, -1, 0}, {0, 1, 0}};
	static int[][] dy = {{0, -1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, 1}};

	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); //행 크기
		M = Integer.parseInt(st.nextToken()); //열 크기
		
		graph = new int[N][M]; //나무 재료의 크기
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//구현
		visited = new boolean[N][M]; //방문체크
		max = 0; //정답 : 최댓값
		
		dfs(0, 0, 0); //0, 0에서 탐색 시작
		
		//출력
		System.out.println(max);
		
	}
	
	public static void dfs(int x, int y, int sum) { //행, 열, 강도의 합  
		if(x == N && y == M) { //탐색 종료
			max = Math.max(max, sum); //최댓값 갱신
			return;
		}
		
		if(y == M) { //열이 끝까지 왔으면
			y = 0; //초기화
			x++; //행 좌표 늘리기
		}
		
		for(int d = 0; d < 4; d++) { //4가지 모양으로 탐색
			int n = getPower(x, y, d); //해당 행, 열, 모양으로 놓을 수 있는 강도 구하기
			
			if(n > 0) { //가능하면
				markVisited(x, y, d, true); //방문 체크
				dfs(x, y + 1, sum + n); //탐색
				markVisited(x, y, d, false); //방문 해제
			}
		}
		
		dfs(x, y + 1, sum); //해당 좌표에서 놓지 않고 탐색
	}

	private static int getPower(int x, int y, int d) {
		int sum = 0; //강도의 합
		
		for(int i = 0; i < 3; i++) { //3칸 찾기
			int nx = x + dx[d][i];
			int ny = y + dy[d][i];
			
			//범위 안에 들고, 방문x
			if(0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
				int value = graph[nx][ny];
				if(i == 0) { //중심 칸
					value *= 2;
				}
				
				sum += value;
			} else { //놓을 수 없음
				return 0; //종료
			}
		}
		
		return sum;
	}

	public static void markVisited(int x, int y, int d, boolean b) {
		for(int i = 0; i < 3; i++) {
			int nx = x + dx[d][i];
			int ny = y + dy[d][i];
			
			visited[nx][ny] = b;
		}
	}

}
