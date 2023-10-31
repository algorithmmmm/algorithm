import java.io.*;
import java.util.*;

public class Main {
	static int L, R, C;
	static char[][][] graph;
	static boolean[][][] visited;
	static int[] dx = {-1, 1, 0, 0, 0, 0}; //상하동서남북
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			//크기 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if(L == 0 && R == 0 && C == 0) break; //종료
			
			//그래프 입력
			graph = new char[L][R][C];
			
			int[] start = new int[3]; //시작 지점
			int[] end = new int[3]; //탈출 지점
			
			for(int i = 0; i < L; i++) {
				for(int j = 0; j < R; j++) {
					char[] chs = br.readLine().toCharArray();
					
					for(int k = 0; k < C; k++) {
						graph[i][j][k] = chs[k];
						
						if(chs[k] == 'S') {
							start[0] = i;
							start[1] = j;
							start[2] = k;
						} else if(chs[k] == 'E') {
							end[0] = i;
							end[1] = j;
							end[2] = k;
						}
					}
				}
				br.readLine();
			}
			
			//구현
			visited = new boolean[L][R][C];
			
			int x = bfs(start, end);
			
			if(x == 0) {
				System.out.println("Trapped!");
			} else {
				System.out.println("Escaped in "+ x + " minute(s).");
			}
		}
		
	}

	public static int bfs(int[] start, int[] end) {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.add(new int[] {start[0], start[1], start[2]});
		visited[start[0]][start[1]][start[2]] = true;
		
		int count = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int s = 0; s < size; s++) {
				int[] p = queue.poll();
				
				if(p[0] == end[0] && p[1] == end[1] && p[2] == end[2]) {
					return count;
				}
				
				for(int d = 0; d < 6; d++) {
					int nx = p[0] + dx[d];
					int ny = p[1] + dy[d];
					int nz = p[2] + dz[d];
					
					if(0 > nx || nx >= L || 0 > ny || ny >= R || 0 > nz || nz >= C) continue;
					
					if(!visited[nx][ny][nz] && graph[nx][ny][nz] != '#') {
						queue.add(new int[] {nx, ny, nz});
						visited[nx][ny][nz] = true;
					}
				}
			}
			count++;
		}
		return 0;
	}

}