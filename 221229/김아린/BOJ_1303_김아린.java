import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 전쟁터의 병사 위치 정보를 그래프로 저장
 * 2. 그래프에 있는 모든 방문되지 않은 정점에 대해 bfs를 시작함. 이때 팀 정보도 같이 인자로 넘겨줌
 * 	2-1. bfs 델타 탐색하며, 인접한 같은 팀 병사의 수를 카운팅
 * 	2-2. 결과값을 제곱하여 해당 팀의 위력의 합을 갱신
 */
public class BOJ_1303_김아린 {
	static int N, M; //전쟁터의 가로, 세로 크기
	static char[][] graph; //병사 배치 그래프
	static boolean[][] visited; //탐색시 방문여부 체크
	static int[] dx = {1, -1, 0, 0}; //상, 하, 좌, 우
	static int[] dy = {0, 0, -1, 1};
	
	static class Node { //병사의 위치 클래스
		int x, y; //행, 열

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		
		graph = new char[M][N];
		visited = new boolean[M][N];
		
		for(int i = 0; i < M; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				graph[i][j] = line.charAt(j);
			}
		}
		
		//구현
		int wPower = 0, bPower = 0; //아군 위력 합, 적군 위력 합
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j] && graph[i][j] == 'W') { //방문x, 아군(흰색) -> 탐색
					wPower += Math.pow(bfs(i, j, 'W'), 2);
				} else if(!visited[i][j] && graph[i][j] == 'B') { //방문x, 적군(파란색) -> 탐색
					bPower += Math.pow(bfs(i, j, 'B'), 2);
				}
			}
		}
		
		//출력
		System.out.println(wPower + " " + bPower);
	}

	public static int bfs(int i, int j, char c) {
		Queue<Node> queue = new LinkedList<>(); //병사 위치를 담아 bfs 돌릴 큐 
		
		queue.add(new Node(i, j));
		visited[i][j] = true;
		int count = 1; //같은 팀 병사 수 세기 위한 카운터 
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				
				if(0 <= nx && nx < M && 0 <= ny && ny < N) { //그래프 범위 안에 있고
					if(!visited[nx][ny] && graph[nx][ny] == c) { //방문x, 같은 병사이면
						queue.add(new Node(nx, ny)); //탐색
						visited[nx][ny] = true;
						count++;
					}
				}
			}
		}
		return count;
	}

}
