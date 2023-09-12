import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] graph;
	static boolean[][] visited;
	static int dx[] = {1, -1, 0, 0}; //상하좌우
    static int dy[] = {0, 0, 1, -1};
    //ㅜ, ㅏ, ㅗ, ㅓ
    static int ex[][] = {{0, 0, 0, 1}, {0, 1, 2, 1}, {0, 0, 0, -1}, {0, -1, 0, 1}};
    static int ey[][] = {{0, 1, 2, 1}, {0, 0, 0, 1}, {0, 1, 2, 1}, {0, 1, 1, 1}};
    
    static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); //세로 길이
		M = Integer.parseInt(st.nextToken()); //가로 길이
		
		graph = new int[N][M]; //그래프
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//탐색
		visited = new boolean[N][M];
		
		//상하좌우 탐색으로 만들 수 있는 모양(ㅜ 제외 놓기)
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, graph[i][j], 1);
				visited[i][j] = false;
				
				putPink(i, j); //ㅜ 모양 놓기(핑크색)
			}
		}
		
		//출력
		System.out.println(max);
	}
	
	public static void dfs(int x, int y, int sum, int depth) { //행, 열, 합, 깊이
		if(depth == 4) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
            int ny = y + dy[d];
            
            if(0 <= nx && nx < N && 0 <= ny && ny < M) { //범위 안에 있고
            	if(!visited[nx][ny]) { //방문 안 했으면
            		visited[nx][ny] = true;
            		dfs(nx, ny, sum + graph[nx][ny], depth + 1);
            		visited[nx][ny] = false;
            	}
            }
		}
	}
	
	public static void putPink(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int sum = 0;
			boolean isFail = false; //실패 여부
			
			for(int j = 0; j < 4; j++) {
				int nx = x + ex[i][j];
	            int ny = y + ey[i][j];
				
	            if(0 <= nx && nx < N && 0 <= ny && ny < M) { //범위 안에 있음
					sum += graph[nx][ny];
				} else {
					isFail = true;
					break;
				}
			}
			
			if(!isFail) {
				max = Math.max(max, sum);
			}
		}
	}
}