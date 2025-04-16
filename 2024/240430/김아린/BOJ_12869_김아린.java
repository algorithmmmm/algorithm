package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_뮤탈리스크 {
	static int[][] attackArr = new int[][]{{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] scv = new int[3];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for(int i = 0; i < N; i++) {
			scv[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(bfs(scv));
	}
	
	public static int bfs(int[] scv) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[scv[0] + 1][scv[1] + 1][scv[2] + 1];
		
		queue.add(scv);
		visited[scv[0]][scv[1]][scv[2]] = true;
		
		int count = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int s = 0; s < size; s++) {
				int[] p = queue.poll();
				
				for(int i = 0; i < 6; i++) {
					int next1 = p[0] - attackArr[i][0] <= 0 ? 0 : p[0] - attackArr[i][0];
					int next2 = p[1] - attackArr[i][1] <= 0 ? 0 : p[1] - attackArr[i][1];
					int next3 = p[2] - attackArr[i][2] <= 0 ? 0 : p[2] - attackArr[i][2];
					
					if(next1 == 0 && next2 == 0 && next3 == 0) return count + 1;
					
					if(!visited[next1][next2][next3]) {
						queue.add(new int[]{next1, next2, next3});
						visited[next1][next2][next3] = true;
					}
				}
			}
			count++;
		}
		
		return count;
	}

}
