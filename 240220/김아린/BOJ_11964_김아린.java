package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_FruitFeast {
	static int T, A, B;
	static boolean[][] visited;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		T = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		visited = new boolean[2][T + 1]; //0 : 물 안먹음, 1 : 물 먹음
		max = 0; //정답 : 최댓값
		
		bfs();
		
		System.out.println(max);
	}

	public static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.add(new int[] {A, 0}); //오렌지 먹음
		queue.add(new int[] {B, 0}); //레몬 먹음
		visited[0][A] = true;
		visited[0][B] = true;
		
		while(!queue.isEmpty()) {
			int[] p = queue.poll();
			int fullness = p[0]; //포만감
			int drink = p[1]; //물 마셨는지
			
			max = Math.max(max, fullness);
			
			//오렌지
			int nextOrange = fullness + A;
			
			if(nextOrange <= T && !visited[drink][nextOrange]) {
				queue.add(new int[] {nextOrange, drink});
				visited[drink][nextOrange] = true;
			}
				
			//레몬
			int nextRemon = fullness + B;
			
			if(nextRemon <= T && !visited[drink][nextRemon]) {
				queue.add(new int[] {nextRemon, drink});
				visited[drink][nextRemon] = true;
			}	
			
			//물
			if(drink == 0) {
				int nextWater = fullness / 2;
				
				if(!visited[1][nextWater] && !visited[0][nextWater]) {
					queue.add(new int[] {nextWater, 1});
					visited[1][nextWater] = true;
				}
			}
		}
		
	}

}
