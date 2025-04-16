import java.util.*;
import java.io.*;

public class BOJ_피리부는사나이 {
	static int N, M;
	static char[][] graph;
	static int[][] visited;
	static int[] dx = {-1, 1, 0, 0}; //U, D, L, R
	static int[] dy = {0, 0, -1, 1};
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(),  " ");

		N = Integer.parseInt(st.nextToken()); //행의 수
		M = Integer.parseInt(st.nextToken()); //열의 수
		
		graph = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			char[] chArr = br.readLine().toCharArray();
			
			for(int j = 0; j < M; j++) {
				graph[i][j] = chArr[j];
			}
		}
		
		//탐색
		visited = new int[N][M]; //방문 표시 : 0(방문X), 1이상(방문O)
		count = 0; //정답 : SAFE ZONE의 최소 개수
		int idx = 0; //탐색 인덱스
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j] == 0) { //방문한 적 없으면
					dfs(i, j, ++idx); //탐색
				}
			}
		}
		
		//출력
		System.out.println(count);
	}

	public static void dfs(int x, int y, int idx) {
		visited[x][y] = idx;
		
		int nx = x; //다음 이동 행
		int ny = y; //다음 이동 열
		
		switch(graph[x][y]) {
			case 'U' :
				nx += dx[0];
				ny += dy[0];
				break;
			case 'D' :
				nx += dx[1];
				ny += dy[1];
				break;
			case 'L' :
				nx += dx[2];
				ny += dy[2];
				break;
			case 'R' :
				nx += dx[3];
				ny += dy[3];
				break;
		}
		
		if(0 <= nx && nx < N && 0 <= ny && ny < M) {
			if(visited[nx][ny] == 0) { //방문한 적 없음
				dfs(nx, ny, idx);
			} else { //방문한 적 있음
				if(visited[nx][ny] == idx) { //사이클 새로 완성
					count++;
					return;
				}
				//현재의 인덱스와 다름(=이전에 만든 사이클)
			}
		}
	}

}